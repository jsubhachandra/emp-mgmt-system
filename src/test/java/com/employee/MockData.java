package com.employee;

import com.employee.model.Employee;

/**
 * @author subhachandra
 *
 */
class MockData {

   static Employee getEmployee() {
	   Employee employee = new Employee();
	   //employee.setEmployeeId(101);
	   employee.setName("John");
	   employee.setAge(30);
	   employee.setAddress("Berlin");
	return employee;
	   
   }
   
}
