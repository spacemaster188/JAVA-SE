package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
/** Check by test floor */
public class runElevatorTest1 {

	@Test
	public void test() throws InterruptedException {
		ElevatorController test = new ElevatorController();
		int output = test.runElevatorGetFloor();
		assertEquals(2, output);
	}

}
