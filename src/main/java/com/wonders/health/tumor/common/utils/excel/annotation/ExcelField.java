/**
 * 
 */
package com.wonders.health.tumor.common.utils.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解定义
 * 
 * @version 2013-03-10
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	/**
	 * excel 头部title
	 * @return
	 */
	String title();

	/**
	 * 字段默认值
	 * @return
	 */
	String defValue() default "";

}
