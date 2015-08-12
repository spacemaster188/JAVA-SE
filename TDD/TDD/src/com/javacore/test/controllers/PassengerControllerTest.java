package com.javacore.test.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.containers.DispatchStoryContainer;
import com.javacore.controllers.PassengerController;
import com.javacore.controllers.TransportationTask;
import com.javacore.entities.Passenger;
import com.javacore.utils.CurrentWay;
import com.javacore.utils.PassengerConditions;

public class PassengerControllerTest {
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
	
	@Test
	public void fillElevatorContainerByPassengerAndCheckIsElevatorVacant() throws IOException {
		this.passengerController = new PassengerController();
		passengerController.organizePassengers();
		assertThat("Elevator capacity must be more than 1 :",
				passengerController.getElevatorCont()
								.getElevatorCapacity() > 1, is(true));
		passengerController.getElevatorCont().addTransportationTask(
				new TransportationTask(new Passenger(11), passengerController));
		assertThat("Elevator container must be vacant :",
				passengerController.isElevatorVacant(), is(true));
	}

	public boolean isElevatorContainerNotEmpty() {
		if (passengerController.getElevatorCont().getTransportationTaskLst()
				.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Test
	public void fillFloorByPassengerAndNotifyFloorPassengerToComingElevatorGoUp() throws IOException {
	final int ANY_BUILDING_FLOOR_COUNT = 11;
	final int START_PASSENGER_FLOOR = 1;
	final int END_PASSENGER_FLOOR = 2;
	PassengerController passengerController = new PassengerController();
	Passenger passenger = new Passenger(ANY_BUILDING_FLOOR_COUNT);
	passenger.setStartFloor(START_PASSENGER_FLOOR);
	passenger.setEndFloor(END_PASSENGER_FLOOR);
	TransportationTask transportationTask = new TransportationTask(passenger, passengerController);
	
	assertThat("Before notify passenger to go UP their state to enter elevator must be inactive :",
			transportationTask.isEnter(), is(false));
	transportationTask.setCurrentFloor(START_PASSENGER_FLOOR);
	transportationTask.setCurrWay(CurrentWay.UP);
	transportationTask.setEnter(true);
		assertThat("After notify passenger he must be ready to enter elevator :",
				transportationTask.isReadyToEnter() && transportationTask.isEnter(), is(true));
	}
	
	@Test
	public void fillArrivalStoryContainerByPassengerAndCheckIsArrivedForFinalServiceChecking()
			throws IOException {
		final int END_FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		Passenger passenger = new Passenger(12);
		TransportationTask transportationTask = new TransportationTask(
				passenger, passengerController);
		ArrivalStoryContainer container = new ArrivalStoryContainer(END_FLOOR);
		container.getTransportationTaskLst().add(transportationTask);
		passengerController.getArrivStorContLst().add(container);

		assertThat(
				"Passenger's condition is not COMPLETED and validation should fail :",
				passengerController.isArrivedPassengersVerified(), is(false));

		passenger.setTransportationState(PassengerConditions.COMPLETED);
		transportationTask.setEndFloor(END_FLOOR);

		assertThat(
				"Passenger's condition is COMPLETED, ArrivalStoryContainer floor is "
				+ "equal transportationTask end floor and validation should be ok :",
				passengerController.isArrivedPassengersVerified(), is(true));
	}
	
	@Test
	public void fillElevatorContainerByPassengersAndCheckIsElevatorContainersEmptyForFinalServiceChecking()
			throws IOException {
		final int FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		passengerController.organizePassengers();
		
		assertThat(
				"After arrive all passengers to the end point validation should be ok :",
				passengerController.isElevatorContainersEmpty(), is(true));
		
		Passenger passenger1 = new Passenger(FLOOR);
		TransportationTask transportationTask1 = new TransportationTask(
				passenger1, passengerController);
		passengerController.getElevatorCont().getTransportationTaskLst()
				.add(transportationTask1);

		assertThat(
				"After filling of any container by passenger validation should fail :",
				passengerController.isElevatorContainersEmpty(), is(false));

	}
	
	@Test
	public void fillDispatchStoryContainerByPassengersAndCheckisAllDispatchStoryContainersEmptyForFinalServiceChecking()
			throws IOException {
		final int FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		DispatchStoryContainer container = new DispatchStoryContainer(FLOOR);
		List <DispatchStoryContainer> lst = new ArrayList<DispatchStoryContainer>();
		lst.add(container);
		passengerController.setDispStorContLst(lst);
		
		assertThat(
				"Before filling by passenger the dispatchStory container validation should be ok :",
				passengerController.isAllDispatchStoryContainersEmpty(), is(true));
		
		Passenger passenger1 = new Passenger(FLOOR);
		TransportationTask transportationTask1 = new TransportationTask(
				passenger1, passengerController);
		passengerController.getDispStorContLst().get(0).addTransportationTask(transportationTask1);

		assertThat(
				"After filling  by passenger the dispatchStory container validation should fail :",
				passengerController.isAllDispatchStoryContainersEmpty(), is(false));

	}
	
	@Test
	public void fillArrivalStoryContainerByPassengerAndCheckisAllPassengersArrivedForFinalServiceChecking()
			throws IOException {
		final int FLOOR = 11;
		PassengerController passengerController = new PassengerController();
		ArrivalStoryContainer container = new ArrivalStoryContainer(FLOOR);
		List<ArrivalStoryContainer> lst = new ArrayList<ArrivalStoryContainer>();
		lst.add(container);
		passengerController.setArrivStorContLst(lst);
		passengerController.organizePassengers();                   // change passengers count to 16

		assertThat(
				"Before filling by passengers the arrival story container validation"
				+ " should be fail cause quantity of arrived passengers is not equal overall "
				+ "passengers quantity  :",
				passengerController.isAllPassengersArrived(), is(false));

		Passenger passenger1 = new Passenger(FLOOR);
		TransportationTask transportationTask = new TransportationTask(
				passenger1, passengerController);
		ArrivalStoryContainer arrivalContainer = passengerController.getArrivStorContLst().get(0);
		
		for(int i = 0;i < 16; i++) {                               //add 16 transportation tasks
			arrivalContainer.addTransportationTask(transportationTask);
		}

		assertThat(
				"After filling by passengers the arrival story container validation"
				+ " should be ok :",
				passengerController.isAllPassengersArrived(), is(true));

	}
}
