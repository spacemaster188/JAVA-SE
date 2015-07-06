package com.javacore.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Properties {
private static int floorNumber;
private static int elevatorCapacity;
private static int passengerCount;
private static int animationBoost;

public Properties() throws IOException {
	super();
	getPropertiesFromFile();
}

public static int getFloorNumber() {
	return floorNumber;
}

public static void setFloorNumber(int floorNumber) {
	Properties.floorNumber = floorNumber;
}

public static int getElevatorCapacity() {
	return elevatorCapacity;
}

public static void setElevatorCapacity(int elevatorCapacity) {
	Properties.elevatorCapacity = elevatorCapacity;
}

public static int getPassengerCount() {
	return passengerCount;
}

public static void setPassengerCount(int passengerCount) {
	Properties.passengerCount = passengerCount;
}

public static int getAnimationBoost() {
	return animationBoost;
}

public static void setAnimationBoost(int animationBoost) {
	Properties.animationBoost = animationBoost;
}

private void getPropertiesFromFile() throws IOException{
	 List<String> strings = new ArrayList<String>();
	 BufferedReader reader = new BufferedReader(new FileReader(Constants.CONFIG));
	 String line;
	 while ((line = reader.readLine()) != null) {
		 if (line.contains(Constants.FLOOR_NUMBER)) {
			 floorNumber = getValueByKey(line);
		 }
		 if (line.contains(Constants.ELEVATOR_CAPACITY)) {
			 elevatorCapacity = getValueByKey(line);
		 }
		 if (line.contains(Constants.PASSENGER_COUNT)) {
			 passengerCount = getValueByKey(line);
		 }
		 if (line.contains(Constants.ANIMATION_BOOST)) {
			 animationBoost = getValueByKey(line);
		 }
	 }
}

private int getValueByKey(String lineStr){
	     String[]tmpMas = lineStr.trim().split(Constants.REGEX);
	     return Integer.parseInt(tmpMas[1].trim());
}

}
