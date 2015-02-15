package com.songwie.task.web.Login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.songwie.task.base.TaskException;
import com.songwie.task.base.constant.TaskConstant;
import com.songwie.task.base.loginCheck.CheckLoginAnnotation;
import com.songwie.task.base.loginCheck.ISLOGIN;
import com.songwie.task.service.ILoginService;

@RequestMapping("/")
@Controller("loginController")
@Scope("prototype")
public class LoginController {


	@Autowired
	@Qualifier("loginService")
	ILoginService service;


	@RequestMapping("/_login")
    public String loginInit(Model model, HttpServletRequest request){
    	return "newlogin";
    }
	@RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){
    	try{
    		String Merchant_No = request.getParameter("Merchant_No");
    		String pwd = request.getParameter("pwd");

    		if(Merchant_No==null||pwd==null){
    			return "redirect:/";
    		}
    		Map<String, String> map = service.LoginAuthen(Merchant_No, pwd,request);
    		HttpSession session = request.getSession();
    		session.setAttribute(TaskConstant.OPER_USER_ID, map.get(TaskConstant.OPER_USER_ID));
    		session.setAttribute(TaskConstant.OPER_USER_NAME, map.get(TaskConstant.OPER_USER_NAME));


    	}catch(TaskException e){
    		model.addAttribute("error", e.getMessage());
    		return "newlogin";
    	}

    	return "redirect:/taskmgr/taskmgr";
    }

	@RequestMapping("/loginOut")
    public String loginOut(Model model, HttpServletRequest request){
    	try{
    		 HttpSession session = request.getSession();
    		 session.invalidate();
    	}catch(Exception e){
    		model.addAttribute("error", e.getMessage());
    		return "redirect:/";
    	}

    	return "redirect:/";
    }

	/*@RequestMapping("/fristPage")
    public String fristPage(Model model, HttpServletRequest request){

		model.addAttribute("operName", SessionCertification.getOperUserRealName());
    	return "loginindex";
    }*/

	@RequestMapping("/taskmgr")
	@CheckLoginAnnotation(isLogin=ISLOGIN.YES)
    public String taskMgr(Model model, HttpServletRequest request){
    	return "taskmgr";
    }

}
