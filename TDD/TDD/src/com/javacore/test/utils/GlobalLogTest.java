package com.javacore.test.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.javacore.utils.GlobalLog;

public class GlobalLogTest {
	@Test
	public void addTwoServiceMessagesForSwingGuiWindowShow() {
		final String FIRST_MESSAGE = "xx";
		final String SECOND_MESSAGE = "ZZ";
		final String MESSAGES_SAVED_IN_MEMORY = "xx\nZZ\n";
		GlobalLog.addLog(FIRST_MESSAGE);

		GlobalLog.addLog(SECOND_MESSAGE);

		assertThat(
				"Messages saved in memory StringBuilder for Swing GUI must be correctly saved:",
				GlobalLog.getLog(), equalTo(MESSAGES_SAVED_IN_MEMORY));

	}
}
