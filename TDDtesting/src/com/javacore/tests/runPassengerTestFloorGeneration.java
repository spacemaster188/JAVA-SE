package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.entities.Passenger;
/** Check passenger's random start floor generation */
public class runPassengerTestFloorGeneration {

	@Test
	public void test() throws InterruptedException {
		Passenger test = new Passenger();
		boolean output = test.getRandomStartFloor(8);
		assertEquals(true, output);
	}

}
