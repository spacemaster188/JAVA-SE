package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.TransportationTask;
import com.javacore.entities.Passenger;
/** Check IsCompletedState method of TransportationTask class */
public class runTransportationTaskTestCompletedState {

	@Test
	public void test() throws InterruptedException {
		TransportationTask test = new TransportationTask(new Passenger());
		boolean output = test.checkIsCompletedState();
		assertEquals(true, output);
	}

}
