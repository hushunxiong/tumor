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
 * 徐汇肺癌专病门诊预约记录表实体
 * @author sunyang
 */
public class LucAppDiagXh extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String manageid;	// 个人管理编号

	@Length(max=32)
	private String appDiagCheckCode;	// 专病门诊预约检查号码

	
	private Date appDiagCheckDate;	// 专病门诊预约检查日期

	@Length(max=20)
	private String appDiagCheckTime;	// 专病门诊预约检查时间

	@Length(max=32)
	private String appDiagCheckKsdm;	// 专病门诊预约检查科室代码

	@Length(max=50)
	private String appDiagCheckKsmc;	// 专病门诊预约检查科室名称

	@Length(max=32)
	private String appDiagCheckYsdm;	// 专病门诊预约检查医生代码

	@Length(max=32)
	private String appDiagCheckYsxm;	// 专病门诊预约检查医生姓名

	@Length(max=20)
	private String appDiagCheckHx;	// 专病门诊预约检查号序

	@Length(max=32)
	private String appointmentDoc;	// 预约医生ID

	@Length(max=32)
	private String appointmentOrg;	// 预约医疗机构代码

	
	private Date appointmentDate;	// 预约日期


	public LucAppDiagXh() {
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

	@JsonProperty("appDiagCheckCode")
	public String getAppDiagCheckCode() {
		return appDiagCheckCode;
	}

	public void setAppDiagCheckCode(String appDiagCheckCode) {
		this.appDiagCheckCode = appDiagCheckCode;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("appDiagCheckDate")
	public Date getAppDiagCheckDate() {
		return appDiagCheckDate;
	}

	public void setAppDiagCheckDate(Date appDiagCheckDate) {
		this.appDiagCheckDate = appDiagCheckDate;
	}

	@JsonProperty("appDiagCheckTime")
	public String getAppDiagCheckTime() {
		return appDiagCheckTime;
	}

	public void setAppDiagCheckTime(String appDiagCheckTime) {
		this.appDiagCheckTime = appDiagCheckTime;
	}

	@JsonProperty("appDiagCheckKsdm")
	public String getAppDiagCheckKsdm() {
		return appDiagCheckKsdm;
	}

	public void setAppDiagCheckKsdm(String appDiagCheckKsdm) {
		this.appDiagCheckKsdm = appDiagCheckKsdm;
	}

	@JsonProperty("appDiagCheckKsmc")
	public String getAppDiagCheckKsmc() {
		return appDiagCheckKsmc;
	}

	public void setAppDiagCheckKsmc(String appDiagCheckKsmc) {
		this.appDiagCheckKsmc = appDiagCheckKsmc;
	}

	@JsonProperty("appDiagCheckYsdm")
	public String getAppDiagCheckYsdm() {
		return appDiagCheckYsdm;
	}

	public void setAppDiagCheckYsdm(String appDiagCheckYsdm) {
		this.appDiagCheckYsdm = appDiagCheckYsdm;
	}

	@JsonProperty("appDiagCheckYsxm")
	public String getAppDiagCheckYsxm() {
		return appDiagCheckYsxm;
	}

	public void setAppDiagCheckYsxm(String appDiagCheckYsxm) {
		this.appDiagCheckYsxm = appDiagCheckYsxm;
	}

	@JsonProperty("appDiagCheckHx")
	public String getAppDiagCheckHx() {
		return appDiagCheckHx;
	}

	public void setAppDiagCheckHx(String appDiagCheckHx) {
		this.appDiagCheckHx = appDiagCheckHx;
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