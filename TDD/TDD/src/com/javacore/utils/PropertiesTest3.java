package com.javacore.utils;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

public class PropertiesTest3 {
	private final String PARSED_PROPERTIES = "6;0;0;0";

	@Test
	public void buildIncorrectPropertiesFromFile() throws IOException {
		Properties properties = new Properties();
		properties.setConfigFile("incorrectConfig.properties");
		properties.getPropertiesFromFile();

		assertThat("Only first property from the file is correct :",
				properties.toString(),  equalTo(PARSED_PROPERTIES));
	}

}
