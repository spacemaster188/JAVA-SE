package com.javacore.utils;
/** Special constants for logging */
public enum LoggingConstants {

    STARTING_TRANSPORTATION("STARTING_TRANSPORTATION"),
    COMPLETION_TRANSPORTATION("COMPLETION_TRANSPORTATION"),
    PROCESS_VALIDATION_OK("Validation correctness of ending transportation process - PASSED"),
    PROCESS_VALIDATION_FAIL("Validation correctness of ending transportation process - FAILED"),
    ALL_PASSENGERS_ARRIVED("All passengers arrived"),
    ALL_PASSENGERS_NOT_ARRIVED("All passengers ARENT arrived"),
    DISPATCH_CONTAINERS_EMPTY("All dispatchStory containers empty"),
    DISPATCH_CONTAINERS_NOT_EMPTY("DispatchStory containers IS NOT empty"),
    ELEVATOR_CONTAINER_EMPTY("Elevator container is empty"),
    ELEVATOR_CONTAINER_NOT_EMPTY("Elevator container IS NOT empty"),
    ARRIVED_PASSENGERS_PASS("All arriving passengers checked"),
    ARRIVED_PASSENGERS_FAIL("There's something WRONG with some arrived passengers"),
    ABORTING_TRANSPORTATION("ABORTING_TRANSPORTATION"),
    INTERRUPTED_STATE("Overall interrupted state of transportation process!");

    private String c;

    private LoggingConstants(final String c) {
        this.c = c;
        }

    public String getConstant() {
        GlobalLog.addLog(c);
        return c;
        }

}
