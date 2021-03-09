package com.employee.exception;

import com.employee.statemachine.ProcessException;

public class EmployeeStateException extends ProcessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1232156305643742267L;

    public EmployeeStateException(String message) {
        super(message);
    }

    public EmployeeStateException(String message, Throwable e) {
        super(message, e);
    }
}
