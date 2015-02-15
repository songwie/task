package com.songwie.task.task;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.songwie.task.base.constant.TaskConstant;
import com.songwie.task.base.log.ErrorLog;
import com.songwie.task.base.util.HttpPostUtil;
import com.songwie.task.model.JobResultBean;
import com.songwie.task.model.ScheduleJobBean;

@Component
public class XuriTask extends QuartzJobBean implements Serializable{

	private static final long serialVersionUID = 7026615035981953235L;

	private static Logger log = LoggerFactory.getLogger(XuriTask.class);
	private static Logger error = LoggerFactory.getLogger(ErrorLog.class);


	protected void task(JobExecutionContext context){
		log.info("#######################task exec start ####################### ");
		log.info("#######################task thread:"+ Thread.currentThread().getName() +" ####################### ");
		ScheduleJobBean jobdata = null;
		try {
			JobDetail jobDetail = context.getJobDetail();
			Trigger trigger = context.getTrigger();
			jobdata = ScheduleJobBean.find(Integer.valueOf(jobDetail.getKey().getName()));
			log.info("#######################"+ jobdata +" ####################### ");

			String rs =  "";
			if(jobdata==null){
				log.info("#######################  no task exec ####################### ");
				return;
			}
			int postType = jobdata.getPostType();
			String postParams = jobdata.getParams();
			String postUrl  = jobdata.getTaskUrl();
			if(postType==TaskConstant.POST_TYPE_GET){
				rs = HttpPostUtil.get(postUrl);
				log.info("#######################task exec result:"+ rs +" ####################### ");

			}else {
				Map<String, String> reqMap = new HashMap<String, String>();
				if(!Strings.isNullOrEmpty( postParams)){
					String params[] = postParams.split(",");
					for(String param:params){
						String datas[] = param.split("=");
						reqMap.put(datas[0], datas[1]);
					}
				}

				rs = HttpPostUtil.postFormNotNull(postUrl, reqMap);
				log.info("#######################task exec result:"+ rs +" ####################### ");

			}
			JobResultBean jobResultBean = new JobResultBean();
			jobResultBean.setResult(rs);
			jobResultBean.setJobName(jobdata.getDesc());
			jobResultBean.setCreateTime(Calendar.getInstance());
			jobResultBean.setJobId(jobDetail.getKey().getName());
			jobResultBean.merge();

		} catch (Exception e) {
			error.error("#######################task error#######################");
			error.error("#######################"+ jobdata +" ####################### ");
			error.error("error:" +e.getMessage(),e);


		}
		log.info("#######################task exec end ####################### ");



	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		this.task(context);
	}

}
