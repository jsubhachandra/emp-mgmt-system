package com.employee.statemachine;

public interface Processor {
    public ProcessData process(ProcessData data) throws ProcessException;
}
