package com.javacore.containers;
/** Container class for location passengers in elevator*/
import java.util.ArrayList;
import java.util.List;

import com.javacore.controllers.TransportationTask;
import com.javacore.interfaces.Container;

public class ElevatorContainer implements Container {
private int elevatorCapacity;
private List<TransportationTask> transportationTaskLst;

public ElevatorContainer(final int elevatorCapacity) {
    super();
    this.elevatorCapacity = elevatorCapacity;
    this.transportationTaskLst = new ArrayList<TransportationTask>();
}

@Override
public void addTransportationTask(final TransportationTask transportationTask) {
    transportationTaskLst.add(transportationTask);
}

@Override
public List<TransportationTask> getTransportationTaskLst() {
    return transportationTaskLst;
}

@Override
public void removeTransportationTask(final TransportationTask transportationTask) {
    transportationTaskLst.remove(transportationTask);
}

public final int getElevatorCapacity() {
    return elevatorCapacity;
}

}
