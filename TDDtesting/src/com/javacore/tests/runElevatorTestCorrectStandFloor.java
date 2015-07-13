package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
/** Check for floor standing correctness */
public class runElevatorTestCorrectStandFloor {

	@Test
	public void test() throws InterruptedException {
		ElevatorController test = new ElevatorController();
		int output = test.runElevatorGetFloor();
		assertEquals(2, output);
	}

}
