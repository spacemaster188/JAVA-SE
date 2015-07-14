package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Check passenger's ID generation */
public class runPassengerTestPassengersIdGeneration {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		int output = test.getLastPassengerId();
		assertEquals(2, output);
	}

}
