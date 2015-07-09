package com.javacore.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ runCheckLoggingTest.class, runElevatorTest1.class,
		runElevatorTest2.class, runElevatorTest3.class,
		runPassengerControllerTest1.class, runPassengerControllerTest2.class,
		runPassengerControllerTest3.class, runPassengerControllerTest4.class,
		runPassengerTest1.class, runPassengerTest2.class,
		runPassengerTest3.class, runPropertiesTest1.class,
		runPropertiesTest2.class, runPropertiesTest3.class,
		runTransportationTaskTest1.class, runTransportationTaskTest2.class,
		runTransportationTaskTest3.class, runTransportationTaskTest4.class })
public class AllTests {

}
