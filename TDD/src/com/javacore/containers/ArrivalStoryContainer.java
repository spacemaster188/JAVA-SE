package com.javacore.containers;

import java.util.ArrayList;
import java.util.List;

import com.javacore.controllers.TransportationTask;
import com.javacore.interfaces.Container;

public class ArrivalStoryContainer implements Container {
	private int floor;
	private List <TransportationTask> transpTaskLst;
	
	public ArrivalStoryContainer(int floor) {
		super();
		this.floor = floor;
		transpTaskLst = new ArrayList<TransportationTask>();
	}
	
	public int getFloor() {
		return floor;
	}

	@Override
	public void addTransportationTask(TransportationTask transportationTask) {
		transpTaskLst.add(transportationTask);
		
	}

	@Override
	public List<TransportationTask> getTransportationTaskLst() {
		return transpTaskLst;
	}

	@Override
	public void removeTransportationTask(TransportationTask transportationTask) {
		transpTaskLst.remove(transportationTask);
	}
	
}
