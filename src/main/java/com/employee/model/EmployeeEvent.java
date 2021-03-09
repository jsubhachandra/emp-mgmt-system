package com.employee.model;

import com.employee.service.EmployeeStateAddedProcessor;
import com.employee.service.EmployeeStateApprovedProcessor;
import com.employee.service.EmployeeStateIncheckProcessor;
import com.employee.statemachine.ProcessEvent;
import com.employee.statemachine.Processor;

/**  
 */
public enum EmployeeEvent implements ProcessEvent {

	ADDED {
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
                return EmployeeStateAddedProcessor.class;
        }
        
        /**
         * This event has no effect on state so return current state
         */
        @Override
        public EmployeeEvent nextState(ProcessEvent event) {
                return EmployeeEvent.INCHECK;
        }

    },
	INCHECK {
    	/**
    	 * 
    	 */
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
            return EmployeeStateIncheckProcessor.class;
        }
        
        @Override
        public EmployeeEvent nextState(ProcessEvent event) {
                return EmployeeEvent.APPROVED;
        }

    },
	APPROVED {
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
                return EmployeeStateApprovedProcessor.class;
        }
        
        /**
         * This event has no effect on state so return current state
         */
        @Override
        public EmployeeEvent nextState(ProcessEvent event) {
                return EmployeeEvent.ACTIVE;
        }
    },
	ACTIVE{
    	/**
    	 * This event does not trigger any process
    	 * So return null 
    	 */
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
            return null;
        }
        @Override
        public EmployeeEvent nextState(ProcessEvent event) {
                return EmployeeEvent.ACTIVE;
        }
    },
    STATE_CHANGE_ERROR {
    	/**
    	 * This event does not trigger any process
    	 * So return null 
    	 */
        @Override
        public Class<? extends Processor> nextStepProcessor(ProcessEvent event) {
            return null;
        }
        
        @Override
        public EmployeeEvent nextState(ProcessEvent event) {
                return EmployeeEvent.ADDED;
        }
    };
}
