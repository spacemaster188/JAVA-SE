package com.javacore.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/** Read configuration variables from file */
public class Properties {
private String configFile;
private int floorNumber;
private int elevatorCapacity;
private int passengerCount;
private int animationBoost;

public Properties() throws IOException {
	this.configFile = Constants.CONFIG;
    getPropertiesFromFile();
}

public int getFloorNumber() {
	return floorNumber;
}


public int getElevatorCapacity() {
	return elevatorCapacity;
}


public int getPassengerCount() {
	return passengerCount;
}


public int getAnimationBoost() {
	return animationBoost;
}

public void getPropertiesFromFile() throws IOException {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(configFile));
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
	} catch (FileNotFoundException ex) {
		floorNumber = 0;
		elevatorCapacity = 0;
		passengerCount = 0;
		animationBoost = 0;
	}
}

private int getValueByKey(final String lineStr) {
    String[]tmpMas = lineStr.trim().split(Constants.REGEX);
    try {
        Integer.parseInt(tmpMas[1].trim());
        } catch (Exception e) {
            return 0;
            }
    return Integer.parseInt(tmpMas[1].trim());
}

public void setConfigFile(String configFile) {
	this.configFile = configFile;
}

@Override
public String toString() {
	return floorNumber + ";"
			+ elevatorCapacity + ";" + passengerCount
			+ ";" + animationBoost;
}

}
