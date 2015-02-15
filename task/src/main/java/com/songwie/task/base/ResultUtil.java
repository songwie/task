package com.songwie.task.base;

import com.songwie.task.base.constant.TaskConstant;
import com.songwie.task.base.quartz.TaskState;

/**
 * 工具类
 * @author user
 *
 */
public class ResultUtil {

	/**
	 * 结果集处理
	 * @param str
	 * @return
	 */
	public static String parseStr(String str){
		if(str==null){
			return "";
		}else{
			return str;
		}
	}

	/**
	 * 开启状态
	 * @param str
	 * @return
	 */
	public static String parseTaskState(String state){
		if(TaskConstant.None.equals(state)){
			return TaskState.None.getValue();
		}else if(TaskConstant.NORMAL.equals(state)){
			return TaskState.NORMAL.getValue();
		}else if(TaskConstant.PAUSED.equals(state)){
			return TaskState.PAUSED.getValue();
		}else if(TaskConstant.COMPLETE.equals(state)){
			return TaskState.COMPLETE.getValue();
		}else if(TaskConstant.BLOCKED.equals(state)){
			return TaskState.BLOCKED.getValue();
		}else if(TaskConstant.ERROR.equals(state)){
			return TaskState.ERROR.getValue();
		}else{
			return "";
		}
	}
	/**
	 * 调用方式
	 * @param str
	 * @return
	 */
	public static String parseTaskState(int state){
		 if(state==TaskConstant.POST_TYPE_POST){
			 return "POST";
		 }else {
			return "GET";
		}
	}

}
