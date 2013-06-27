package es.udc.smunin.electricforce;

import es.udc.smunin.electricforce.data.Charge;

public class SequentialWorker {

	public double[] calculateForces(Charge[] charges) {
		double[] forces = new double[charges.length];
		for (int i = 0; i < forces.length; i++) {
			forces[i] = charges[i].calculateForce(charges);
		}
		return forces;
	}
}
