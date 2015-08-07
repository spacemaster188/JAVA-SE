package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.utils.CurrentWay;

public class ElevatorControllerTest4 {
	private final boolean ELEVATOR_START_FLAG = true;
	private final int ZERO_MOVE_DELAY = 0;

	@Test
	public void movesAnElevatorDownFromLastFloorToFirst()
			throws InterruptedException, IOException {
		ElevatorController controller = new ElevatorController();
		controller.setMoveDelay(ZERO_MOVE_DELAY);
		controller.setElevatorStartFlag(ELEVATOR_START_FLAG);
		controller.setFloorNumber(6);
		controller.setCurrentFloor(6);
		controller.setCurrWay(CurrentWay.DOWN);
		final int START_FLOOR_NUMBER = controller.getCurrentFloor();
		final int FIRST_FLOOR_NUMBER = 1;
		for (int i = START_FLOOR_NUMBER; i > 0; i--) {
			movesAnElevatorToDistanceOfOneFloor(controller);
		}
		assertThat(
				"Covered floor count after coming to first floor must be 5:",
				START_FLOOR_NUMBER - FIRST_FLOOR_NUMBER, is(5));

	}

	public void movesAnElevatorToDistanceOfOneFloor(
			ElevatorController controller) throws InterruptedException {
		controller.moveElevator();
		controller.moveElevator();
		controller.moveElevator();
	}

}
