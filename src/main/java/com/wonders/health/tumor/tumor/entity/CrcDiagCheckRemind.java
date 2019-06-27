/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 大肠癌诊断检查提醒表实体
 * @author sunyang
 */
public class CrcDiagCheckRemind extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String crcCheckId;	// 大肠癌初筛信息登记id

	@NotNull
	private Integer checkYear;	// 初筛年度

	
	private Date firstRemindDate;	// 第一次提醒日期

	@Length(max=2)
	private String firstRemindType;	// 第一次提醒方式

	
	private Date secondRemindDate;	// 第二次提醒日期

	@Length(max=2)
	private String secondRemindType;	// 第二次提醒方式

	
	private Date thirdRemindDate;	// 第三次提醒日期

	@Length(max=2)
	private String thirdRemindType;	// 第三次提醒方式

	
	private Date perRemindDate;	// 计划提醒时间

	@Length(max=2)
	private String remindStatus;	// 提醒状态


	public CrcDiagCheckRemind() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("crcCheckId")
	public String getCrcCheckId() {
		return crcCheckId;
	}

	public void setCrcCheckId(String crcCheckId) {
		this.crcCheckId = crcCheckId;
	}

	@JsonProperty("checkYear")
	public Integer getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(Integer checkYear) {
		this.checkYear = checkYear;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("firstRemindDate")
	public Date getFirstRemindDate() {
		return firstRemindDate;
	}

	public void setFirstRemindDate(Date firstRemindDate) {
		this.firstRemindDate = firstRemindDate;
	}

	@JsonProperty("firstRemindType")
	public String getFirstRemindType() {
		return firstRemindType;
	}

	public void setFirstRemindType(String firstRemindType) {
		this.firstRemindType = firstRemindType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("secondRemindDate")
	public Date getSecondRemindDate() {
		return secondRemindDate;
	}

	public void setSecondRemindDate(Date secondRemindDate) {
		this.secondRemindDate = secondRemindDate;
	}

	@JsonProperty("secondRemindType")
	public String getSecondRemindType() {
		return secondRemindType;
	}

	public void setSecondRemindType(String secondRemindType) {
		this.secondRemindType = secondRemindType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("thirdRemindDate")
	public Date getThirdRemindDate() {
		return thirdRemindDate;
	}

	public void setThirdRemindDate(Date thirdRemindDate) {
		this.thirdRemindDate = thirdRemindDate;
	}

	@JsonProperty("thirdRemindType")
	public String getThirdRemindType() {
		return thirdRemindType;
	}

	public void setThirdRemindType(String thirdRemindType) {
		this.thirdRemindType = thirdRemindType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("perRemindDate")
	public Date getPerRemindDate() {
		return perRemindDate;
	}

	public void setPerRemindDate(Date perRemindDate) {
		this.perRemindDate = perRemindDate;
	}

	@JsonProperty("remindStatus")
	public String getRemindStatus() {
		return remindStatus;
	}

	public void setRemindStatus(String remindStatus) {
		this.remindStatus = remindStatus;
	}


}