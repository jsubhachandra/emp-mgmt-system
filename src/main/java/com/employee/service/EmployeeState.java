package com.employee.service;

import com.employee.statemachine.ProcessState;


/**
 * @author subhachandra
 *
 */
public enum EmployeeState implements ProcessState {
	ADD_EMPLOYEE,
	EMPLOYEE_CREATED,
	EMPLOYEE_INCHECK,
	EMPLOYEE_APPROVED,
	EMPLOYEE_ACTIVE;
}
