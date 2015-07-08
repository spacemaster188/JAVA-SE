package com.javacore.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import com.javacore.utils.Properties;
/** Check correct incoming properties from config.properties */
public class runPropertiesTest1 {

	@Test
	public void test() throws InterruptedException, IOException {
		Properties test = new Properties();
		String output = test.getPropertiesFromFile("resources/config.properties");
		assertEquals("6;4;4;1;", output);
	}

}
