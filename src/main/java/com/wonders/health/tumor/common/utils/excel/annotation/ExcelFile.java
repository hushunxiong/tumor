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
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelFile {

	/**
	 * excel 文件名称
	 * @return
	 */
	String fileName();

	/**
     * 文件后缀
	 * @return
     */
	String subfix() default ".xlsx";

}
