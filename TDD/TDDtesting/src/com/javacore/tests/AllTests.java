package com.javacore.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ runCheckLoggingTest.class,
		runElevatorTestCorrectFloorCount.class,
		runElevatorTestCorrectStandFloor.class,
		runElevatorTestCorrectWay.class,
		runPassengerControllerTestAllPassengersArrived.class,
		runPassengerControllerTestArrivedPassengersVerify.class,
		runPassengerControllerTestConditionsStoppingElevatorVerify.class,
		runPassengerControllerTestCreationContainers2.class,
		runPassengerControllerTestCreationContainersPassengers.class,
		runPassengerControllerTestCreationContaners.class,
		runPassengerControllerTestDispatcherContainersForEmpty.class,
		runPassengerControllerTestElevatorContainerForEmpty.class,
		runPassengerControllerTestElevatorVacancy.class,
		runPassengerTestFloorGeneration.class,
		runPassengerTestFloorGeneration2.class,
		runPassengerTestPassengersIdGeneration.class,
		runPropertiesTestReadProperties.class,
		runPropertiesTestReadProperties2.class,
		runPropertiesTestReadProperties3.class,
		runTransportationTaskTestCompletedState.class,
		runTransportationTaskTestIsReadyEnter.class,
		runTransportationTaskTestIsReadyGetout.class,
		runTransportationTaskTestPassengerStateChanging.class })
public class AllTests {

}
