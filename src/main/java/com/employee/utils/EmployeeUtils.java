/**
 * 
 */
package com.employee.utils;


import static com.employee.Constants.ADD_EMPLOYEE_REQUEST;
import static com.employee.Constants.EMPTY;
import static com.employee.Constants.EMPTY_NAME;
import static com.employee.Constants.ERROR_MSG_TEMPLATE;
import static com.employee.Constants.INVALID_ADD_EMPLOYEE_REQUEST;
import static com.employee.Constants.INVALID_ADD_EMPLOYEE_REQUEST_ERROR;
import static com.employee.Constants.INVALID_ADD_EMPLOYEE_REQUEST_MSG;
import static com.employee.Constants.NAME;
import static com.employee.Constants.NEW_LINE;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import com.employee.exception.EmployeeException;
import com.employee.model.Employee;
import com.employee.vo.EmployeeRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author subhachandra
 *
 */
@Slf4j
public class EmployeeUtils {

	public static void validateEmployeeRequest(EmployeeRequest employee,String employeeRequestJson){
		log.info("validateEmployeeRequest start --->");
		StringBuilder errorMessages = new StringBuilder();
		
		errorMessages.append(isEmpty(employee) ? INVALID_ADD_EMPLOYEE_REQUEST : EMPTY);
		
		errorMessages
		.append((isNotEmpty(employee) && isEmpty(employee.getName()))
				? getErrorMessage(errorMessages, EMPTY_NAME, NAME)
				: EMPTY);
		if (errorMessages.length() > 0) {
			errorMessages.append(INVALID_ADD_EMPLOYEE_REQUEST_ERROR);
			log.error(errorMessages.toString());
			
			StringBuilder message = new StringBuilder(INVALID_ADD_EMPLOYEE_REQUEST_MSG);
			
			message.append(NEW_LINE);
			message.append(ADD_EMPLOYEE_REQUEST);
			message.append(NEW_LINE);
			message.append(NEW_LINE);
			message.append(employeeRequestJson);
			message.append(NEW_LINE);
			message.append(NEW_LINE);
			message.append(ERROR_MSG_TEMPLATE);
			message.append(NEW_LINE);
			message.append(errorMessages.toString());
		
			throw new EmployeeException(errorMessages.toString());

		}
		log.info("<-- validateEmployeeRequest end ");
	}
	
	/**
	 * get concat error messages
	 * 
	 * @param errorMessage
	 * @param message
	 * @param concatMessage
	 * @return
	 */
	private static String getErrorMessage(StringBuilder errorMessage, String message, String concatMessage) {
		return errorMessage.length() > 0 ? concatMessage : message;
	}
	
	/**
	 * Check for the empty string
	 * 
	 * @param obj - obj can be List or Map or Set or Wrapper objects
	 * 
	 * @return false if object found null or ""
	 */
	public static boolean isEmpty(Object... objs) {
		for (Object obj : objs) {
			if (obj == null) {
				return true;
			} else if (obj instanceof List<?>) {
				List<?> var = (List<?>) obj;
				return var.isEmpty();
			} else if (obj instanceof Map<?, ?>) {
				Map<?, ?> var = (Map<?, ?>) obj;
				return var.isEmpty();
			} else if (obj instanceof Set<?>) {
				Set<?> var = (Set<?>) obj;
				return var.isEmpty();
			} else if (obj instanceof String) {
				return ((String) obj).isEmpty();
			} else if (obj instanceof Long) {
				return Objects.isNull(objs);
			}
		}
		return false;
	}

	/**
	 * validate whether object is empty
	 * 
	 * @param objs
	 * @return
	 */
	public static boolean isNotEmpty(Object... objs) {
		return !isEmpty(objs);
	}

	/**
	 * @param request
	 * @return Employee
	 */
	public static Employee getEmployee(@Valid EmployeeRequest request) {
		 Employee employee = new Employee();
		// employee.setEmployeeId(request.getEmployeeId());
		 employee.setName(request.getName());
		 employee.setAddress(request.getAddress());
		 employee.setAge(request.getAge());
		 employee.setContact(request.getContact());
		 employee.setInformation(request.getInformation());
		return employee;
	}


}
