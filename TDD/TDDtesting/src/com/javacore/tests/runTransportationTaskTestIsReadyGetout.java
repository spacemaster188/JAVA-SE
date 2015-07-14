package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.TransportationTask;
import com.javacore.entities.Passenger;
/** Check getOutReady method of TransportationTask class */
public class runTransportationTaskTestIsReadyGetout {

	@Test
	public void test() throws InterruptedException {
		TransportationTask test = new TransportationTask(new Passenger());
		boolean output = test.checkIsGetOutNotify();
		assertEquals(false, output);
	}

}
