package com.songwie.task.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.songwie.task.base.quartz.ScheduleJob;
import com.songwie.task.base.quartz.TaskCronUtil;
import com.songwie.task.task.MyTask;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@ActiveProfiles(profiles={"dev"})
@Lazy(false)
public class QuartzTest {

	@Autowired(required=false)
	private TaskCronUtil taskCronUtil;

	@Test
	public void addTask() throws Exception{

		taskCronUtil.stopTask("taskJob", null);

		ScheduleJob job = new  ScheduleJob();

		job.setCronExpression("0/10 * * * * ?");
		job.setJobId("myjobtest");
		job.setJobName("测试");
		job.setTaskClass(MyTask.class);

		taskCronUtil.saveTask(job);


		job.setCronExpression("0/20 * * * * ?");
		taskCronUtil.updateTask(job);

		List<ScheduleJob> jobs = taskCronUtil.getTasks(null);
		for (ScheduleJob jobb : jobs) {
			System.out.println(jobb.getJobName() + "|" + jobb.getCronExpression()+ "|" + jobb.getJobStatus());
		}

		taskCronUtil.stopTask("myjobtest", null);

		taskCronUtil.resumeTask("myjobtest", null);

		taskCronUtil.execTask("myjobtest", null);

		taskCronUtil.deleteTask("myjobtest", null);




	}

}
