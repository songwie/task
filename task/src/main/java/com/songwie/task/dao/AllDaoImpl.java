package com.songwie.task.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.songwie.task.base.dao.CommonDAO;
import com.songwie.task.base.quartz.ScheduleJob;
import com.songwie.task.model.JobResultBean;
import com.songwie.task.model.OperUser;
import com.songwie.task.model.ScheduleJobBean;


@Component
public class AllDaoImpl {
	
	@Autowired
	private CommonDAO commonDAO;
	
	public OperUser getOperUserByUserNameAndPassoword(String username,String password){
		
		List<OperUser> operUsers = commonDAO.getEntityManager().createQuery("select o from OperUser o where o.operUserName=?1 and o.operPassword=?2", OperUser.class)
    			.setParameter(1, username)
    			.setParameter(2, password)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }
	 
	public OperUser getOperUserByTelphone(String telphone){
    	List<OperUser> operUsers = commonDAO.getEntityManager().createQuery("select o from OperUser o where o.operPhone=:telphone ", OperUser.class)
    			.setParameter("telphone", telphone)
    			.getResultList();

    	if(operUsers.size() > 0){
    		return operUsers.get(0);
    	}
    	return null;
    }
    
	@SuppressWarnings("unchecked")
	public List<ScheduleJobBean> findJobsByList(List<ScheduleJob> jobs ) {
		List<Integer> ids = new ArrayList<Integer>();
		List<ScheduleJobBean> dataBeans = new ArrayList<ScheduleJobBean>();
		for (ScheduleJob job : jobs) {
			ids.add(Integer.valueOf(job.getJobId()));
		}
		if(ids.isEmpty()){
			return dataBeans;
		}
		return commonDAO.getEntityManager().createQuery("from ScheduleJobBean where Id in (:ids ) ").setParameter("ids", ids).getResultList();
	}
	
	//获取某一job的resultSize条执行记录
	public List<JobResultBean> getJobResultBeansById(String myJobid, int resultSize){
		String sql = "select o from JobResultBean o where o.jobId=?1 order by o.createTime desc";
		List<JobResultBean> list = commonDAO.getEntityManager().createQuery(sql,JobResultBean.class)
				.setParameter(1, myJobid)
				.setMaxResults(resultSize)
				.getResultList();
		return list;
	}

	//取得前n条执行记录
	public List<JobResultBean> getTopnJobResultBeans(int resultSize){
		String sql = "select o from JobResultBean o order by o.createTime desc";
		List<JobResultBean> list = commonDAO.getEntityManager().createQuery(sql,JobResultBean.class)
				.setMaxResults(resultSize)
				.getResultList();
		return list;
	}
	
	public ScheduleJobBean find(Integer id) {
		if (id == null)
			return null;
		return commonDAO.getEntityManager().find(ScheduleJobBean.class, id);
	}
	
	public JobResultBean findJobResultByID(Integer id) {
		if (id == null)
			return null;
		return commonDAO.getEntityManager().find(JobResultBean.class, id);
	}
	
}
