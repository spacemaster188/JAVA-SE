package com.javacore.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ runElevatorTest1.class, runElevatorTest2.class, runElevatorTest3.class })
public class AllTests {

}
