package com.javacore.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.utils.Properties;
/** Check no file properties */
public class runPropertiesTestReadProperties3 {

	@Test
	public void test() throws InterruptedException, IOException {
		Properties test = new Properties();
		String output = test.getPropertiesFromFile("noFile.properties");
		assertEquals("0;0;0;0;", output);
	}
}
