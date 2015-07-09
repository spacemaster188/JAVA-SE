package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.containers.DispatchStoryContainer;
import com.javacore.controllers.PassengerController;
/** Check creation of containers in PassengerController class */
public class runPassengerControllerTest3 {

	@Test
	public void test() throws InterruptedException {
		PassengerController test = new PassengerController();
		DispatchStoryContainer output = test.getDispatchStoryContainerByFloor(3);
		assertEquals(null, output);
	}

}
