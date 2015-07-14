package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Checking final report by empty of ElevatorContainer */
public class runPassengerControllerTestElevatorContainerForEmpty {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		boolean output = test.isElevatorContainersEmpty();
		assertEquals(false, output);
	}

}
