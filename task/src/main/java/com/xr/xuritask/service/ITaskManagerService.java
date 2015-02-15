package com.xr.xuritask.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xr.xuritask.base.quartz.ScheduleJob;
import com.xr.xuritask.model.JobResultBean;
import com.xr.xuritask.model.ScheduleJobBean;

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
