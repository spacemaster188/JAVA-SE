package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

public class ElevatorControllerTest3 {
	private final int ZERO_MOVE_DELAY = 0;
	private final boolean ELEVATOR_START_FLAG = true;

	@Test
	public void movesAnElevatorAllTheWayUpAndDown()
			throws InterruptedException, IOException {
		ElevatorController controller = new ElevatorController();
		controller.setMoveDelay(ZERO_MOVE_DELAY);
		assertThat("Elevator's floor number must be > 1:",
				controller.getFloorNumber() > 1, is(true));
		controller.setCurrentFloor(2);
		controller.setElevatorStartFlag(ELEVATOR_START_FLAG);

		while (!(controller.getCurrentFloor() == 1)) {
			movesAnElevatorToDistanceOfOneFloor(controller);
		}

		assertThat("Elevator must arrive to the first floor :",
				controller.getCurrentFloor() == 1, is(true));
	}

	public void movesAnElevatorToDistanceOfOneFloor(
			ElevatorController controller) throws InterruptedException {
		controller.moveElevator();
		controller.moveElevator();
		controller.moveElevator();
	}
}
