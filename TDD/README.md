Elevator

Using Singleton, Observer patterns.
TransportationTaskTest contains a Mock tests classes
Other tests classes is a JUnit test classes

We have a building of several floors, with Elevator
a certain capacity. On the floors of the building the men placed,
who want to move to another floor. Elevator organize 
THEIR transportation.

Optional:
- accelerated animations (ratio) (animationBoost) 

The original data stored in a special config file.property file in the form
steam - key/value. 
Regarding the latter parameter animationBoost, the increase
cause the acceleration of the rendering (drawing graphs) of the transportation process. 
If animationBoost set to zero (animationBoost=0) then the app should work in console mode.

The condition of the transportation process for the individual: 
Every passenger has a property "condition of carriage" (transportationState), which can take the values:
NOT_STARTED (the value field is initialized when the instance of the Passenger)
IN_PROGRESS (when a Transportation Task) 
COMPLETED (when completed Transportation Task) 
ABORTED (when Transportation is interrupted Task)

Validation of the correctness of the completion of the transportation process: 
the following checks should be performed:
- all containers in the shipment (dispatchStoryContainer) must be empty 
container Elevator cabin (evelvatorContainer) must be empty
- in containers of arrival (arrivalStoryContainer) all people who are there, 
transportationState must be COMPLETED and the destination floor (destinationStory) must match
number of floors associated with the corresponding arrivalStoryContainer 
- the total number of people on all arrivalStoryContainer must match passengersNumber

Logging of the transportation process:
the transportation process is carried out through certain operator actions (actions). Such as:
STARTING_TRANSPORTATION the start of the transportation process 
COMPLETION_TRANSPORTATION the completion of the transportation process 
ABORTING_TRANSPORTATION interruption of the transportation process
MOVING_ELEVATOR (from story-story N to-M) the movement of the Elevator from floor to floor 
BOARDING_OF_PASSENGER ( passangerID on story-N) boarding on the floor
DEBOARDING_OF_PASSENGER ( passangerID on story-N) disembark the passenger at the floor

If in config file animationBoost=0, then the process of transportation is only displayed by outputting to the console