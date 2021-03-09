package com.employee.model;

import com.employee.statemachine.ProcessData;
import com.employee.statemachine.ProcessEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Builder
public class EmployeeData  implements ProcessData{

		private ProcessEvent event;
		private Employee employee;
		
		@Override
		public ProcessEvent getEvent() {
			return this.event;
		}
}
