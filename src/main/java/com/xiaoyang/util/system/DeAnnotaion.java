package com.xiaoyang.util.system;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** 
* @ClassName: DeAnnotaion 
* 自定义注解	
*  
*/
@Retention(RetentionPolicy.RUNTIME)
public @interface DeAnnotaion {
	boolean is_autoincrement() default false;  //判断是否自增
}
