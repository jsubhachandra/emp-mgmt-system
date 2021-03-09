package com.employee.statemachine;

/**
 * @author subhachandra
 *
 */
public interface StateTransitionsManager {
    public ProcessData processEvent(ProcessData data) throws ProcessException;
}
