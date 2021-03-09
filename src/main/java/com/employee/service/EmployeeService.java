/**
 * 
 */
package com.employee.service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.employee.exception.EmployeeException;
import com.employee.exception.EmployeeStateException;
import com.employee.model.Employee;
import com.employee.model.EmployeeData;
import com.employee.model.EmployeeEvent;
import com.employee.statemachine.AbstractStateTransitionsManager;
import com.employee.statemachine.ProcessData;
import com.employee.statemachine.ProcessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author subhachandra
 *
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class EmployeeService extends AbstractStateTransitionsManager {

	private final ApplicationContext context;
	private final EmployeeDbService dbService;

	/**
	 *processStateTransition
	 */
	@Override
	protected ProcessData processStateTransition(ProcessData sdata) throws ProcessException {

		log.info("processStateTransition start-->");

		EmployeeData data = (EmployeeData) sdata;
		try {
			log.info("Event: " + data.getEvent().toString());
			data = (EmployeeData) this.context.getBean(data.getEvent().nextStepProcessor(data.getEvent()))
					.process(data);
			log.info("Post Event " + data.getEvent().toString());
			dbService.getStates().put(data.getEmployee().getEmployeeId(),
					(EmployeeEvent) data.getEvent().nextState(data.getEvent()));

		} catch (EmployeeStateException e) {
			log.info("Exception in processStateTransition" + e.getMessage());
			throw new EmployeeStateException(((EmployeeEvent) data.getEvent()).name(), e);
		}
		log.info("<-- processStateTransition end");
		return data;
	}

	/**
	 * @param employeeData
	 * @return EmployeeData
	 * @throws EmployeeException
	 */
	public EmployeeData checkStateForReturningCustomers(EmployeeData employeeData) throws EmployeeException {
		log.info("checkStateForReturningCustomers start-->");
		// returning customers must have a state
		Employee employee = employeeData.getEmployee();
		if (employee.getEmployeeId() != null) {
			if (this.dbService.getStates().get(employee.getEmployeeId()) == null) {
				log.info("No state exists for employeeId=" + employee.getEmployeeId());
				throw new EmployeeException("No state exists for employeeId=" + employee.getEmployeeId());
			} else if (this.dbService.getStates().get(employee.getEmployeeId()) == EmployeeEvent.ACTIVE) {
				log.info("ACTIVE state: " + employee.getEmployeeId());
				throw new EmployeeException("ACTIVE state: " + employee.getEmployeeId());
			} else {
				log.info("Initial state: " + dbService.getStates().get(employee.getEmployeeId()).name());
			}
		}
		log.info("<-- checkStateForReturningCustomers end");
		return employeeData;
	}

	/**
	 * initialize employee state
	 ** 
	 * @param ProcessData
	 * @return ProcessData
	 */
	@Override
	protected ProcessData initializeState(ProcessData processData) throws EmployeeException {
		log.info("initializeState start-->");

		EmployeeData employeeData = (EmployeeData) processData;

		if (employeeData.getEmployee().getEmployeeId() != null) {
			return checkStateForReturningCustomers(employeeData);
		}

		Random r = new Random();
		int low = 100;
		int high = 500;

		Integer employeeId = r.nextInt(high - low) + low;
		Employee employee = employeeData.getEmployee();
		employee.setEmployeeId(employeeId);
		dbService.getStates().put(employeeId, (EmployeeEvent) EmployeeEvent.ADDED);

		log.info("Initial state: " + dbService.getStates().get(employeeId));
		log.info("<-- initializeState end");
		return processData;
	}

	/**
	 * @return ConcurrentHashMap
	 */
	public ConcurrentHashMap<Integer, EmployeeEvent> getStates() {
		return dbService.getStates();
	}

	/**
	 * This method is used to get existing employee events
	 * 
	 * @param empId
	 * @return EmployeeEvent
	 */
	public EmployeeEvent getStates(Integer empId) {
		log.info("getStates ");
		return dbService.getStates().get(empId);
	}
}
