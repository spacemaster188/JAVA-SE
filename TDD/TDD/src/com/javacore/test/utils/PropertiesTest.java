package com.javacore.test.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.javacore.utils.Properties;

public class PropertiesTest {
	@Test
	public void buildValidPropertiesFromFile() throws IOException {
		final String PARSED_PROPERTIES = "6;4;4;1";
		Properties properties = new Properties();
		properties.setConfigFile("resources/validConfig.properties");
		properties.getPropertiesFromFile();

		assertThat("All properties from file must be correct :",
				properties.toString(),  equalTo(PARSED_PROPERTIES));
	}
	
	@Test
	public void buildNoFileProperties() throws IOException {
		final String PARSED_PROPERTIES = "0;0;0;0";
		Properties properties = new Properties();
		properties.setConfigFile("noFile.properties");
		properties.getPropertiesFromFile();

		assertThat("All properties from file must be equal to zero :",
				properties.toString(),  equalTo(PARSED_PROPERTIES));
	}
	
	@Test
	public void buildIncorrectPropertiesFromFile() throws IOException {
		final String PARSED_PROPERTIES = "6;0;0;0";
		Properties properties = new Properties();
		properties.setConfigFile("resources/incorrectConfig.properties");
		properties.getPropertiesFromFile();

		assertThat("Only first property from the file is correct :",
				properties.toString(),  equalTo(PARSED_PROPERTIES));
	}
}
