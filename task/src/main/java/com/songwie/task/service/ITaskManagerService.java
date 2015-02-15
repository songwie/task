package com.songwie.task.service;

import java.util.List;
import java.util.Map;

import com.songwie.task.base.quartz.ScheduleJob;
import com.songwie.task.model.JobResultBean;
import com.songwie.task.model.ScheduleJobBean;

public interface ITaskManagerService {
	List<Map<String, Object>> getTasks(String operId );
	List<Map<String, Object>> getTaskExecs(String operId );
	List<Map<String, Object>> getTask(String operId,String taskId );
	void saveTask(String operId,ScheduleJob job);
	void updateTask(String operId,String taskId,ScheduleJob job );
	void execTask(String operId,String taskId );
	void deleteTask(String operId,String taskId  );
	void resumeTask(String operId,String taskId );
	void stopTask(String operId,String taskId );
	List<JobResultBean> getTaskHisByTaskId(String taskId);
	List<JobResultBean> getAllTopNTaskHis();
	ScheduleJobBean getTaskById(String taskId);

}
