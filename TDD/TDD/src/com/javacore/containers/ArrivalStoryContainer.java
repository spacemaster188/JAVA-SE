package com.javacore.containers;
/** Container class for location arrived passengers */
import java.util.ArrayList;
import java.util.List;

import com.javacore.controllers.TransportationTask;
import com.javacore.interfaces.Container;

public class ArrivalStoryContainer implements Container {
    private int floor;
    private List<TransportationTask> transpTaskLst;

    public ArrivalStoryContainer(final int floor) {
        this.floor = floor;
        transpTaskLst = new ArrayList<TransportationTask>();
    }

    public final int getFloor() {
       return floor;
    }

    @Override
    public void addTransportationTask(final TransportationTask transportationTask) {
       transpTaskLst.add(transportationTask);

    }

    @Override
    public List<TransportationTask> getTransportationTaskLst() {
       return transpTaskLst;
    }

    @Override
    public void removeTransportationTask(final TransportationTask transportationTask) {
       transpTaskLst.remove(transportationTask);
    }

}
