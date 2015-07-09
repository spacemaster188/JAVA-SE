package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.entities.Passenger;
/** Check passenger's random end floor generation */
public class runPassengerTest2 {

	@Test
	public void test() throws InterruptedException {
		Passenger test = new Passenger();
		boolean output = test.getRandomEndFloor(10);
		assertEquals(true, output);
	}

}
