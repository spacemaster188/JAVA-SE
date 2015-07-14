package com.javacore.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.utils.Properties;
/** Check incorrect incoming properties from config2.properties */
public class runPropertiesTestReadProperties2 {

	@Test
	public void test() throws InterruptedException, IOException {
		Properties test = new Properties();
		String output = test.getPropertiesFromFile("resources/config2.properties");
		assertEquals("6;0;0;0;", output);
	}
}
