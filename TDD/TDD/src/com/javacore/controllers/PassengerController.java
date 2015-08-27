package com.javacore.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.containers.DispatchStoryContainer;
import com.javacore.containers.ElevatorContainer;
import com.javacore.entities.Passenger;
import com.javacore.utils.Constants;
import com.javacore.utils.GlobalLog;
import com.javacore.utils.LoggingConstants;
import com.javacore.utils.PassengerConditions;
import com.javacore.utils.Properties;
import com.javacore.utils.CurrentWay;
/** Service class for control containers and passengers */
public class PassengerController {
	private Properties properties;
    private volatile boolean isWaiting = false;
    private int floorNumber;
    private int passengerCount;
    private List<DispatchStoryContainer> dispStorContLst;
    private List<ArrivalStoryContainer> arrivStorContLst;
    private ElevatorContainer elevatorCont;
    static final Logger logger = Logger.getLogger("ElevatorController.class");

    public PassengerController() throws IOException {
        this.properties =  Properties.getProperties();
        this.floorNumber = properties.getFloorNumber();
        this.passengerCount = properties.getPassengerCount();
        this.dispStorContLst = new ArrayList<DispatchStoryContainer>();
        this.arrivStorContLst = new ArrayList<ArrivalStoryContainer>();
        this.elevatorCont = new ElevatorContainer(properties.getElevatorCapacity());
        }
    /** Build DispatchStoryContainer, ArrivalStoryContainer, TransportatinTasks lists */
    public final void organizePassengers() {
        for (int i = 1; i <= floorNumber; i++) {
            dispStorContLst.add(new DispatchStoryContainer(i));
            arrivStorContLst.add(new ArrivalStoryContainer(i));
            }
        for (int i = 0; i < passengerCount; i++) {
            TransportationTask tt = new TransportationTask(new Passenger(floorNumber), this);
            for (DispatchStoryContainer dispatchStroryContainer : dispStorContLst) {
                if (dispatchStroryContainer.getFloor() == tt.getStartFloor()) {
                    tt.setContainer(dispatchStroryContainer);
                    tt.getPassenger().setTransportationState(PassengerConditions.IN_PROGRESS);
                    dispatchStroryContainer.addTransportationTask(tt);
                    tt.startThread();
                    }
                }
            }
        }

    public final ElevatorContainer getElevatorCont() {
        return elevatorCont;
        }

    public final DispatchStoryContainer getDispatchStoryContainerByFloor(final int floor) {
        for (DispatchStoryContainer dispatchStoryContainer : dispStorContLst) {
            if (dispatchStoryContainer.getFloor() == floor) {
                return dispatchStoryContainer;
                }
            }
        return null;
        }

    public final ArrivalStoryContainer getArrivalStoryContainerByFloor(final int floor) {
        for (ArrivalStoryContainer arrivalStoryContainer : arrivStorContLst) {
            if (arrivalStoryContainer.getFloor() == floor) {
                return arrivalStoryContainer;
                }
            }
        return null;
        }

    public final boolean hasNoTransportationTasks() {
        boolean isNoTransportationTasks = true;
        for (DispatchStoryContainer cont : dispStorContLst) {
            if (cont.getTransportationTaskLst().size() > 0) {
                isNoTransportationTasks = false;
                }
            }
        if (elevatorCont.getTransportationTaskLst().size() > 0) {
            isNoTransportationTasks = false;
            }
        return isNoTransportationTasks;
        }

    /**  Verify conditions for elevator stopping  */
    public final void isConditionsStoppingElevatoraVerified() {
        if (isAllPassengersArrived() && isAllDispatchStoryContainersEmpty()
                && isElevatorContainersEmpty() && isArrivedPassengersVerified()) {
            logger.info(LoggingConstants.PROCESS_VALIDATION_OK.getConstant());
            } else {
            logger.info(LoggingConstants.PROCESS_VALIDATION_FAIL.getConstant());
            }
        }

    public final boolean isAllPassengersArrived() {
        int arrivedPassengersCount = 0;
        for (ArrivalStoryContainer arrivalStoryContainer : arrivStorContLst) {
            arrivedPassengersCount += arrivalStoryContainer.getTransportationTaskLst().size();
            }
        if (passengerCount == arrivedPassengersCount) {
            logger.info(LoggingConstants.ALL_PASSENGERS_ARRIVED.getConstant());
            return true;
            }
        logger.info(LoggingConstants.ALL_PASSENGERS_NOT_ARRIVED.getConstant());
        return false;
        }

    public final boolean isAllDispatchStoryContainersEmpty() {
        int dispatchStorPassengersCount = 0;
        for (DispatchStoryContainer containerElement : dispStorContLst) {
            dispatchStorPassengersCount += containerElement.getTransportationTaskLst().size();
            }
        if (dispatchStorPassengersCount == 0) {
            logger.info(LoggingConstants.DISPATCH_CONTAINERS_EMPTY.getConstant());
            return true;
            }
        logger.info(LoggingConstants.DISPATCH_CONTAINERS_NOT_EMPTY.getConstant());
        return false;
        }

    public final boolean isElevatorContainersEmpty() {
        if (elevatorCont.getTransportationTaskLst().size() == 0) {
            logger.info(LoggingConstants.ELEVATOR_CONTAINER_EMPTY.getConstant());
            return true;
            }
        logger.info(LoggingConstants.ELEVATOR_CONTAINER_NOT_EMPTY.getConstant());
        return false;
        }

