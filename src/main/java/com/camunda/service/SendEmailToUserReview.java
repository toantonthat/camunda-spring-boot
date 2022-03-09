package com.camunda.service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("sendEmailToUserReview")
public class SendEmailToUserReview implements JavaDelegate {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		logger.info("sendEmailToUserReview");
	}

}
