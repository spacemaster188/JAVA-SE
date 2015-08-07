package com.javacore.controllers;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.containers.DispatchStoryContainer;

public class PassengerControllerTest1 {
	private PassengerController passengerController;

	@Test
	public void orginizePassengersByContainers() throws IOException {
		this.passengerController = new PassengerController();
		passengerController.organizePassengers();
		assertThat("Number of passengers must be > 1:",
				passengerController.getPassengerCount() > 0, is(true));

		assertThat(
				"DispatchStoryContainer must be not empty and other containers"
						+ " must be empty :",
				isAnyDispatchStoryContainerNotEmpty()
						&& isAllArrivalStoryContainersEmpty()
						&& isElevatorContainerEmpty(), is(true));
	}

	public boolean isAnyDispatchStoryContainerNotEmpty() {
		boolean isNotEmpty = false;
		List<DispatchStoryContainer> lst = passengerController
				.getDispStorContLst();
		for (DispatchStoryContainer dispatchStoryContainerElement : lst) {
			if (dispatchStoryContainerElement.getTransportationTaskLst().size() > 0) {
				isNotEmpty = true;
				return isNotEmpty;
			}
		}
		return isNotEmpty;
	}

	public boolean isAllArrivalStoryContainersEmpty() {
		boolean isEmpty = true;
		List<ArrivalStoryContainer> lst = passengerController
				.getArrivStorContLst();
		for (ArrivalStoryContainer arrivalStoryContainerElement : lst) {
			if (arrivalStoryContainerElement.getTransportationTaskLst().size() > 0) {
				isEmpty = false;
				return isEmpty;
			}
		}
		return isEmpty;
	}

	public boolean isElevatorContainerEmpty() {
		return passengerController.getElevatorCont().getTransportationTaskLst()
				.size() > 0 ? false : true;
	}
}
