package com.mcoding.base.ui.service.schedule.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestJob {
	
	Logger logger = LoggerFactory.getLogger(TestJob.class);
	
	private int executeTime = 0;
	
	public void test(){
		
		logger.info("Test job["+Thread.currentThread().getName()+"] begin execute");
		int name = executeTime;
		executeTime ++;
		try {
			logger.info("sleep 5s ");
			Thread.sleep(20000l);
//			this.wait(5000l);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Test job["+Thread.currentThread().getName()+"] execute time["+name+"]");
	}

}
