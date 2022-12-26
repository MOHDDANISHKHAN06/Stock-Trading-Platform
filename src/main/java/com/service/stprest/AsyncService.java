package com.service.stprest;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
	
	@Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ExecutorService taskExecutor;

    public void executeAsynchronously() {

    	OrderWorkerService myThread = applicationContext.getBean(OrderWorkerService.class);
        taskExecutor.execute(myThread);
    }

}
