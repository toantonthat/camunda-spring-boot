package com.camunda.service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LoggerDelegate implements JavaDelegate {

	private final Logger logger = Logger.getLogger(LoggerDelegate.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("\n\n Logger Delegate invoked by " 
				+ " processDefinationId=" + execution.getProcessDefinitionId()
				+ ", activityId=" + execution.getCurrentActivityId() 
				+ ", activityName=" + execution.getCurrentActivityName()
				+ ", processInstanceId=" + execution.getProcessInstanceId()
				+ ", businessKey=" + execution.getProcessBusinessKey()
				+ ", executionId=" + execution.getId()
				+ "\n\n");
	}

}
