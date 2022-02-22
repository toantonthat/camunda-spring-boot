package com.camunda.service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sendOrderDelegate")
public class SendOrderDelegate implements JavaDelegate {

	private static final Logger log = Logger.getLogger(SendOrderDelegate.class.getName());
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("send order service task");
		MessageCorrelationResult result = runtimeService.createMessageCorrelation("messageShipment")
				.processInstanceBusinessKey(execution.getBusinessKey())
				.setVariable("customerName", execution.getVariable("customerName"))
				.setVariable("item", execution.getVariable("item"))
				.setVariable("quantity", execution.getVariable("quantity"))
				.correlateWithResult();
		DelegateExecution shipmentExecution = (DelegateExecution) result.getExecution();
		log.info("{} = " + shipmentExecution.getVariables());
	}
}
