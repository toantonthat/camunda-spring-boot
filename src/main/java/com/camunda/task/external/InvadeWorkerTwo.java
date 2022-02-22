package com.camunda.task.external;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.stereotype.Component;

@Component
public class InvadeWorkerTwo {
	private static final Logger log = Logger.getLogger(InvadeWorkerTwo.class.getName());

	@PostConstruct
	public void invadeWorker() {
		log.info("--------------- invade worker 2 --------------");
		ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();

		// subscribe to an external task topic as specified in the process
		client.subscribe("Invade").lockDuration(1000) // the default lock duration is 20 seconds, but you can override
														// this
				.handler((externalTask, externalTaskService) -> {
					// Put your business logic here
					String businessKey = externalTask.getBusinessKey();
					if ("fail".equals(businessKey)) {
						log.warning("FAILURE");
						externalTaskService.handleFailure(externalTask, "errorMessage", "errorDetails", 0, 0);
					} else if ("error".equals(businessKey)) {
						log.warning("ERROR");
						externalTaskService.handleBpmnError(externalTask, "errorCode", "errorMessage");
					} else {
						String command = externalTask.getVariable("command");
						log.info(command + "");
						// Complete the task
						externalTaskService.complete(externalTask);
					}
				}).open();
	}
}
