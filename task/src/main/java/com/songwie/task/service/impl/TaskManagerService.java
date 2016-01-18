package com.songwie.task.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songwie.task.base.ResultUtil;
import com.songwie.task.base.quartz.ScheduleJob;
import com.songwie.task.base.quartz.TaskCronUtil;
import com.songwie.task.dao.AllDaoImpl;
import com.songwie.task.dao.ScheduleJobDao;
import com.songwie.task.model.JobResultBean;
import com.songwie.task.model.ScheduleJobBean;
import com.songwie.task.service.ITaskManagerService;


@Service("taskManagerService")
@Scope("prototype")
@Transactional
public class TaskManagerService implements ITaskManagerService{

	Logger logger = Logger.getLogger(TaskManagerService.class);

	@Autowired(required=false)
	private TaskCronUtil taskCronUtil;
	@Autowired
	private AllDaoImpl dao;
	@Autowired
	private ScheduleJobDao scheduleJobDao;

	@Override
	public List<Map<String, Object>> getTasks(String operId ) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ScheduleJob> jobs = taskCronUtil.getTasks(null);
		List<ScheduleJobBean> jobBeans = dao.findJobsByList(jobs);

		if(jobs!=null){
			for (ScheduleJob job : jobs) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("taskname", job.getJobName());
				map.put("taskdesc", job.getDesc());
				map.put("execcount", "1");
				map.put("state", ResultUtil.parseTaskState(job.getJobStatus()));
				map.put("cronexpression", job.getCronExpression());
				map.put("createoper", job.getOperUser());
				map.put("taskid", job.getJobId());
				map.put("createdate", job.getCreateTime());
				map.put("execclass", job.getTaskClass());
				map.put("tasktrigger", job.getTrriger());
				for(ScheduleJobBean jobBean : jobBeans){
					if(jobBean.getId().compareTo(Integer.valueOf(job.getJobId()))==0){
						map.put("taskurl", jobBean.getTaskUrl());
						map.put("taskpost", ResultUtil.parseTaskState(jobBean.getPostType()));
						map.put("taskparams", jobBean.getParams());
						map.put("taskdesc", jobBean.getDesc());

						//logger.info("task get info :"+jobBean.getId());
					}else {
						//logger.info("task cant't get info :"+jobBean.getId()+" | "+Integer.valueOf(job.getJobId()));
					}
				}

				list.add(map);
			}
		}

		return list;
	}



	public List<JobResultBean> getTaskHisByTaskId(String taskId){
		List<JobResultBean> list = dao.getJobResultBeansById(taskId, 20);
		return list;
	}

	public List<JobResultBean> getAllTopNTaskHis(){
		List<JobResultBean> list = dao.getTopnJobResultBeans(50);
		return list;
	}

	@Override
	public List<Map<String, Object>> getTaskExecs(String operId ) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ScheduleJob> jobs = taskCronUtil.getTaskExecs(null);
		List<ScheduleJobBean> jobBeans = dao.findJobsByList(jobs);

		if(jobs!=null){
			for (ScheduleJob job : jobs) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("taskname", job.getJobName());
				map.put("taskdesc", job.getDesc());
				map.put("execcount", "1");
				map.put("state", ResultUtil.parseTaskState(job.getJobStatus()));
				map.put("cronexpression", job.getCronExpression());
				map.put("createoper", job.getOperUser());
				map.put("taskid", job.getJobId());
				map.put("createdate", job.getCreateTime());
				map.put("execclass", job.getTaskClass());
				map.put("tasktrigger", job.getTrriger());
				for(ScheduleJobBean jobBean : jobBeans){
					if(jobBean.getId().compareTo(Integer.valueOf(job.getJobId()))==0){
						map.put("taskurl", jobBean.getTaskUrl());
						map.put("taskpost", ResultUtil.parseTaskState(jobBean.getPostType()));
						map.put("taskparams", jobBean.getParams());
						map.put("taskdesc", jobBean.getDesc());
					}
				}
				list.add(map);
			}
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getTask(String operId, String taskId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTask(String operId,ScheduleJob job) {
		//job.setJobId(UUID.randomUUID().toString());

		ScheduleJobBean jobBean = new ScheduleJobBean();
		jobBean.setCreateTime(Calendar.getInstance());
		jobBean.setJobName(job.getJobName());
		jobBean.setJobStatus(1);
		jobBean.setDesc(job.getDesc());
		jobBean.setOperUser(operId);
		jobBean.setParams(job.getParams());
		jobBean.setPostType(job.getPostType());
		jobBean.setTaskUrl(job.getTaskUrl());
		
		scheduleJobDao.save(jobBean);

		job.setJobId(String.valueOf(jobBean.getId()));
		taskCronUtil.saveTask(job);
	}

	@Override
	public void updateTask(String operId, String taskId,ScheduleJob job ) {
		ScheduleJobBean jobBean = dao.find(Integer.valueOf(taskId));
		jobBean.setJobName(job.getJobName());
		jobBean.setDesc(job.getDesc());
		jobBean.setOperUser(operId);
		jobBean.setParams(job.getParams());
		jobBean.setPostType(job.getPostType());
		jobBean.setTaskUrl(job.getTaskUrl());
		
		scheduleJobDao.save(jobBean);

		job.setJobId(String.valueOf(jobBean.getId()));
		taskCronUtil.saveTask(job);

	}

	@Override
	public void execTask(String operId, String taskId ) {
		taskCronUtil.execTask(taskId, null);

	}

	@Override
	public void deleteTask(String operId, String taskId ) {
		taskCronUtil.deleteTask(taskId, null);

	}

	@Override
	public void resumeTask(String operId, String taskId ) {
		taskCronUtil.resumeTask(taskId, null);

	}

	@Override
	public void stopTask(String operId, String taskId ) {
		taskCronUtil.stopTask(taskId, null);

	}



	@Override
	public ScheduleJobBean getTaskById(String taskId) {
		ScheduleJobBean job = dao.find(Integer.valueOf(taskId));
		List<ScheduleJob> qurzJobs = taskCronUtil.getTaskByJobName(taskId);
		ScheduleJob qurzJob = qurzJobs.get(0);
		if(qurzJob!=null){
			String cronExpression = qurzJob.getCronExpression();
			job.setCronExpression(cronExpression);
		}

		return job;
	}





}
