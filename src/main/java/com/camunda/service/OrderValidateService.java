package com.camunda.service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class OrderValidateService implements JavaDelegate {
	
	private static final Logger log = Logger.getLogger(OrderValidateService.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("order validate service task");
		String orderName = (String) execution.getVariable("orderName");
		boolean isValidate = false;
		if(!orderName.isEmpty()) {
			log.info("Order is valid");
			isValidate = true;
		} else {
			log.info("Order is not valid");
		}
		execution.setVariable("isValidate", isValidate);
	}
}
