package es.udc.smunin.electricforce.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the correctness of the electric force calculation.
 * 
 * @author Santiago Mun’n <santimunin@gmail.com>
 * 
 */
public class ChargeTest {

	private final static double ERROR = 1e-5;

	@Test
	public void test() {
		Charge q1 = new Charge(0, 0, 3);
		Charge q2 = new Charge(1, 0, 2);

		assertTrue(q1.calculateForce(q2) == 6 * 9e9);
		q1 = new Charge(1, 1, 3);
		q2 = new Charge(3, 2, 2);
		assertTrue(Math.abs(q1.calculateForce(q2) - (6 * 9e9 / 5)) < ERROR);
		Charge q3 = new Charge(1, 2, 5);
		Charge[] charges = new Charge[2];
		q1 = new Charge(0, 2, 3);
		q2 = new Charge(2, 2, 2);
		charges[0] = q1;
		charges[1] = q2;
		assertTrue(Math.abs(q3.calculateForce(charges)
				- (q1.getValue() * q3.getValue() * 9e9 + q2.getValue()
						* q3.getValue() * 9e9)) < ERROR);
	}
}
