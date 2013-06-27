package es.udc.smunin.electricforce.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChargeTest {

	@Test
	public void test() {
		Charge q1 = new Charge(0, 0, 3);
		Charge q2 = new Charge(1, 0, 2);
		assertTrue(q1.calculateForce(q2) == 6 * 9e9);
		q1 = new Charge(1, 1, 3);
		q2 = new Charge(3, 2, 2);
		assertTrue(Math.abs(q1.calculateForce(q2) - (6 * 9e9 / 5)) < 1);
	}

}
