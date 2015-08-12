package com.javacore.test.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.javacore.entities.Passenger;

public class PassengerTest {
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
	
	@Test
	public void createNewPassengerAndCheckGeneratedId() {
		final int FLOOR_COUNT = 10;
		Passenger newPassenger1 = new Passenger(FLOOR_COUNT);
		int FIRST_CREATED_PASSENGER_GENERATED_ID = newPassenger1.getPassengerID();
		Passenger newPassenger2 = new Passenger(FLOOR_COUNT);
		final int SECOND_CREATED_PASSENGER_GENERATED_ID = FIRST_CREATED_PASSENGER_GENERATED_ID + 1;
		assertThat(
				"Second created passenger's ID must be 1 more than first created passenger's ID:",
				newPassenger2.getPassengerID(), is(SECOND_CREATED_PASSENGER_GENERATED_ID));
	}
	
}
