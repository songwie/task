package com.songwie.task.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songwie.task.base.TaskException;
import com.songwie.task.base.constant.TaskConstant;
import com.songwie.task.dao.AllDaoImpl;
import com.songwie.task.model.OperUser;
import com.songwie.task.service.ILoginService;


@Service("loginService")
@Scope("prototype")
@Transactional
public class LoginService implements ILoginService{
	@Autowired
	private AllDaoImpl dao;

	//登录
	//默认商户或机构号为登录用户名
	public Map<String, String> LoginAuthen(String username,String pwd,HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();

		OperUser operUser = null;

		operUser = dao.getOperUserByUserNameAndPassoword(username, pwd);
		if(operUser==null){
			operUser = dao.getOperUserByTelphone(String.valueOf(username));
			if(operUser==null){
				throw new TaskException("登录用户或手机不存在！");
			}
		}
		String type = operUser.getOperType();
		String password = operUser.getOperPassword();
		if(password.equals(pwd)){
			map.put(TaskConstant.OPER_USER_ID,String.valueOf(operUser.getOperNo()));
			map.put(TaskConstant.OPER_USER_NAME, operUser.getOperUserName());
			map.put(TaskConstant.OPER_USER_REAL_NAME, operUser.getOperRealName());
		}else {
			throw new TaskException("密码错误！");
		}


		return map;
	}

	//首页
	@Override
	public Map<String, String> fristPage(String type, String merchanId,String userId) {
        Map<String, String> map = new HashMap<String, String>();

		return map;
	}




}
