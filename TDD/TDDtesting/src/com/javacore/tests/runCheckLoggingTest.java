package com.javacore.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javacore.controllers.PassengerController;
/** Check Global logging */
public class runCheckLoggingTest {

	@Test
	public void test() {
		PassengerController test = new PassengerController();
		String output = test.checkLogging();
		assertEquals("asd\nasd\nasd\n", output);
	}
}
