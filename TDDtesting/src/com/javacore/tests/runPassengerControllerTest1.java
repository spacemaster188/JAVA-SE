package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Check organization creation of passengers and containers in PassengerController class */
public class runPassengerControllerTest1 {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		int output = test.organizePassengers();
		assertEquals(20, output);
	}

}
