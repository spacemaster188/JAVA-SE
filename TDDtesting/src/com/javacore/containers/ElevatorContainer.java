package com.javacore.containers;

import java.util.ArrayList;
import java.util.List;

import com.javacore.controllers.TransportationTask;
import com.javacore.interfaces.Container;

public class ElevatorContainer implements Container {
private int elevatorCapacity;
private List <TransportationTask> transportationTaskLst;

public ElevatorContainer() {
	super();
	this.elevatorCapacity = 3;
	this.transportationTaskLst = new ArrayList<TransportationTask>();
}

@Override
public void addTransportationTask(TransportationTask transportationTask) {
	transportationTaskLst.add(transportationTask);	
}

@Override
public List<TransportationTask> getTransportationTaskLst() {
	return transportationTaskLst;
}

@Override
public void removeTransportationTask(TransportationTask transportationTask) {
	transportationTaskLst.remove(transportationTask);
}

public int getElevatorCapacity() {
	return elevatorCapacity;
}

}
