/**
 * 
 */
package com.employee.exception;

/**
 * @author subhachandra
 *
 */
public class EmployeeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8252897965529523681L;
	

	public EmployeeException(String message){
		super(message);
		this.exceptionMsg = message; 
	}
	
	public EmployeeException(String message, Exception e) {
		super(message);
		this.exceptionMsg = message; 
	}

	private String exceptionMsg;

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	

}
