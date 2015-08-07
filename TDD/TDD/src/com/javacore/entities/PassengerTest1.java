package com.javacore.entities;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class PassengerTest1 {

	@Test
	public void createNewPassengerAndCheckStartEndFloorGeneration() {
		final int FLOOR_COUNT = 8;
		Passenger newPassenger = new Passenger(FLOOR_COUNT);

		assertThat(
				"Start floor number must be greater than zero and no greater than floor count:",
				newPassenger.getStartFloor() > 0 && newPassenger.getStartFloor() <= FLOOR_COUNT, is(true));
		assertThat(
				"End floor number must be greater than zero and no greater than floor count",
				newPassenger.getEndFloor() > 0 && newPassenger.getEndFloor() <= FLOOR_COUNT, is(true));
	}
	
}
