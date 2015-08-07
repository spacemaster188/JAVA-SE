package com.javacore.entities;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class PassengerTest2 {

	@Test
	public void createNewPassengerAndCheckGeneratedId() {
		final int FLOOR_COUNT = 10;
		Passenger newPassenger1 = new Passenger(FLOOR_COUNT);
		final int FIRST_CREATED_PASSENGER_GENERATED_ID = 1;
		Passenger newPassenger2 = new Passenger(FLOOR_COUNT);
		final int SECOND_CREATED_PASSENGER_GENERATED_ID = 2;
		assertThat(
				"First created passenger's ID must be 1:",
				newPassenger1.getPassengerID(), is(FIRST_CREATED_PASSENGER_GENERATED_ID));
		assertThat(
				"Second created passenger's ID must be 2:",
				newPassenger2.getPassengerID(), is(SECOND_CREATED_PASSENGER_GENERATED_ID));
	}
	
}
