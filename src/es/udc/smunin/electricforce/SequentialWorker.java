package es.udc.smunin.electricforce;

import es.udc.smunin.electricforce.data.Charge;

/**
 * Performs the calculation using one single thread.
 * 
 * @author Santiago Mun’n <santimunin@gmail.com>
 * 
 */
public class SequentialWorker {

	private Charge[] charges;

	public SequentialWorker(Charge[] charges) {
		super();
		this.charges = charges;
	}

	public double[] calculateForces() {
		double[] forces = new double[charges.length];
		for (int i = 0; i < forces.length; i++) {
			forces[i] = charges[i].calculateForce(charges);
		}
		return forces;
	}
}
