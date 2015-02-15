package com.xr.xuritask.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import com.xr.xuritask.base.quartz.ScheduleJob;


@Configurable
@Entity
@Table(name = "TTASK_JOB_RESULT")
public class JobResultBean implements Serializable {

	//获取某一job的resultSize条执行记录
	public static List<JobResultBean> getJobResultBeansById(String myJobid, int resultSize){
		String sql = "select o from JobResultBean o where o.jobId=?1 order by o.createTime desc";
		List<JobResultBean> list = entityManager().createQuery(sql,JobResultBean.class)
				.setParameter(1, myJobid)
				.setMaxResults(resultSize)
				.getResultList();
		return list;
	}
	
	//取得前n条执行记录
	public static List<JobResultBean> getTopnJobResultBeans(int resultSize){
		String sql = "select o from JobResultBean o order by o.createTime desc";
		List<JobResultBean> list = entityManager().createQuery(sql,JobResultBean.class)
				.setMaxResults(resultSize)
				.getResultList();
		return list;
	}
	
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
	/** 创建日期*/
	@Column(name = "D_CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Calendar createTime;
	/** 调用结果*/
	@Column(name = "V_RESULT" )
	private String result;


	/**
	 *
	 */
	@javax.persistence.PersistenceContext
	transient EntityManager entityManager;

	public static final EntityManager entityManager() {
		EntityManager em = new JobResultBean().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
		this.entityManager.flush();
	}

	public static JobResultBean find(Integer id) {
		if (id == null)
			return null;
		return entityManager().find(JobResultBean.class, id);
	}

	@Transactional
	public JobResultBean merge() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		JobResultBean merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			JobResultBean attached = JobResultBean.find(this.Id);
			this.entityManager.remove(attached);
		}
	}

	@Transactional
	public void flush() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.flush();
	}

	@Transactional
	public void clear() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.clear();
	}


	@SuppressWarnings("unchecked")
	public static List<JobResultBean> findJobsByList(List<ScheduleJob> jobs ) {
		List<Integer> ids = new ArrayList<Integer>();
		List<JobResultBean> dataBeans = new ArrayList<JobResultBean>();
		for (ScheduleJob job : jobs) {
			ids.add(Integer.valueOf(job.getJobId()));
		}
		if(ids.isEmpty()){
			return dataBeans;
		}
		return entityManager().createQuery("from ScheduleJobBean where Id in (:ids ) ").setParameter("ids", ids).getResultList();
	}






	@Override
	public String toString() {
		return "JobResultBean [Id=" + Id + ", jobId=" + jobId + ", jobName="
				+ jobName + ", result=" + result + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
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
		JobResultBean other = (JobResultBean) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
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
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
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

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public void setResult(String result) {
		this.result = result;
	}


}
