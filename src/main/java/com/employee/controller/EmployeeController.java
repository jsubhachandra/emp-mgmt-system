/**
 * 
 */
package com.employee.controller;

import static com.employee.Constants.FAILURE;
import static com.employee.Constants.ROOT_PATH;
import static com.employee.Constants.SUCCESS;
import static com.employee.utils.EmployeeUtils.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exception.EmployeeException;
import com.employee.model.Employee;
import com.employee.model.EmployeeData;
import com.employee.model.EmployeeEvent;
import com.employee.service.EmployeeService;
import com.employee.statemachine.ProcessException;
import com.employee.utils.EmployeeUtils;
import com.employee.utils.JsonConverter;
import com.employee.vo.EmployeeRequest;
import com.employee.vo.EmployeeResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author subhachandra
 *
 */
@RestController
@RequestMapping(ROOT_PATH)
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	/**
	 * This method is used to add employee
	 * @param employeeRequest
	 * @return EmployeeResponse
	 */
	@RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
	public EmployeeResponse addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
		log.info("addEmployee start -->");
		
		EmployeeResponse response = new EmployeeResponse();
  		
  		try {
  			String employeeRequestJson = JsonConverter.convertObjectToJsonString(employeeRequest);
  			
  			validateEmployeeRequest(employeeRequest, employeeRequestJson);
  			
  	      EmployeeData data = new EmployeeData();
          data.setEvent(EmployeeEvent.ADDED);
          
          data.setEmployee(EmployeeUtils.getEmployee(employeeRequest));
          data = (EmployeeData)employeeService.processEvent(data);
          
          response.setEmployee(data.getEmployee());
          
          response.setEmployeeState(((EmployeeEvent)data.getEvent()).name());
          
  		}catch (Exception ex) {
  			log.error("Error occurred while processing the data {} ", ex.getMessage());
			response.setStatus(FAILURE + ex.getMessage());
			return response;
		}
  		response.setStatus(SUCCESS);
  		
  		log.info("<-- addEmployee end");
		return response;
	}
	
	/**
	 * This method is used to change the Employee State
	 * @param empId
	 * @return String
	 * @throws ProcessException
	 */
	@RequestMapping(path = "/changeState", method = RequestMethod.GET)
	public String changeState(@NotNull @NotBlank Integer empId) throws ProcessException {
		
		log.info("changeState start -->");
		
		if(isEmpty(empId)){
		
			throw new EmployeeException("empId is null in Request");
		}
		
		EmployeeData data = new EmployeeData();
		Employee employee = new Employee();
		employee.setEmployeeId(empId);
		data.setEmployee(employee);
		
		 //set existing 
		EmployeeEvent state =employeeService.getStates(empId);
		 
		data.setEvent(state);
		
		data = (EmployeeData)employeeService.processEvent(data);
    	
		log.info("<-- changeState end");
		
        return ((EmployeeEvent)data.getEvent()).name();

	}
	
    /**
     * This method is used to handle the exception
     * @param EmployeeException
     * @return String
     */
    @ExceptionHandler(value=EmployeeException.class)
    public String handleEmployeeException(EmployeeException e) {
        return e.getMessage();
    }
}
