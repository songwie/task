package com.songwie.task.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "TTASK_JOB")
public class ScheduleJobBean implements Serializable {
	@PersistenceContext
    transient EntityManager entityManager;
	
	/** 任务id */
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Integer Id;
	/** 任务ID */
	@Column(name = "V_JOB_ID" )
	private String jobId;
	/** 任务名称 */
	@Column(name = "V_JOB_NAME", length = 255)
	private String jobName;
	/** 任务状态 0禁用 1启用 2删除*/
	@Column(name = "N_JOB_STATUS")
	private Integer jobStatus;
	/** 任务描述 */
	@Column(name = "V_JOB_DESC", length = 255)
	private String desc;
	/** 创建日期*/
	@Column(name = "D_CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Calendar createTime;
	/** 创建人*/
	@Column(name = "V_OPER_USER", length = 255)
	private String operUser;
	/** 调用方式*/
	@Column(name = "N_POST_TYPE" )
	private Integer postType;
	/** 调用URL*/
	@Column(name = "V_JOB_URL", length = 255)
	private String taskUrl;
	/** 调用参数*/
	@Column(name = "V_JOB_PARAMS", length = 255)
	private String params;
	/** 调用结果*/
	@Column(name = "V_RESULT" )
	private String result;
	/** 执行计划*/
	@Column(name = "V_CRON_EXPRE" )
	private String cronExpression;
 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		result = prime * result
				+ ((jobStatus == null) ? 0 : jobStatus.hashCode());
		result = prime * result
				+ ((operUser == null) ? 0 : operUser.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result + ((taskUrl == null) ? 0 : taskUrl.hashCode());
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
		ScheduleJobBean other = (ScheduleJobBean) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		if (jobStatus == null) {
			if (other.jobStatus != null)
				return false;
		} else if (!jobStatus.equals(other.jobStatus))
			return false;
		if (operUser == null) {
			if (other.operUser != null)
				return false;
		} else if (!operUser.equals(other.operUser))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		if (taskUrl == null) {
			if (other.taskUrl != null)
				return false;
		} else if (!taskUrl.equals(other.taskUrl))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ScheduleJobBean [jobId=" + jobId + ", jobName=" + jobName
				+ ", jobStatus=" + jobStatus + ", desc=" + desc + ", operUser=" + operUser
				+ ", taskUrl=" + taskUrl + ", params=" + params + "]";
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOperUser() {
		return operUser;
	}
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
	public Integer getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
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

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getResult() {
		return result;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public void setResult(String result) {
		this.result = result;
	}


}
