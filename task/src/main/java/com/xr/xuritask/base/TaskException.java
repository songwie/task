package com.xr.xuritask.base;

/**
 * 公用异常处理类
 * @author user
 *
 */
public class TaskException extends RuntimeException{
	static final long serialVersionUID = -7034897190745766939L;

    public TaskException() {
        super();
    }
    public TaskException(String message) {
        super(message);
    }
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
    public TaskException(Throwable cause) {
        super(cause);
    }
}
