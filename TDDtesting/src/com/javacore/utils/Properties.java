package com.javacore.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Properties {
private static int floorNumber;
private static int elevatorCapacity;
private static int passengerCount;
private static int animationBoost;
final static String REGEX = ";";

public Properties() throws IOException {
	super();
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

public String getPropertiesFromFile(String config) throws IOException{
	 BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader(config));
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
	 reader.close();
	} catch (FileNotFoundException e1) {
		return  String.valueOf(0 + REGEX + 0 + REGEX + 0 + REGEX + 0 + REGEX);
	}
	 return  String.valueOf(floorNumber + REGEX + elevatorCapacity + REGEX + passengerCount + REGEX + animationBoost + REGEX);
}

private int getValueByKey(String lineStr){
	     String[]tmpMas = lineStr.trim().split(Constants.REGEX);
	     try {
	    	 Integer.parseInt(tmpMas[1].trim());
		 } catch (Exception e) {
			return 0;
		 }
	     return Integer.parseInt(tmpMas[1].trim());
}

}
