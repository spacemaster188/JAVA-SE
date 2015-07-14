package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Checking final report of conditions for elevator stopping */
public class runPassengerControllerTestConditionsStoppingElevatorVerify {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		boolean output = test.isConditionsStoppingElevatoraVerified();
		assertEquals(false, output);
	}

}
