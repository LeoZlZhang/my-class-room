package com.leo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by sh00514 on 2017/1/22.
 * entry
 */
public class Run4SpringBatch {
    public static void main(String[] args) throws IOException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        String[] confs = Files.find(Paths.get(System.getProperty("user.dir")), Integer.MAX_VALUE, (path, attri) -> path.getFileName().toString().startsWith("demoSpringBatchContext"))
                .map(path -> path.toAbsolutePath().toString())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        ApplicationContext context = new FileSystemXmlApplicationContext(confs[0]);
        JobLauncher launcher = context.getBean("jobLauncher", SimpleJobLauncher.class);
        launcher.run(context.getBean("batchjob", Job.class), new JobParameters());
    }
}
