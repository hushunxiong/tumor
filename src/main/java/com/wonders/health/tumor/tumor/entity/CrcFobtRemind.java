/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 大肠癌便隐血检查提醒表实体
 * @author menglianghai
 */
@Data
public class CrcFobtRemind extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String crcCheckId;	// 大肠癌初筛信息登记id

	@NotNull
	private Integer checkYear;	// 初筛年度

	
	private Date firstFobtRemindDate;	// 第一次便隐血提醒日期

	@Length(max=2)
	private String firstFobtRemindType;	// 第一次便隐血提醒方式 字典表 id=60047

	
	private Date secondFobtRemindDate;	// 第二次便隐血提醒日期

	@Length(max=2)
	private String secondFobtRemindType;	// 第二次便隐血提醒方式 字典表 id=60047

	
	private Date perFobtRemindDate;	// 计划提醒时间

	@Length(max=2)
	private String fobtRemindStatus;	// 提醒状态 字典表 id=60049


	public CrcFobtRemind() {
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
	@JsonProperty("firstFobtRemindDate")
	public Date getFirstFobtRemindDate() {
		return firstFobtRemindDate;
	}

	public void setFirstFobtRemindDate(Date firstFobtRemindDate) {
		this.firstFobtRemindDate = firstFobtRemindDate;
	}

	@JsonProperty("firstFobtRemindType")
	public String getFirstFobtRemindType() {
		return firstFobtRemindType;
	}

	public void setFirstFobtRemindType(String firstFobtRemindType) {
		this.firstFobtRemindType = firstFobtRemindType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("secondFobtRemindDate")
	public Date getSecondFobtRemindDate() {
		return secondFobtRemindDate;
	}

	public void setSecondFobtRemindDate(Date secondFobtRemindDate) {
		this.secondFobtRemindDate = secondFobtRemindDate;
	}

	@JsonProperty("secondFobtRemindType")
	public String getSecondFobtRemindType() {
		return secondFobtRemindType;
	}

	public void setSecondFobtRemindType(String secondFobtRemindType) {
		this.secondFobtRemindType = secondFobtRemindType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("perFobtRemindDate")
	public Date getPerFobtRemindDate() {
		return perFobtRemindDate;
	}

	public void setPerFobtRemindDate(Date perFobtRemindDate) {
		this.perFobtRemindDate = perFobtRemindDate;
	}

	@JsonProperty("fobtRemindStatus")
	public String getFobtRemindStatus() {
		return fobtRemindStatus;
	}

	public void setFobtRemindStatus(String fobtRemindStatus) {
		this.fobtRemindStatus = fobtRemindStatus;
	}


}