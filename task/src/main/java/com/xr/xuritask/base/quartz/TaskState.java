package com.xr.xuritask.base.quartz;


public enum TaskState {

	None("完成或不存在"), 
	NORMAL("正常"), 
	PAUSED("暂停"), 
	COMPLETE("已经发送执行命令"), 
	BLOCKED("线程阻塞"), 
	ERROR("出现错误") ; 

    private String value;

    private TaskState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static TaskState getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(TaskState v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException();
    }
	
}
