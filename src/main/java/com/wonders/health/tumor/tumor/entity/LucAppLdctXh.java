/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 徐汇肺癌LDCT预约记录表实体
 * @author sunyang
 */
public class LucAppLdctXh extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String manageid;	// 个人管理编号

	@Length(max=32)
	private String appCheckCode;	// LDCT预约检查号码

	
	private Date appCheckDate;	// LDCT预约检查日期

	@Length(max=10)
	private String appCheckTime;	// LDCT预约检查时间

	@Length(max=32)
	private String appointmentDoc;	// 预约医生ID

	@Length(max=32)
	private String appointmentOrg;	// 预约医疗机构代码

	
	private Date appointmentDate;	// 预约日期


	public LucAppLdctXh() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("manageid")
	public String getManageid() {
		return manageid;
	}

	public void setManageid(String manageid) {
		this.manageid = manageid;
	}

	@JsonProperty("appCheckCode")
	public String getAppCheckCode() {
		return appCheckCode;
	}

	public void setAppCheckCode(String appCheckCode) {
		this.appCheckCode = appCheckCode;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("appCheckDate")
	public Date getAppCheckDate() {
		return appCheckDate;
	}

	public void setAppCheckDate(Date appCheckDate) {
		this.appCheckDate = appCheckDate;
	}

	@JsonProperty("appCheckTime")
	public String getAppCheckTime() {
		return appCheckTime;
	}

	public void setAppCheckTime(String appCheckTime) {
		this.appCheckTime = appCheckTime;
	}

	@JsonProperty("appointmentDoc")
	public String getAppointmentDoc() {
		return appointmentDoc;
	}

	public void setAppointmentDoc(String appointmentDoc) {
		this.appointmentDoc = appointmentDoc;
	}

	@JsonProperty("appointmentOrg")
	public String getAppointmentOrg() {
		return appointmentOrg;
	}

	public void setAppointmentOrg(String appointmentOrg) {
		this.appointmentOrg = appointmentOrg;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("appointmentDate")
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


}