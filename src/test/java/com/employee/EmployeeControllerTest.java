/**
 * 
 */
package com.employee;

import static com.employee.Constants.FAILURE;
import static com.employee.Constants.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.controller.EmployeeController;
import com.employee.model.EmployeeData;
import com.employee.model.EmployeeEvent;
import com.employee.service.EmployeeService;
import com.employee.statemachine.ProcessData;
import com.employee.utils.EmployeeUtils;
import com.employee.vo.EmployeeRequest;
import com.employee.vo.EmployeeResponse;

/**
 * @author subhachandra
 *
 */
@SpringBootTest(classes = { Application.class, EmployeeController.class, EmployeeService.class })
@AutoConfigureWebTestClient
public class EmployeeControllerTest {

	@Mock
	EmployeeService employeeService;
	@Mock
	ProcessData processData;
	@Mock
	EmployeeData employeeData;

	@InjectMocks
	EmployeeController controller = new EmployeeController();

	/**
	 * Test addEmployee for success scenario
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_addEmployee() throws Exception {
		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setName("John");
		EmployeeResponse response = new EmployeeResponse();

		EmployeeData data = new EmployeeData();
		data.setEvent(EmployeeEvent.ADDED);

		data.setEmployee(EmployeeUtils.getEmployee(employeeRequest));

		Mockito.when(employeeService.processEvent(Mockito.any())).thenReturn(data);
		response = controller.addEmployee(employeeRequest);
		assertEquals(SUCCESS, response.getStatus());
	}

	/**
	 * Test addEmployee for failure scenario
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_addEmployeeFailure() throws Exception {
		EmployeeRequest employeeRequest = new EmployeeRequest();
		EmployeeResponse response = new EmployeeResponse();
		response = controller.addEmployee(employeeRequest);
	    assertTrue(response.getStatus().contains(FAILURE));

	}

	/**
	 * Test changeState
	 * 
	 * @throws Exception
	 */
		@Test
        public void test_changeState() throws Exception {
    
        	Integer emplId =101;
        	
        	EmployeeEvent event= EmployeeEvent.INCHECK;
    		 //set existing 
        	Mockito.when(employeeService.getStates(emplId)).thenReturn(event);
        	
        	assertThrows(NullPointerException.class, () -> {
        		controller.changeState(emplId);
            });
        	
        } 
}
