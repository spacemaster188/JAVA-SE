package com.javacore.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
/** Check floor count by test floor */
public class runElevatorTestCorrectFloorCount {

	@Test
	public void test() throws InterruptedException {
		ElevatorController test = new ElevatorController();
		int output = test.runElevatorFloorCount();
		assertEquals(9, output);
	}

}
