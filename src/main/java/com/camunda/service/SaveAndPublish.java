package com.camunda.service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SaveAndPublish implements JavaDelegate {
	
	private final Logger logger = Logger.getLogger(SaveAndPublish.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Saved");
	}

}
