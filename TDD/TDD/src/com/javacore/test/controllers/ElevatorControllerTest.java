package com.javacore.test.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.javacore.controllers.ElevatorController;
import com.javacore.utils.CurrentWay;

public class ElevatorControllerTest {
	private final int ZERO_MOVE_DELAY = 0;
	private final boolean ELEVATOR_START_FLAG = true;
	
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

	@Test
	public void movesAnElevatorToChangeDirection() throws InterruptedException,	IOException {
		final String WAY_UP = CurrentWay.UP.toString();
		final String WAY_DOWN = CurrentWay.DOWN.toString();
		String currentWay;
		
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
	
	@Test
	public void movesAnElevatorAllTheWayUpAndDown()	throws InterruptedException, IOException {
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
	
	@Test
	public void movesAnElevatorDownFromLastFloorToFirst() throws InterruptedException, IOException {
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
	
	public void movesAnElevatorToDistanceOfOneFloor(ElevatorController controller) throws InterruptedException {
		controller.moveElevator();
		controller.moveElevator();
		controller.moveElevator();
	}
}
