package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import com.javacore.controllers.PassengerController;
/** Check elevator's vacancy in PassengerController class */
public class runPassengerControllerTest4 {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		boolean output = test.isElevatorVacant();
		assertEquals(false, output);
	}

}