    public final boolean isArrivedPassengersVerified() {
        boolean verified = true;
        for (ArrivalStoryContainer containerElement : arrivStorContLst) {
            List<TransportationTask> transpTaskLst = containerElement.getTransportationTaskLst();
            for (TransportationTask tt : transpTaskLst) {
                if (!tt.getPassenger().getTransportationState().equals(PassengerConditions.COMPLETED)
                        || !(tt.getEndFloor() == containerElement.getFloor())) {
                    verified = false;
                    }
                }
            }
        if (verified) {
            logger.info(LoggingConstants.ARRIVED_PASSENGERS_PASS.getConstant());
            return true;
            }
        logger.info(LoggingConstants.ARRIVED_PASSENGERS_FAIL.getConstant());
        return false;
        }

    public final void terminateTransportationTasks() {
        for (DispatchStoryContainer cont : dispStorContLst) {
            List<TransportationTask> lst = cont.getTransportationTaskLst();
            for (TransportationTask transportationTask : lst) {
                transportationTask.setAborted();
                proceedAnotherThread(transportationTask);
                }
            }
        List<TransportationTask> elevatorLst = elevatorCont.getTransportationTaskLst();
        for (TransportationTask transportationTask : elevatorLst) {
            transportationTask.setAborted();
            proceedAnotherThread(transportationTask);
            }
        }

    public final void notifyElevatorPassengersGetOut(final int currentFloor, final Enum<CurrentWay> currWay) {
        List<TransportationTask> transpTaskLst = elevatorCont.getTransportationTaskLst();
        for (TransportationTask transportationTask : transpTaskLst) {
            transportationTask.setCurrentFloor(currentFloor);
            transportationTask.setCurrWay(currWay);
            transportationTask.setGetOut(true);
            proceedAnotherThread(transportationTask);
            }
        }

    public void proceedAnotherThread(TransportationTask tt) {
        isWaiting = true;
        tt.notifyTransportationTask();
        while (isWaiting) {
            //wait
            }
        }

    public final void notifyIncomingPassengersToEnter(final int currentFloor, final Enum<CurrentWay> currWay) {
        for (DispatchStoryContainer dispatchStoryContainer : dispStorContLst) {
            if (dispatchStoryContainer.getFloor() == currentFloor) {
                List<TransportationTask> transpTaskLst = dispatchStoryContainer.getTransportationTaskLst();
                for (TransportationTask transportationTask : transpTaskLst) {
                    transportationTask.setCurrentFloor(currentFloor);
                    transportationTask.setCurrWay(currWay);
                    transportationTask.setEnter(true);
                    proceedAnotherThread(transportationTask);
                    }
                }
            }
        }

    public final void notifyPassengerController() {
        isWaiting = false;
        }

    public final void getOutPassengers(final int currentFloor) {
        ArrivalStoryContainer arrCont = getArrivalContainerByFloor(currentFloor);
        List<TransportationTask> elevatorTtLst = elevatorCont.getTransportationTaskLst();
        for (int i = elevatorTtLst.size() - 1; i >= 0; i--) {
            if (elevatorTtLst.get(i).getGetOutFloorFlag() != 0) {
                TransportationTask newTt = elevatorTtLst.get(i);
                elevatorTtLst.remove(i);
                newTt.setContainer(arrCont);
                newTt.setGetOutFloorFlag(0);
                proceedAnotherThread(newTt);
                arrCont.getTransportationTaskLst().add(newTt);
                logger.info(GlobalLog.addLog(Constants.DEBOARDING + newTt.getPassenger().getPassengerID()
                        + Constants.ON_STORY + currentFloor));
                }
            }
        }

    public final void takeOnboardPassengers(final int currentFloor) {
        for (DispatchStoryContainer cont : dispStorContLst) {
            if (cont.getFloor() == currentFloor) {
                List<TransportationTask> lst = cont.getTransportationTaskLst();
                for (int i = lst.size() - 1; i >= 0; i--) {
                    if (isElevatorVacant() && lst.get(i).getOnboardFloorFlag() != 0) {
                        TransportationTask newTt = lst.get(i);
                        lst.remove(i);
                        newTt.setContainer(elevatorCont);
                        newTt.setOnboardFloorFlag(0);
                        elevatorCont.getTransportationTaskLst().add(newTt);
                        logger.info(GlobalLog.addLog(Constants.BOARDING + newTt.getPassenger().getPassengerID()
                                + Constants.ON_STORY + currentFloor));
                        }
                    }
                }
            }
        }

    public final ArrivalStoryContainer getArrivalContainerByFloor(final int currentFloor) {
        for (ArrivalStoryContainer arrivalStoryContainer : arrivStorContLst) {
            if (arrivalStoryContainer.getFloor() == currentFloor) {
                return arrivalStoryContainer;
                }
            }
        return null;
        }

    public final boolean isElevatorVacant() {
        if (elevatorCont.getTransportationTaskLst().size() >= elevatorCont.getElevatorCapacity()) {
            return false;
            }
        return true;
        }
    
	public int getPassengerCount() {
		return passengerCount;
	}
	
	public List<DispatchStoryContainer> getDispStorContLst() {
		return dispStorContLst;
	}
	
	public void setDispStorContLst(List<DispatchStoryContainer> dispStorContLst) {
		this.dispStorContLst = dispStorContLst;
	}
	
	public List<ArrivalStoryContainer> getArrivStorContLst() {
		return arrivStorContLst;
	}
	
	public void setArrivStorContLst(List<ArrivalStoryContainer> arrivStorContLst) {
		this.arrivStorContLst = arrivStorContLst;
	}
	
	public Properties getProperties() {
		return properties;
	}

}
