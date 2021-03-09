/**
 * 
 */
package com.employee.service;

import org.springframework.stereotype.Service;

import com.employee.model.EmployeeData;
import com.employee.model.EmployeeEvent;
import com.employee.statemachine.ProcessData;
import com.employee.statemachine.ProcessException;
import com.employee.statemachine.Processor;


/**
 * @author subhachandra
 *
 */
@Service
public class EmployeeStateIncheckProcessor implements Processor{

	@Override
	public ProcessData process(ProcessData data) throws ProcessException {
		if(EmployeeEvent.INCHECK.equals(data.getEvent())) {
			((EmployeeData)data).setEvent(EmployeeEvent.INCHECK); 
		}
        return data;
	}

}
