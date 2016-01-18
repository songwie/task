package com.songwie.task.base.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	private static Logger log = LoggerFactory.getLogger(LogAspect.class);
	private static Logger error = LoggerFactory.getLogger(ErrorLog.class);

	//定义一个日志切入点
	@Pointcut("within(com.songwie.task.web..*)")
	public void LogPointCut(){

	}

	//日志记录 advice
	@Before(value="@annotation(LogAnnotation)" ,argNames="LogAnnotation")
	public void eLog(LogAnnotation LogAnnotation) throws Throwable{
		log.info(LogAnnotation.name()+":"+LogAnnotation.message());
	}

	// 异常通知
	@AfterThrowing(pointcut = "LogPointCut()",throwing = "e")
	public void myThrowingAdvice(JoinPoint jionpoint, Exception e) {
	        // 获取被调用的类名
	        String targetClassName = jionpoint.getTarget().getClass().getName();
	        // 获取被调用的方法名
	        String targetMethodName = jionpoint.getSignature().getName();

	        // 日志格式字符串
	        String logInfoText = "Exception ：executing class " + targetClassName + " method " + targetMethodName + " happends an error " + e.getLocalizedMessage();
	        // 将日志信息写入配置的文件中
	        log.info(logInfoText);
	        error.error(logInfoText,e);


	 }


}
