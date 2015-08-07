package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

import com.javacore.utils.CurrentWay;

public class ElevatorControllerTest2 {
	private final int ZERO_MOVE_DELAY = 0;
	private final boolean ELEVATOR_START_FLAG = true;
	private final String WAY_UP = CurrentWay.UP.toString();
	private final String WAY_DOWN = CurrentWay.DOWN.toString();
	private String currentWay;

	@Test
	public void movesAnElevatorToChangeDirection() throws InterruptedException,
			IOException {
		ElevatorController controller = new ElevatorController();
		controller.setMoveDelay(ZERO_MOVE_DELAY);
		controller.setCurrentFloor(controller.getFloorNumber() - 1);
		controller.setElevatorStartFlag(ELEVATOR_START_FLAG);
		currentWay = controller.getCurrWay().toString();
		assertThat("Current Elevator way must be UP:", currentWay,
				equalTo(WAY_UP));
		movesAnElevatorToDistanceOfOneFloor(controller);
		movesAnElevatorToDistanceOfOneFloor(controller);
		currentWay = controller.getCurrWay().toString();
		assertThat("Current Elevator way must be DOWN:", currentWay,
				equalTo(WAY_DOWN));
	}

	public void movesAnElevatorToDistanceOfOneFloor(
			ElevatorController controller) throws InterruptedException {
		controller.moveElevator();
		controller.moveElevator();
		controller.moveElevator();
	}
}
