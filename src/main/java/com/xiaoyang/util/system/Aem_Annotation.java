package com.xiaoyang.util.system;

import java.lang.annotation.Annotation;

public interface Aem_Annotation extends Annotation {

	 public abstract boolean isPrimary();

	 public abstract boolean isNull();

	 public abstract boolean isStringClob();

	 public abstract boolean isTimestamp();

	 public abstract boolean isTransient();
}
