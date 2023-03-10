package com.service.stprest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableTransactionManagement
public class ThreadAppRunner implements ApplicationRunner {

	@Autowired
	AsyncService backgroundWorker;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		backgroundWorker.executeAsynchronously();
	}

}
