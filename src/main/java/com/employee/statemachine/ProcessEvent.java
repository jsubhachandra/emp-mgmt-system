package com.employee.statemachine;

import com.employee.model.EmployeeEvent;

public interface ProcessEvent {
    public abstract Class<? extends Processor> nextStepProcessor(ProcessEvent event);   
    public abstract EmployeeEvent nextState(ProcessEvent event);
}
