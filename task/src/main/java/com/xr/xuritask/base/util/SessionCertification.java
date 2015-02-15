package com.xr.xuritask.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xr.xuritask.base.constant.TaskConstant;

@Component
public class SessionCertification {

	//获取当前操作员
	public static String getOperUserID(){
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String operUser = (String) session.getAttribute(TaskConstant.OPER_USER_ID);
		return operUser;
	}

	//获取当前操作员 实际名字
	public static String getOperUserRealName() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String operUser = (String) session.getAttribute(TaskConstant.OPER_USER_REAL_NAME);
		return operUser;
	}
	//获取当前操作员
	public static String getOperUserName() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String operUser = (String) session.getAttribute(TaskConstant.OPER_USER_NAME);
		return operUser;
	}

}
