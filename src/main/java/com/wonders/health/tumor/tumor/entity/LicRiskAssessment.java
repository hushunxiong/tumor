/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.wonders.health.auth.client.vo.User;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 肝癌危险度评估表实体
 * @author menglianghai
 */
public class LicRiskAssessment extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String licCheckId;	// 肝癌初筛信息登记id

	@Length(max=1)
	private String yigan;	// 有无乙型肝炎 cdc_dic_personinfo id=60007code=1：有；code=2：无

	@Length(max=1)
	private String binggan;	// 有无丙型肝炎 cdc_dic_personinfo id=60007code=1：有；code=2：无

	@Length(max=1)
	private String ganyinghua;	// 有无肝硬化 cdc_dic_personinfo id=60007code=1：有；code=2：无

	@Length(max=1)
	private String aizhengshi;	// 有无癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在癌症史表中有对应记录)

	@Length(max=1)
	private String qinshuAizhengshi;	// 一级亲属癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在亲属癌症史中有对应记录)

	@Length(max=1)
	@NotNull
	private String assessmentResult;	// 危险度评估结果 cdc_dic_personinfo id=60001code=1：阴性；code=2：阳性

	@Length(max=32)
	@NotNull
	private String assessmentDoc;	// 评估医生代码

	@Length(max=64)
	@NotNull
	private String assessmentDocName;	// 评估医生签名

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date assessmentDate;	// 评估日期

	@Length(max=1)
	@NotNull
	private String riskQualityFlag;	// 危险度评估质控标志 cdc_dic_personinfo id=60005code=1：未参与；code=2：已参与

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间


	public LicRiskAssessment() {
		super();
	}
	public LicRiskAssessment(User user) {
		super();
		this.assessmentDocName=user.getName();
		this.assessmentDoc=user.getId();
		this.assessmentDate=new Date();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("licCheckId")
	public String getLicCheckId() {
		return licCheckId;
	}

	public void setLicCheckId(String licCheckId) {
		this.licCheckId = licCheckId;
	}

	@JsonProperty("yigan")
	public String getYigan() {
		return yigan;
	}

	public void setYigan(String yigan) {
		this.yigan = yigan;
	}

	@JsonProperty("binggan")
	public String getBinggan() {
		return binggan;
	}

	public void setBinggan(String binggan) {
		this.binggan = binggan;
	}

	@JsonProperty("ganyinghua")
	public String getGanyinghua() {
		return ganyinghua;
	}

	public void setGanyinghua(String ganyinghua) {
		this.ganyinghua = ganyinghua;
	}

	@JsonProperty("aizhengshi")
	public String getAizhengshi() {
		return aizhengshi;
	}

	public void setAizhengshi(String aizhengshi) {
		this.aizhengshi = aizhengshi;
	}

	@JsonProperty("qinshuAizhengshi")
	public String getQinshuAizhengshi() {
		return qinshuAizhengshi;
	}

	public void setQinshuAizhengshi(String qinshuAizhengshi) {
		this.qinshuAizhengshi = qinshuAizhengshi;
	}

	@JsonProperty("assessmentResult")
	public String getAssessmentResult() {
		return assessmentResult;
	}

	public void setAssessmentResult(String assessmentResult) {
		this.assessmentResult = assessmentResult;
	}

	@JsonProperty("assessmentDoc")
	public String getAssessmentDoc() {
		return assessmentDoc;
	}

	public void setAssessmentDoc(String assessmentDoc) {
		this.assessmentDoc = assessmentDoc;
	}

	@JsonProperty("assessmentDocName")
	public String getAssessmentDocName() {
		return assessmentDocName;
	}

	public void setAssessmentDocName(String assessmentDocName) {
		this.assessmentDocName = assessmentDocName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("assessmentDate")
	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	@JsonProperty("riskQualityFlag")
	public String getRiskQualityFlag() {
		return riskQualityFlag;
	}

	public void setRiskQualityFlag(String riskQualityFlag) {
		this.riskQualityFlag = riskQualityFlag;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("uploadTime")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}


}