package com.xr.xuritask.web.TaskManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.xr.xuritask.base.TaskException;
import com.xr.xuritask.base.loginCheck.CheckLoginAnnotation;
import com.xr.xuritask.base.loginCheck.ISLOGIN;
import com.xr.xuritask.base.quartz.ScheduleJob;
import com.xr.xuritask.base.util.SessionCertification;
import com.xr.xuritask.model.JobResultBean;
import com.xr.xuritask.model.ScheduleJobBean;
import com.xr.xuritask.service.ITaskManagerService;

@RequestMapping("/taskmgr")
@Controller("taskManagerController")
@Scope("prototype")
public class TaskManagerController {

	@Autowired
	ITaskManagerService service;

	@RequestMapping("/taskmgr")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
    public String taskMgr(Model model, HttpServletRequest request){
		String operId = SessionCertification.getOperUserID();

		List<Map<String, Object>> data = service.getTasks(operId );

		model.addAttribute("taskList", data);

    	return "taskmgr";
    }

	@RequestMapping("/getTasks")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String getTasks(Model model, HttpServletRequest request) {
		String operId = SessionCertification.getOperUserID();

		List<Map<String, Object>> data = service.getTasks(operId );

		model.addAttribute("taskList", data);

    	return "redirect:/taskmgr/taskmgr";
	}

	//任务执行记录
	@RequestMapping("/getTaskHis")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String getTaskHis(Model model, HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		List<JobResultBean> list = null;
		if(Strings.isNullOrEmpty(taskId)){
			list = service.getAllTopNTaskHis();
		}
		else {
			list = service.getTaskHisByTaskId(taskId);
		}
		model.addAttribute("taskList", list);
    	return "taskhis";
	}

	//根据Id获取Task信息

	@RequestMapping("/getTaskById")
	//@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	@ResponseBody
	public String getTaskById(Model model, HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		ScheduleJobBean task = null;
		if(Strings.isNullOrEmpty(taskId)){
			//no taskId
		}
		else {
			task =  service.getTaskById(taskId);
		}
		//convet to json
		Gson gson = new Gson();
		String jsonString = gson.toJson(task,ScheduleJobBean.class);

		return jsonString;
	}

	@RequestMapping("/getTaskExecs")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String getTaskExecs(Model model, HttpServletRequest request) {
		String operId = SessionCertification.getOperUserID();

		List<Map<String, Object>> data = service.getTaskExecs(operId);

		model.addAttribute("taskList", data);

    	return "taskexecs";
	}

	@RequestMapping("/getTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String getTask(Model model, HttpServletRequest request) {
        String operId = SessionCertification.getOperUserID();

		service.getTasks(operId);

    	return "redirect:/taskmgr/taskmgr";
	}

	@RequestMapping("/saveTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String saveTask(Model model, HttpServletRequest request) throws Exception {
        String operId = SessionCertification.getOperUserID();

        String taskname = request.getParameter("taskname");
        String taskclass = request.getParameter("taskclass");
        String taskurl = request.getParameter("taskurl");
        String taskpost = request.getParameter("taskpost");
        String taskparams = request.getParameter("taskparams");
        String cronexpression = request.getParameter("cronexpression");
        String taskid = request.getParameter("taskid");

        if(taskclass.indexOf("class ")!=-1){
        	taskclass = taskclass.replace("class ", "");
        }

        Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);

		try {
			 ScheduleJob job = new ScheduleJob();
			 job.setJobId(taskid);
	         job.setDesc(taskname);
	         job.setJobName(taskname);
	         job.setTaskUrl(taskurl);
	         job.setPostType(Integer.valueOf(taskpost));
	         job.setParams(taskparams);
	         Class<?> tassClass = Class.forName(taskclass);
	         job.setTaskClass(tassClass);
	         job.setCronExpression(cronexpression);

	         if(Strings.isNullOrEmpty(taskid)){
		 		 service.saveTask(operId, job);
	         }else {
	        	 service.updateTask(operId, taskid, job);
			}

		} catch (TaskException e) {
			map.put("success", false);
			map.put("error", e.getMessage());
		}



    	return "forward:/taskmgr/taskmgr";
	}

	@RequestMapping("/updateTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String updateTask(Model model, HttpServletRequest request) throws Exception {
        String operId = SessionCertification.getOperUserID();

        String taskname = request.getParameter("taskname");
        String taskclass = request.getParameter("taskclass");
        String taskurl = request.getParameter("taskurl");
        String taskpost = request.getParameter("taskpost");
        String taskparams = request.getParameter("taskparams");
        String cronexpression = request.getParameter("cronexpression");
        String taskid = request.getParameter("taskid");

        if(taskclass.indexOf("class ")!=-1){
        	taskclass = taskclass.replace("class ", "");
        }

        Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);

		try {
			 ScheduleJob job = new ScheduleJob();
			 job.setJobId(taskid);
	         job.setDesc(taskname);
	         job.setJobName(taskname);
	         job.setTaskUrl(taskurl);
	         job.setPostType(Integer.valueOf(taskpost));
	         job.setParams(taskparams);
	         Class<?> tassClass = Class.forName(taskclass);
	         job.setTaskClass(tassClass);
	         job.setCronExpression(cronexpression);

	 		 service.updateTask(operId, taskid,job );

		} catch (TaskException e) {
			map.put("success", false);
			map.put("error", e.getMessage());
		}




    	return "redirect:/taskmgr/taskmgr";

	}

	@RequestMapping("/execTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String execTask(Model model, HttpServletRequest request) {
		String taskid = request.getParameter("taskid");
		String operId = SessionCertification.getOperUserID();

		service.execTask(operId, taskid );

    	return "redirect:/taskmgr/taskmgr";
	}

	@RequestMapping("/deleteTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String deleteTask(Model model, HttpServletRequest request) {
		String taskid = request.getParameter("taskid");
		String operId = SessionCertification.getOperUserID();

		service.deleteTask(operId, taskid );

    	return "redirect:/taskmgr/taskmgr";

	}

	@RequestMapping("/resumeTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String resumeTask(Model model, HttpServletRequest request) {
		String taskid = request.getParameter("taskid");
		String operId = SessionCertification.getOperUserID();

		service.resumeTask(operId, taskid );

    	return "redirect:/taskmgr/taskmgr";

	}

	@RequestMapping("/stopTask")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
	public String stopTask(Model model, HttpServletRequest request) {
		String taskid = request.getParameter("taskid");
		String operId = SessionCertification.getOperUserID();

		service.stopTask(operId, taskid );

    	return "redirect:/taskmgr/taskmgr";

	}
}
