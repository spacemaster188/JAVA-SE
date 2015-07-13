package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Checking final report by overall arrived passengers verify */
public class runPassengerControllerTestArrivedPassengersVerify {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		boolean output = test.isArrivedPassengersVerified();
		assertEquals(false, output);
	}

}
