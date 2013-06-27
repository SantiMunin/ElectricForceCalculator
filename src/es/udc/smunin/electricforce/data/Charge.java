package es.udc.smunin.electricforce.data;

import java.io.Serializable;

public class Charge implements Serializable{

	private static final long serialVersionUID = 808016622567491760L;
	private final static double K = 9e9D;
	private final static int N = 100000;

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
		return (this.value * charge.value * K) / Math.pow(r,2);
	}

	public static void main(String[] args) {
		Charge[] charges = new Charge[N];
		double[] result = new double[N];
		charges[0] = new Charge(1, 0, 1);
		for (int i = 1; i < charges.length; i++) {
			double x, y, c;
			x = Math.random() * 10;
			y = Math.random() * 10;
			c = Math.random() * 10;
			charges[i] = new Charge(x, y, c);
		}
		long time = System.currentTimeMillis();
		for (int i = 0; i < charges.length; i++) {
			result[i] = charges[i].calculateForce(charges);
		}
		long endtime = System.currentTimeMillis();
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		System.out.println("Took " + String.valueOf(endtime-time) + " ms.");
	}

}
