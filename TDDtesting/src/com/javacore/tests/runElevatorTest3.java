package com.javacore.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
/** Check floor_count by test floor */
public class runElevatorTest3 {

	@Test
	public void test() throws InterruptedException {
		ElevatorController test = new ElevatorController();
		int output = test.runElevatorFloorCount();
		assertEquals(9, output);
	}

}
