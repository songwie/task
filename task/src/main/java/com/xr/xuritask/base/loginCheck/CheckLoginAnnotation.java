package com.xr.xuritask.base.loginCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义检查登陆情况的注解
 * @author 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckLoginAnnotation {

	ISLOGIN isLogin();
}
