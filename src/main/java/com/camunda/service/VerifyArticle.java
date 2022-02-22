package com.camunda.service;

import java.util.Random;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class VerifyArticle implements JavaDelegate {
	
	private final Logger logger = Logger.getLogger(VerifyArticle.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Verify Article");
		Random r = new Random();
		execution.setVariable("isVerified", r.nextBoolean());
	}
}
