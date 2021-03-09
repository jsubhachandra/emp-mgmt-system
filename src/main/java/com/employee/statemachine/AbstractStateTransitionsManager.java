package com.employee.statemachine;

/**
 * @author subhachandra
 *
 */
public abstract class AbstractStateTransitionsManager implements StateTransitionsManager {
	
    protected abstract ProcessData initializeState(ProcessData data) throws ProcessException;
    protected abstract ProcessData processStateTransition(ProcessData data) throws ProcessException;

    /**
     *processEvent
     */
    @Override
    public ProcessData processEvent(ProcessData data) throws ProcessException {
    	initializeState(data);
        return processStateTransition(data);
    }
}
