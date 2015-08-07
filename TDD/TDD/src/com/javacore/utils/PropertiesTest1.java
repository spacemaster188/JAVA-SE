package com.javacore.utils;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

public class PropertiesTest1 {
private final String PARSED_PROPERTIES = "6;4;4;1";
	@Test
	public void buildValidPropertiesFromFile() throws IOException {
		Properties properties = new Properties();
		properties.setConfigFile("resources/validConfig.properties");
		properties.getPropertiesFromFile();

		assertThat("All properties from file must be correct :",
				properties.toString(),  equalTo(PARSED_PROPERTIES));
	}

}
