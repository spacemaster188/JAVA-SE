package com.javacore.containers;

import java.io.IOException;

import com.javacore.controllers.ElevatorController;
import com.javacore.controllers.PassengerController;

public class Building {

    public Building() throws IOException, InterruptedException {
        super();
        PassengerController passControl = new PassengerController();
        passControl.organizePassengers();
        new ElevatorController(passControl).start();
        }

}
