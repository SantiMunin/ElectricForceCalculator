package es.udc.smunin.electricforce;

import java.text.DecimalFormat;

import mpi.MPI;
import es.udc.smunin.electricforce.data.Charge;

public class Main {

	private static Charge[] generateCharges(int n) {
		Charge[] charges = new Charge[n];
		for (int i = 0; i < charges.length; i++) {
			double x, y, c;
			x = Math.random() * 10;
			y = Math.random() * 10;
			c = Math.random() * 10;
			charges[i] = new Charge(x, y, c);
		}
		return charges;
	}

	private static void printResults(double[] results, double startTime,
			double endTime) {
		System.out.println("Results");
		DecimalFormat df = new DecimalFormat("#.##");
		for (int i = 0; i < results.length; i++) {
			System.out.print(df.format(results[i]) + " ");
		}
		System.out.println("\nThis computation needed "
				+ String.valueOf(endTime - startTime) + " seconds to be done");
	}

	public static void main(String[] args) {
		int size, myrank;
		args = MPI.Init(args);
		size = MPI.COMM_WORLD.Size();
		myrank = MPI.COMM_WORLD.Rank();
		
		if (args.length != 1 && myrank == 0) {
			System.out.println("Usage: sh execute.sh <num_procs> <num_charges>");
			MPI.Finalize();
			System.exit(-1);
		}
		Charge[] charges = generateCharges(Integer.parseInt(args[0]));
		double[] result = null;
		double startTime = MPI.Wtime();
		if (size == 1) {
			result = new SequentialWorker(charges).calculateForces();
		} else {
			result = new ParallelWorker(myrank, size, charges)
					.calculateForces();
		}
		double endTime = MPI.Wtime();
		if (myrank == 0) {
			printResults(result, startTime, endTime);
		}
		MPI.Finalize();
	}
}
