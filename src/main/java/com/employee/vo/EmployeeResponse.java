package com.employee.vo;

import com.employee.model.Employee;

public class EmployeeResponse extends BaseResponse {

	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
