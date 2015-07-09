package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import com.javacore.controllers.TransportationTask;
import com.javacore.entities.Passenger;
/** Check changing of PassengerState by TransportationTask's thread */
public class runTransportationTaskTest4 {

	@Test
	public void test() throws InterruptedException {
		TransportationTask test = new TransportationTask(new Passenger());
		boolean output = test.checkChangePassengerConditionFromAnotherThread();
		assertEquals(true, output);
	}

}
