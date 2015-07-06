package com.javacore.controllers;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.javacore.containers.ArrivalStoryContainer;
import com.javacore.containers.DispatchStoryContainer;
import com.javacore.containers.ElevatorContainer;
import com.javacore.entities.Passenger;
import com.javacore.interfaces.TransportationTaskObserver;
import com.javacore.interfaces.TransportationTaskSubject;
import com.javacore.utils.Constants;
import com.javacore.utils.GlobalLog;
import com.javacore.utils.LoggingConstants;
import com.javacore.utils.PassengerConditions;
import com.javacore.utils.Properties;
import com.javacore.utils.currentWay;

public class PassengerController implements TransportationTaskSubject{
	private ArrayList<TransportationTaskObserver> observers;
	private int floorNumber;
	private int passengerCount;
    private List <DispatchStoryContainer> dispStorCont;
    private List <ArrivalStoryContainer> arrivStorCont;
    private List <TransportationTask> transpTaskList;
    private List <Passenger> passengerList;
    private ElevatorContainer elevatorCont;
    final static Logger logger = Logger.getLogger("ElevatorController.class");
    
	public PassengerController() {
		super();
		observers = new ArrayList<TransportationTaskObserver>();
		this.floorNumber = Properties.getFloorNumber();
		this.passengerCount = Properties.getPassengerCount();
		this.passengerList = new ArrayList<Passenger>();
		this.dispStorCont = new ArrayList<DispatchStoryContainer>();
		this.arrivStorCont = new ArrayList<ArrivalStoryContainer>();
		this.transpTaskList = new ArrayList<TransportationTask>();
		this.elevatorCont = new ElevatorContainer(Properties.getElevatorCapacity());
	}
	
	public void organizePassengers(){
		int tmpFloorNumber = Properties.getFloorNumber();
		for(int i = 1; i <= tmpFloorNumber; i++){
			dispStorCont.add(new DispatchStoryContainer(i));      //build DispatchStoryContainer list
			arrivStorCont.add(new ArrivalStoryContainer(i));      //build ArrivalStoryContainer list
		}
		for(int i = 0; i < passengerCount;i++){                   //build passenger list
			passengerList.add(new Passenger(floorNumber));
		}
		for (Passenger passenger : passengerList) {               //fill DispatchStoryContainers by passengers
			sortPassengersByStoryContainer(passenger);
		}
	}
	
	public void createTransportationTasks(){
		for (Passenger passenger : passengerList) {
			TransportationTask tt = new TransportationTask(passenger);
			register(tt);
			transpTaskList.add(tt);
		}
	}
	
	public TransportationTask getTransportationTaskByPassenger(Passenger passenger){
		for (TransportationTask transpTaskElement : transpTaskList) {
			if(passenger.equals(transpTaskElement.getPassenger())){
               return transpTaskElement;
			}
		}
		return null;
	}
	
	public void removeTransportationTaskByPassenger(Passenger passenger){
		if(transpTaskList.size() != 0){
			for (int i = 0; i < transpTaskList.size(); i++) {
				if(passenger.equals(transpTaskList.get(i).getPassenger())){
					transpTaskList.remove(i);
					break;
				}
			}
		}
	}
	
	public void sortPassengersByStoryContainer(Passenger passenger){
		for (DispatchStoryContainer listElement : dispStorCont) {
			if(passenger.getStartFloor() == listElement.getFloor()){
				listElement.addPassenger(passenger);
			}
		}
	}

	public ElevatorContainer getElevatorCont() {
		return elevatorCont;
	}
	
	public List<DispatchStoryContainer> getDispStorCont() {
		return dispStorCont;
	}

	public DispatchStoryContainer getDispatchStoryContainerByFloor(int floor) {
		for (DispatchStoryContainer dispatchStoryContainer : dispStorCont) {
			if(dispatchStoryContainer.getFloor() == floor){
				return dispatchStoryContainer;
			}
		}
		return null;
	}

	public ArrivalStoryContainer getArrivalStoryContainerByFloor(int floor) {
		for (ArrivalStoryContainer arrivalStoryContainer : arrivStorCont) {
			if(arrivalStoryContainer.getFloor() == floor){
				return arrivalStoryContainer;
			}
		}
		return null;
	}
	
	
	public boolean hasNoTransportationTasks() {
		if(transpTaskList.size() == 0){
			return true;
		}
		return false;
	}
	
	/**  Verify conditions for elevator stopping  */
	public void isConditionsStoppingElevatoraVerified() {
		if(isAllPassengersArrived() && isAllDispatchStoryContainersEmpty() && isElevatorContainersEmpty() && isArrivedPassengersVerified()){
			logger.info(LoggingConstants.PROCESS_VALIDATION_OK.getConstant());
		}
		else{
			logger.info(LoggingConstants.PROCESS_VALIDATION_FAIL.getConstant());
		}
	}
	
