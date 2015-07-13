package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Checking final report by overall arrived passengers */
public class runPassengerControllerTestAllPassengersArrived {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		boolean output = test.isAllPassengersArrived();
		assertEquals(false, output);
	}

}
