package com.javacore.containers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

public class BuildingTest1 {
	private Building building;

	@Test
	public void buildElevatorComponents() throws InterruptedException,
			IOException {
		this.building = new Building();
		assertThat("All important components for Elevator must be built :",
				isBuildingMainElevatorComponentsOk(), is(true));
	}

	public boolean isBuildingMainElevatorComponentsOk() {
		if (building.getElevatorController() != null
				&& building.getElevatorController().getPassengersController() != null
				&& building.getElevatorController().getPassengersController()
						.getProperties() != null) {
			return true;
		}
		return false;
	}

}