	public boolean isAllPassengersArrived() {
		int arrivedPassengersCount = 0;
		for (ArrivalStoryContainer arrivalStoryContainer : arrivStorCont) {
			arrivedPassengersCount += arrivalStoryContainer.getPassengerLst().size();
		}
		if(passengerCount == arrivedPassengersCount){
			logger.info(LoggingConstants.ALL_PASSENGERS_ARRIVED.getConstant());
			return true;
		}
		logger.info(LoggingConstants.ALL_PASSENGERS_NOT_ARRIVED.getConstant());
		return false;
	}
	
	public boolean isAllDispatchStoryContainersEmpty() {
		int dispatchStorPassengersCount = 0;
		for (DispatchStoryContainer containerElement : dispStorCont) {
			dispatchStorPassengersCount += containerElement.getPassengerLst().size();
		}
		if(dispatchStorPassengersCount == 0){
			logger.info(LoggingConstants.DISPATCH_CONTAINERS_EMPTY.getConstant());
			return true;
		}
		logger.info(LoggingConstants.DISPATCH_CONTAINERS_NOT_EMPTY.getConstant());
		return false;
	}
	
	public boolean isElevatorContainersEmpty() {
		if(elevatorCont.getPassengerLst().size() == 0){
			logger.info(LoggingConstants.ELEVATOR_CONTAINER_EMPTY.getConstant());
			return true;
		}
		logger.info(LoggingConstants.ELEVATOR_CONTAINER_NOT_EMPTY.getConstant());
		return false;
	}
	
	public boolean isArrivedPassengersVerified() {
		boolean verified = true;
		for (ArrivalStoryContainer containerElement : arrivStorCont) {
			List <Passenger> passLst = containerElement.getPassengerLst();
			for (Passenger passenger : passLst) {
				if(!passenger.getTransportationState().equals(PassengerConditions.COMPLETED) || !(passenger.getEndFloor() == containerElement.getFloor())){
					verified = false;
				}
			}
		}
		if(verified){
			logger.info(LoggingConstants.ARRIVED_PASSENGERS_PASS.getConstant());
			return true;
		}
		logger.info(LoggingConstants.ARRIVED_PASSENGERS_FAIL.getConstant());
		return false;
	}
	
	public void terminateTransportationTasks() {
		for (TransportationTask transpTaskElement : transpTaskList) {
			transpTaskElement.setAborted();
		}
		notifyObservers();
		for (int i = transpTaskList.size() - 1; i >= 0; i--) {
			transpTaskList.remove(i);
		}
	}
	
	public void notifyElevatorPassengers(int currentFloor){
		List<Passenger> passengerGetOutLst = elevatorCont.notifyPassengers(currentFloor);
		for (Passenger passenger : passengerGetOutLst) {
			getOutPassenger(passenger, currentFloor);
		}
	}
	
	public void notifyIncomingPassengers(int currentFloor, Enum<currentWay> currWay){
		DispatchStoryContainer dispStorContainer = getDispatchStoryContainerByFloor(currentFloor);
		if(dispStorContainer != null){
			List<Passenger> passengerIncomingLst = dispStorContainer.notifyPassengers(currWay);
			for (Passenger passenger : passengerIncomingLst) {
				getPassengerToElevator(passenger, currentFloor);
			}
		}
	}
	
	public void getOutPassenger(Passenger passenger, int currentFloor){
		ArrivalStoryContainer arrivalContainer = getArrivalStoryContainerByFloor(passenger.getEndFloor());
		if(arrivalContainer != null){
			logger.info(GlobalLog.addLog(Constants.DEBOARDING + passenger.getPassengerID() + Constants.ON_STORY + currentFloor));
			arrivalContainer.addPassenger(passenger);
			notifyObservers(passenger);
			removeTransportationTaskByPassenger(passenger);
			removeObserverByPassenger(passenger);
		}
	}
	
	public void getPassengerToElevator(Passenger passenger, int currentFloor){
		if(elevatorCont.isElevatorVacant()){
			elevatorCont.addPassenger(passenger);
			logger.info(GlobalLog.addLog(Constants.BOARDING + passenger.getPassengerID() + Constants.ON_STORY + currentFloor));
		}
	}
	
	@Override
	public void register(TransportationTaskObserver newObserver) {
		observers.add(newObserver);
		
	}

	@Override
	public void removeObserverByPassenger(Passenger passenger){
		for (int i = observers.size()-1; i >= 0; i--) {
			TransportationTask tt = (TransportationTask)observers.get(i);
			if(tt.getPassenger().equals(passenger)){
				observers.remove(i);
			}
		}
	}
	
	@Override
	public void notifyObservers(Passenger passenger) {
		for(TransportationTaskObserver observer : observers){
			TransportationTask tt = (TransportationTask)observer;
			if(tt.getPassenger().equals(passenger)){
				observer.update();
			}
		}
	}

	@Override
	public void notifyObservers() {
		for(TransportationTaskObserver observer : observers){
			observer.update();
		}
	}
}
