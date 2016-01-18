package com.songwie.task.base.loginCheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.songwie.task.base.constant.TaskConstant;

/**
 * 检查是否登陆的AOP（对登陆注解添加的AOP）
 * @author
 *
 */
@Aspect
@Component
public class CheckLoginAspect {
	 @Before(value="@annotation(CheckLoginAnnotation)")
	 public void checkPermission(JoinPoint jp,CheckLoginAnnotation CheckLoginAnnotation) throws Exception {
		ISLOGIN isLogin = CheckLoginAnnotation.isLogin ();
		if(isLogin.equals(ISLOGIN.YES)){
			HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes)
					RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			String userid = (String) session.getAttribute(TaskConstant.OPER_USER_ID);
			if(userid==null||userid.equals("")){
				throw new Exception("请先登录！");
			}
		}

	}
}
