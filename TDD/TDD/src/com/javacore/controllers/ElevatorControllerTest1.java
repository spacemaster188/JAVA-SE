package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;


public class ElevatorControllerTest1 {
	private final boolean ELEVATOR_START_FLAG = true;
	private final int ZERO_MOVE_DELAY = 0;

	@Test
	public void movesAnElevatorUpWhileUpDirection() throws InterruptedException, IOException {   		
		ElevatorController controller = new ElevatorController();
		controller.setMoveDelay(ZERO_MOVE_DELAY);
		controller.setElevatorStartFlag(ELEVATOR_START_FLAG);
		final int START_FLOOR_NUMBER = controller.getCurrentFloor();
		final int OVERALL_FLOOR_NUMBER = controller.getFloorNumber();
		for (int i = START_FLOOR_NUMBER; i < OVERALL_FLOOR_NUMBER; i++) {
			assertThat("Current floor must be one up after Elevator moving 3 times:", controller.getCurrentFloor(), is(i));
			movesAnElevatorToDistanceOfOneFloor(controller);
			}

		}

	public void movesAnElevatorToDistanceOfOneFloor(ElevatorController controller) throws InterruptedException {
		controller.moveElevator();
		controller.moveElevator();
		controller.moveElevator();
	}
	
}
