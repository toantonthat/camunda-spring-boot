package com.camunda.external;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.stereotype.Component;

@Component
public class InvadeWorkerOne {
	private static final Logger log = Logger.getLogger(InvadeWorkerOne.class.getName());

	@PostConstruct
	public void invadeWorker() {
		log.info("--------------- invade worker 1 --------------");
		ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();

		// subscribe to an external task topic as specified in the process
		client.subscribe("DecideOnExpansion").lockDuration(1000) // the default lock duration is 20 seconds, but you can
																	// override this
				.handler((externalTask, externalTaskService) -> {
					String businessKey = externalTask.getBusinessKey();
                    log.info("businessKey --------------> " + businessKey);
                    		
					// Put your business logic here
					log.info("Decide where to expand (5 sec)");

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					log.info("After sleep");

					Map<String, Object> map = new HashMap<>();

					Random rando = new Random();
					map.put("north", rando.nextBoolean());

					// Complete the task
					externalTaskService.complete(externalTask, map);
				}).open();
	}
}
