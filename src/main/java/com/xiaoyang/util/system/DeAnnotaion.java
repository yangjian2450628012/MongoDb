package com.xiaoyang.util.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @ClassName: DeAnnotaion 
* 自定义注解	
*  
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface DeAnnotaion {
	boolean is_autoincrement() default false;  //判断是否自增
	
	boolean isPrimary() default false; //是否是主键
	
	boolean isNull() default true; //是否能为空

	 public boolean isStringBlob() default false;

	 public boolean isTimestamp() default false;

	 public boolean isTransient() default false;
}
