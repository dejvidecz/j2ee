package batching;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.util.Properties;


/**
 * Created by Dejv on 12.01.17.
 */
public class QuartzJob implements Job {
    JobOperator jobOperator;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(jobOperator==null){
            jobOperator = BatchRuntime.getJobOperator();
        }

        jobOperator.start("myjob",new Properties());

        System.out.println("Hello World! - " + new java.util.Date());
    }
}
