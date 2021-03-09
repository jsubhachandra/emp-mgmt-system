/**
 * 
 */
package com.employee;

/**
 * @author subha.chandra
 *
 */
public interface Constants {
    /**
     * The version of the REST APIs
     */
	public static final String API_VERSION = "v1";

    /**
     * The root of all REST API URLs
     */
	public static final String API_PREFIX = "/api/" + API_VERSION;
	public static final String ROOT_PATH = API_PREFIX + "/employee-mgmt";
	
	public static final String ADD_EMPLOYEE = "/addEmployee";
	
	public static final String FAILURE = "FAILURE ";

	public static final String SUCCESS = "SUCCESS";
	
	
	public static final String REQUEST_PROCESSED_SUCCESSFULLY = "Request processed successfully";
	
	public static final String EMPTY = "";
	
	public static final String NEW_LINE = "<br>";
	
	public static final String SERVICE_TYPE = ", service type";
	
	public static final String INVALID_ADD_EMPLOYEE_REQUEST = "Invalid addEmployeeRequest";
	
	public static final String INVALID_ADD_EMPLOYEE_REQUEST_ERROR = " in addEmployeeRequest";
	
	public static final String INVALID_ADD_EMPLOYEE_REQUEST_MSG = "Invalid addEmployeeRequest Request received";
	
	public static final String ADD_EMPLOYEE_REQUEST = "Please find below the addEmployeeRequest request received:";
	
	public static final String ERROR_MSG_TEMPLATE = "Also, Please find the error details:";
	
	public static final String EMPTY_NAME = "Empty name";
	
	public static final String NAME = ", name";
}
