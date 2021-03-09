package com.employee.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.employee.model.EmployeeEvent;

@Service
public class EmployeeDbService {
    
    private final ConcurrentHashMap<Integer, EmployeeEvent> states;
    
    public EmployeeDbService() {
        this.states = new ConcurrentHashMap<Integer, EmployeeEvent>();
    }

    public ConcurrentHashMap<Integer, EmployeeEvent> getStates() {
        return states;
    }
}
