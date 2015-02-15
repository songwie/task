package com.songwie.task.base.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;


public class TaskConstant {

	public static Map<String, String> mapConfig;

	//登录相关
	public static String OPER_USER_ID = "OPER_USER_ID" ;         //用户ID
	public static String OPER_USER_NAME = "OPER_USER_NAME" ;     //用户
	public static String OPER_USER_REAL_NAME = "OPER_USER_REAL_NAME" ;     //用户

	//日期格式常量
	public static String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	public static String DATE_FORMAT_LONG =  "yyyy-MM-dd  HH:mm:ss";
	public static String TIME_FORMAT =  "HH:mm:ss";
	public static DateFormat DATEFORMAT_SHORT = new SimpleDateFormat(DATE_FORMAT_SHORT); ;
	public static DateFormat DATEFORMAT_LONG = new SimpleDateFormat(DATE_FORMAT_LONG); ;
	public static DateFormat TIMEFORMAT = new SimpleDateFormat(TIME_FORMAT); ;

	//任务状态
	public static String None = "None";//
	public static String NORMAL = "NORMAL";//
	public static String PAUSED = "PAUSED";//
	public static String COMPLETE = "COMPLETE";//
	public static String BLOCKED = "BLOCKED";//
	public static String ERROR = "ERROR";//

    //提交方式
	public static int POST_TYPE_POST = 1;
	public static int POST_TYPE_GET = 2;

}
