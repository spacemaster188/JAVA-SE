package com.javacore.test.containers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.javacore.containers.Building;

public class BuildingTest {
	@Test
	public void buildElevatorComponents() throws InterruptedException, IOException {
        Building building = new Building();
		assertThat("All important components for Elevator must be built :",
				isBuildingMainElevatorComponentsOk(building), is(true));
	}

	public boolean isBuildingMainElevatorComponentsOk(Building building) {
		if (building.getElevatorController() != null
				&& building.getElevatorController().getPassengersController() != null
				&& building.getElevatorController().getPassengersController()
						.getProperties() != null) {
			return true;
		}
		return false;
	}
}
