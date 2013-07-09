package es.udc.smunin.electricforce;

import mpi.MPI;
import es.udc.smunin.electricforce.data.Charge;

public class ParallelWorker {
	private Charge[] charges;
	private int myrank;
	private int nproc;
	private int totalSize = 0;
	private int offset = 0;
	private int amountOfWork = 0;

	public ParallelWorker(int myrank, int nproc, Charge[] charges) {
		super();
		this.myrank = myrank;
		this.nproc = nproc;
		this.charges = charges;
	}

	private int getOffset() {
		return (totalSize / nproc) * myrank;
	}

	private int getWorkSize() {
		return myrank < nproc - 1 ? totalSize / nproc : totalSize / nproc
				+ totalSize % nproc;
	}

	private Charge[] broadcastCharges(Charge[] charges) {

		if (myrank == 0) {
			int[] nCharges = new int[] {charges.length};
			MPI.COMM_WORLD.Bcast(nCharges, 0, 1, MPI.INT, 0);
			MPI.COMM_WORLD.Bcast(charges, 0, charges.length, MPI.OBJECT, 0);
			return charges;
		} else {
			int[] nCharges = new int[] { 0 };
			MPI.COMM_WORLD.Bcast(nCharges, 0, 1, MPI.INT, 0);
			Charge[] receivedCharges = new Charge[nCharges[0]];
			MPI.COMM_WORLD
					.Bcast(receivedCharges, 0, nCharges[0], MPI.OBJECT, 0);
			return receivedCharges;
		}

	}

	private double[] gatherResult(double[] result) {
		double[] total_result = myrank == 0 ? new double[totalSize] : null;
		MPI.COMM_WORLD.Gather(result, 0, amountOfWork, MPI.DOUBLE,
				total_result, 0, amountOfWork, MPI.DOUBLE, 0);
		return total_result;
	}

	private double[] doWork(Charge[] charges) {

		double[] result = new double[amountOfWork];
		for (int i = offset; i < offset + amountOfWork; i++) {
			result[i - offset] = charges[i].calculateForce(charges);
		}

		return result;
	}

	public double[] calculateForces() {
		totalSize = charges.length;
		this.offset = getOffset();
		this.amountOfWork = getWorkSize();

		charges = broadcastCharges(charges);

		double[] result = doWork(charges);
		return gatherResult(result);

	}
}
