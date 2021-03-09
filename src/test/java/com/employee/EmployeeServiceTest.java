package com.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.employee.exception.EmployeeException;
import com.employee.model.Employee;
import com.employee.model.EmployeeData;
import com.employee.model.EmployeeEvent;
import com.employee.service.EmployeeDbService;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeState;

/**
 * @author subhachandra
 *
 */
@SpringBootTest
@AutoConfigureWebTestClient
public class EmployeeServiceTest {

	@Autowired
	private ApplicationContext context;

	private EmployeeDbService dbService = new EmployeeDbService();

	private EmployeeService employeeService;

	/**
	 * Test for addEmployee success scenario
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_addEmployeeState() throws Exception {
		EmployeeData employeeData = EmployeeData.builder().event(EmployeeEvent.ADDED).build();
		employeeData.setEmployee(MockData.getEmployee());
		employeeService = new EmployeeService(context, dbService);

		EmployeeData data = (EmployeeData) employeeService.processEvent(employeeData);

		assertThat(employeeService.getStates().get(data.getEmployee().getEmployeeId()))
				.isEqualTo(EmployeeEvent.INCHECK);
	}

	/**
	 * Test for addEmployee failure scenario
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_addEmployeeStateFailure() throws Exception {
		EmployeeData data = EmployeeData.builder().event(EmployeeEvent.ADDED).build();
		data.setEmployee(MockData.getEmployee());
		employeeService = new EmployeeService(context, dbService);

		data = (EmployeeData) employeeService.processEvent(data);
		assertThat(employeeService.getStates().get(data.getEmployee().getEmployeeId()))
				.isNotEqualTo(EmployeeEvent.ADDED);
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void testCheckStateForReturningCustomers() throws Exception {
		EmployeeData employeeData = new EmployeeData();
		Employee employee = MockData.getEmployee();
		employee.setEmployeeId(23);
		employeeData.setEmployee(employee);
		employeeData.setEvent(EmployeeEvent.ADDED);
		employeeService = new EmployeeService(context, dbService);
		assertThrows(EmployeeException.class, () -> {
			employeeService.checkStateForReturningCustomers(employeeData);
        });
		
	}
	
	/**
	 * testEnums
	 */
	@Test
	public void testEnums() {
	    assertEquals("ADD_EMPLOYEE", EmployeeState.ADD_EMPLOYEE.name());
	    assertEquals("EMPLOYEE_CREATED", EmployeeState.EMPLOYEE_CREATED.name());
	    assertEquals("EMPLOYEE_INCHECK", EmployeeState.EMPLOYEE_INCHECK.name());
	    assertEquals("EMPLOYEE_APPROVED", EmployeeState.EMPLOYEE_APPROVED.name());
	    assertEquals("EMPLOYEE_ACTIVE", EmployeeState.EMPLOYEE_ACTIVE.name());

	}

}
