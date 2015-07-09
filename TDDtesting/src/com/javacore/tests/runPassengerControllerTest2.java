package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Check creation of containers in PassengerController class */
public class runPassengerControllerTest2 {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		boolean output = test.hasNoTransportationTasks();
		assertEquals(false, output);
	}

}
