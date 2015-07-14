package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.TransportationTask;
import com.javacore.entities.Passenger;
/** Check isReadyToEnter method of TransportationTask class */
public class runTransportationTaskTestIsReadyEnter {

	@Test
	public void test() throws InterruptedException {
		TransportationTask test = new TransportationTask(new Passenger());
		boolean output = test.isReadyToEnter();
		assertEquals(true, output);
	}

}
