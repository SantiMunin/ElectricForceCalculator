package es.udc.smunin.electricforce.data;

import java.io.Serializable;

public class Charge implements Serializable {

	private static final long serialVersionUID = 808016622567491760L;
	private final static double K = 9e9D;

	private double x;
	private double y;
	private double value;

	public Charge(double x, double y, double value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getValue() {
		return value;
	}

	public double calculateForce(Charge[] charges) {
		double result = 0;
		for (int i = 0; i < charges.length; i++) {
			result += this.calculateForce(charges[i]);
		}
		return result;
	}

	private double distance(Charge charge) {
		double x = Math.pow(this.x - charge.getX(), 2);
		double y = Math.pow(this.y - charge.getY(), 2);
		return (Math.sqrt(x + y));
	}

	public double calculateForce(Charge charge) {
		double r = distance(charge);
		if (r == 0) {
			return 0;
		}
		return (this.value * charge.value * K) / Math.pow(r, 2);
	}
}