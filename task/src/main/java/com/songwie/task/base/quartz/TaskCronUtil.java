package com.songwie.task.base.quartz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.songwie.task.base.TaskException;
import com.songwie.task.base.constant.TaskConstant;


@Component
public class TaskCronUtil {

	@Autowired(required=false)
	private Scheduler scheduler;


	private static String GROUP = "DEFAULT";


	//添加任务
	@SuppressWarnings("unchecked")
	public void saveTask(ScheduleJob job)  {
		if(job.getJobGroup()==null){
			job.setJobGroup(GROUP)  ;
		}

		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobId(), job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			if (null == trigger) {

				JobDetail jobDetail = JobBuilder.newJob(job.getTaskClass()).withIdentity(job.getJobId(), job.getJobGroup()).build();

				//表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

				//按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobId(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				this.updateTask(job);
			}

		}catch (Exception e) {
			throw new TaskException(e);
		}

	}




	public void getTaskByKey(String taskKey,String group){
		try {
    		JobKey jobKey = JobKey.jobKey(taskKey,group);

		}catch (Exception e) {
			throw new TaskException(e);
		}
	}

	//更新任务
	@SuppressWarnings("unchecked")
	public void updateTask(ScheduleJob job){
		JobKey jobKey = JobKey.jobKey(job.getJobId(),job.getJobGroup());

		try {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);

			List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
			for(Trigger trr: triggers){
				//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
				CronTrigger trigger = (CronTrigger)trr;

				//表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

				//按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(trigger.getKey()).withSchedule(scheduleBuilder).build();

				//按新的trigger重新设置job执行
				scheduler.rescheduleJob(trigger.getKey(), trigger);
			}
		}catch (Exception e) {
			throw new TaskException(e);
		}

	}
	//立即执行
    public void execTask(String taskKey,String group)  {
    	if(group==null){
    		group = GROUP ;
		}

    	try {
    		JobKey jobKey = JobKey.jobKey(taskKey,group);
        	scheduler.triggerJob(jobKey);
		}catch (Exception e) {
			throw new TaskException(e);
		}
	}
	//删除任务
	public void deleteTask(String taskKey,String group)  {
		if(group==null){
    		group = GROUP ;
		}

		try {
			JobKey jobKey = JobKey.jobKey(taskKey,group);
			scheduler.deleteJob(jobKey);
		}catch (Exception e) {
			throw new TaskException(e);
		}
	}
	//恢复任务
	public void resumeTask(String taskKey,String group) {
		if(group==null){
    		group = GROUP ;
		}

	    try {
	    	JobKey jobKey = JobKey.jobKey(taskKey,group);
		    scheduler.resumeJob(jobKey);
		}catch (Exception e) {
			throw new TaskException(e);
		}
	}

	//暂停任务
	public void stopTask(String taskKey,String group){
		if(group==null){
    		group = GROUP ;
		}
		try {
			JobKey jobKey = JobKey.jobKey(taskKey, group);
		    scheduler.pauseJob(jobKey);
		}catch (Exception e) {
			throw new TaskException(e);
		}

	}

	//查询任务 定时
	public List<ScheduleJob> getTasks(String group) {
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();

		try {
			Set<JobKey> jobKeys = new HashSet<JobKey>();
			if(group==null){
				List<String> groups = scheduler.getJobGroupNames();
				for (String name : groups) {
					GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(name);
					jobKeys = scheduler.getJobKeys(matcher);
				}
			}else {
				GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(group);
				jobKeys = scheduler.getJobKeys(matcher);
			}


			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);

				for (Trigger trigger : triggers) {
					ScheduleJob job = new ScheduleJob();


					job.setJobName(jobKey.getName());
					job.setJobId(trigger.getJobKey().getName());
					job.setJobGroup(jobKey.getGroup());
					job.setDesc(jobDetail.getDescription());
					job.setCreateTime(TaskConstant.DATEFORMAT_LONG.format(trigger.getStartTime()));
					job.setTaskClass(jobDetail.getJobClass());

					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setJobStatus(triggerState.name());

					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setCronExpression(cronExpression);
						job.setTrriger("CronTrigger"+Thread.currentThread().getId());

						jobList.add(job);
					}/*else{
						if (trigger instanceof SimpleTrigger) {
							job.setTrriger("SimpleTrigger"+Thread.currentThread().getId());
							job.setCronExpression("立即执行");
							job.setExecCount(((SimpleTrigger) trigger).getTimesTriggered());

							jobList.add(job);
						}
					}*/

				}
			}

		} catch (Exception e) {
			throw new TaskException(e);
		}


		return jobList;
	}

	//查询任务 定时
	public List<ScheduleJob> getTaskByJobName(String jobName) {
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();

		JobKey jobKey = JobKey.jobKey(jobName);

		try {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);

			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();


				job.setJobName(jobKey.getName());
				job.setJobId(trigger.getJobKey().getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDesc(jobDetail.getDescription());
				job.setCreateTime(TaskConstant.DATEFORMAT_LONG.format(trigger.getStartTime()));
				job.setTaskClass(jobDetail.getJobClass());

				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());

				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
					job.setTrriger("CronTrigger"+Thread.currentThread().getId());

					jobList.add(job);
				}
			}

		} catch (Exception e) {
			throw new TaskException(e);
		}


		return jobList;
	}

	//查询任务 当前正在运行
	public List<ScheduleJob> getTaskExecs(String group) {
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();

		try {
			Set<JobKey> jobKeys = new HashSet<JobKey>();
			if(group==null){
				List<String> groups = scheduler.getJobGroupNames();
				for (String name : groups) {
					GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(name);
					jobKeys = scheduler.getJobKeys(matcher);
				}
			}else {
				GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(group);
				jobKeys = scheduler.getJobKeys(matcher);
			}


			/*for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);

				for (Trigger trigger : triggers) {
					ScheduleJob job = new ScheduleJob();


					job.setJobName(jobKey.getName());
					job.setJobId(trigger.getJobKey().getName());
					job.setJobGroup(jobKey.getGroup());
					job.setDesc(jobDetail.getDescription());
					job.setCreateTime(XuriTaskConstant.DATEFORMAT_LONG.format(trigger.getStartTime()));
					job.setTaskClass(jobDetail.getJobClass());

					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setJobStatus(triggerState.name());

					if (trigger instanceof SimpleTrigger) {
						job.setTrriger("SimpleTrigger"+Thread.currentThread().getId());
						job.setCronExpression("立即执行");
						job.setExecCount(((SimpleTrigger) trigger).getTimesTriggered());

						jobList.add(job);

					}
				}
			}
			*/
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();

			for (JobExecutionContext executingJob : executingJobs) {
				JobDetail jobDetail = executingJob.getJobDetail();

				JobKey jobKey = jobDetail.getKey();
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					ScheduleJob jobb = new ScheduleJob();
					if(jobKey.getName()==null){
						continue;
					}

					jobb.setJobName(jobKey.getName());
					jobb.setJobId(trigger.getJobKey().getName());
					jobb.setJobGroup(jobKey.getGroup());
					jobb.setDesc(jobDetail.getDescription()+trigger.getDescription());
					jobb.setCreateTime(TaskConstant.DATEFORMAT_LONG.format(trigger.getStartTime()));
					jobb.setTaskClass(jobDetail.getJobClass());

					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					jobb.setJobStatus(triggerState.name());

					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						jobb.setCronExpression(cronExpression);
						jobb.setTrriger("CronTrigger"+Thread.currentThread().getId());

						jobList.add(jobb);

					}
					if (trigger instanceof SimpleTrigger) {
						//jobb.setDesc("trigger"+Thread.currentThread().getName());
						jobb.setExecCount(((SimpleTrigger) trigger).getTimesTriggered());
						jobb.setTrriger("SimpleTrigger"+Thread.currentThread().getId());

						jobList.add(jobb);

					}
				}
			}
		} catch (Exception e) {
			throw new TaskException(e);
		}


		return jobList;
	}

}
