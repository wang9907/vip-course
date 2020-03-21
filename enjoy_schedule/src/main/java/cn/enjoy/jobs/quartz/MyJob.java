package cn.enjoy.jobs.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String name = dataMap.get("name").toString();
        business(name);
	}

    private void business(String params){
        System.out.println("Myob start --- params:"+params+", thread:" + Thread.currentThread().getName() );
    }

}