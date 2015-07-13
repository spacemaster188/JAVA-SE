package com.javacore.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
import com.javacore.utils.currentWay;
/** Check for correctness way changing */
public class runElevatorTestCorrectWay {

	@Test
	public void test() throws InterruptedException {
		ElevatorController test = new ElevatorController();
		Enum<currentWay> output = test.runElevatorGetWay();
		assertEquals(currentWay.DOWN, output);
	}

}
