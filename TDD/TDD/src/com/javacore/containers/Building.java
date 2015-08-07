package com.javacore.containers;

import java.io.IOException;

import com.javacore.controllers.ElevatorController;

public class Building {
	private ElevatorController elevatorController;

	public Building() throws IOException, InterruptedException {
		this.elevatorController = new ElevatorController();
		elevatorController.launch();
	}

	public ElevatorController getElevatorController() {
		return elevatorController;
	}

}
