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
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 肺癌初筛信息登记实体
 * @author menglianghai
 */
public class LucRegcase extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String manageid;	// 个人管理编号

	@Length(max=64)
	@NotNull
	private String idNumber;	// 初筛ID：2位年份+5位机构编码+5位序号

	@NotNull
	private Integer checkYear;	// 初筛年度

	@Length(max=1)
	private String checkResult;	// 判定结果

	@Length(max=32)
	@NotNull
	private String regionCode;	// 初筛数据所属行政区划代码

	@Length(max=32)
	@NotNull
	private String regorg;	// 登记医疗机构代码

	@Length(max=32)
	@NotNull
	private String regdoc;	// 登记医生代码

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regdate;	// 登记日期

	@Length(max=1)
	@NotNull
	private String submitsStatus;	// 数据提交状态

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date submitDate;	// 数据提交日期

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date transDate;	// 转诊单打印日期

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date printDate;	// 通知单打印日期

	@Length(max=1)
	@NotNull
	private String closeStatus;	// 结案状态

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date closeDate;	// 结案日期

	@Length(max=1)
	private String closeResult;	// 结案结果

	@Length(max=1)
	private String checkQualityFlag;	// 初筛登记质控标志

	@Length(max=1)
	private String checkresultQualityFlag;	// 初筛结果通知质控标志

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间

	@Length(max=32)
	private String sourceId;	// 来源代码

	// 一个初筛登记记录关联一个肺癌危险度评估记录
	private LucRiskAssessment lucRiskAssessment;

	public LucRiskAssessment getLucRiskAssessment() {
		return lucRiskAssessment;
	}

	public void setLucRiskAssessment(LucRiskAssessment lucRiskAssessment) {
		this.lucRiskAssessment = lucRiskAssessment;
	}

	public LucRegcase() {
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

	@JsonProperty("idNumber")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@JsonProperty("checkYear")
	public Integer getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(Integer checkYear) {
		this.checkYear = checkYear;
	}

	@JsonProperty("checkResult")
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	@JsonProperty("regionCode")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@JsonProperty("regorg")
	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}

	@JsonProperty("regdoc")
	public String getRegdoc() {
		return regdoc;
	}

	public void setRegdoc(String regdoc) {
		this.regdoc = regdoc;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("regdate")
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@JsonProperty("submitsStatus")
	public String getSubmitsStatus() {
		return submitsStatus;
	}

	public void setSubmitsStatus(String submitsStatus) {
		this.submitsStatus = submitsStatus;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("submitDate")
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("transDate")
	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("printDate")
	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	@JsonProperty("closeStatus")
	public String getCloseStatus() {
		return closeStatus;
	}

	public void setCloseStatus(String closeStatus) {
		this.closeStatus = closeStatus;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("closeDate")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@JsonProperty("closeResult")
	public String getCloseResult() {
		return closeResult;
	}

	public void setCloseResult(String closeResult) {
		this.closeResult = closeResult;
	}

	@JsonProperty("checkQualityFlag")
	public String getCheckQualityFlag() {
		return checkQualityFlag;
	}

	public void setCheckQualityFlag(String checkQualityFlag) {
		this.checkQualityFlag = checkQualityFlag;
	}

	@JsonProperty("checkresultQualityFlag")
	public String getCheckresultQualityFlag() {
		return checkresultQualityFlag;
	}

	public void setCheckresultQualityFlag(String checkresultQualityFlag) {
		this.checkresultQualityFlag = checkresultQualityFlag;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("uploadTime")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@JsonProperty("sourceId")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


}