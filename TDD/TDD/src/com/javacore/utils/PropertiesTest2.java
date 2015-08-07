package com.javacore.utils;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

public class PropertiesTest2 {
	private final String PARSED_PROPERTIES = "0;0;0;0";

	@Test
	public void buildNoFileProperties() throws IOException {
		Properties properties = new Properties();
		properties.setConfigFile("noFile.properties");
		properties.getPropertiesFromFile();

		assertThat("All properties from file must be equal to zero :",
				properties.toString(),  equalTo(PARSED_PROPERTIES));
	}

}
