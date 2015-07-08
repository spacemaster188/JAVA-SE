package com.javacore.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
import com.javacore.utils.currentWay;
/** Check for correct way by test floor */
public class runElevatorTest2 {

	@Test
	public void test() throws InterruptedException {
		ElevatorController test = new ElevatorController();
		Enum<currentWay> output = test.runElevatorGetWay();
		assertEquals(currentWay.DOWN, output);
	}

}
