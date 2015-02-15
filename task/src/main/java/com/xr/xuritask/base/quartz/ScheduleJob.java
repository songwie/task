package com.xr.xuritask.base.quartz;

import java.io.Serializable;


public class ScheduleJob implements Serializable{
 
	/** 任务执行对象 */
	private String jobScheduler;
	/** 任务id */
	private String jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务分组 */
	private String jobGroup;
	/** 任务状态 0禁用 1启用 2删除*/
	private String jobStatus;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String desc;
	/** 创建日期*/
	private String createTime;
	/** 创建人*/
	private String operUser;
	/** 创建人*/
	private Class taskClass;
	/** 执行次数*/
	private long execCount;
	/** 触发器*/
	private String trriger;
	/** 调用方式*/
	private Integer postType;
	/** 调用URL*/
	private String taskUrl;
	/** 调用参数*/
	private String params;
	
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jobGroup == null) ? 0 : jobGroup.hashCode());
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result
				+ ((taskClass == null) ? 0 : taskClass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleJob other = (ScheduleJob) obj;
		if (jobGroup == null) {
			if (other.jobGroup != null)
				return false;
		} else if (!jobGroup.equals(other.jobGroup))
			return false;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (taskClass == null) {
			if (other.taskClass != null)
				return false;
		} else if (!taskClass.equals(other.taskClass))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ScheduleJob [jobScheduler=" + jobScheduler + ", jobId=" + jobId
				+ ", jobName=" + jobName + ", jobGroup=" + jobGroup
				+ ", jobStatus=" + jobStatus + ", cronExpression="
				+ cronExpression + ", desc=" + desc + ", createTime="
				+ createTime + ", operUser=" + operUser + ", taskClass="
				+ taskClass + ", execCount=" + execCount + ", trriger="
				+ trriger + "]";
	}
	public Class getTaskClass() {
		return taskClass;
	}
	public void setTaskClass(Class taskClass) {
		this.taskClass = taskClass;
	}
	public String getJobScheduler() {
		return jobScheduler;
	}
	public void setJobScheduler(String jobScheduler) {
		this.jobScheduler = jobScheduler;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public Integer getPostType() {
		return postType;
	}
	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	public String getTaskUrl() {
		return taskUrl;
	}
	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOperUser() {
		return operUser;
	}
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
	public long getExecCount() {
		return execCount;
	}
	public void setExecCount(long execCount) {
		this.execCount = execCount;
	}
	public String getTrriger() {
		return trriger;
	}
	public void setTrriger(String trriger) {
		this.trriger = trriger;
	} 
	
	
    
}
