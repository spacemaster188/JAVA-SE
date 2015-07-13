package com.javacore.interfaces;
/** Main interface for containers */
import java.util.List;

import com.javacore.controllers.TransportationTask;

public interface Container {

    public void addTransportationTask(TransportationTask transportationTask);

    public List<TransportationTask> getTransportationTaskLst();

    public void removeTransportationTask(TransportationTask transportationTask);

}
