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
 * 大肠癌危险度评估实体
 * @author menglianghai
 */
public class CrcRiskAssessment extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String crcCheckId;	// 大肠癌初筛信息登记id

	@Length(max=1)
	private String manxingfuxie;	// 慢性腹泻史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

	@Length(max=1)
	private String manxingbianmi;	// 慢性便秘史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

	@Length(max=1)
	private String nianyexuebian;	// 粘液和或血便史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

	@Length(max=1)
	private String lanweiyan;	// 慢性阑尾炎或阑尾切除史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

	@Length(max=1)
	private String dannangyan;	// 有无慢性胆囊炎或胆囊切除史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

	@Length(max=1)
	private String jingshenchuangshang;	// 有无精神创伤 cdc_dic_personinfo id=60007 code=1：有；code=2：无

	@Length(max=1)
	private String aizhengshi;	// 有无癌症史 cdc_dic_personinfo id=60007 code=1：有；code=2：无 (若为1时,则在癌症史表中有对应记录)

	@Length(max=1)
	private String qinshuAizhengshi;	// 一级亲属癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在亲属癌症史中有对应记录)

	@Length(max=1)
	private String changxirou;	// 有无肠息肉史 cdc_dic_personinfo id=60007code=1：有；code=2：无

	@Length(max=1)
	private String xiyan;	// 是否吸烟 cdc_dic_personinfo id=60008code=1：不吸；code=2：过去吸，现在不吸；code=3：现在吸

	@Length(max=1)
	private String xuexichong;	// 有无血吸虫病史 cdc_dic_personinfo id=60007code=1：有；code=2：无

	
	private Integer ganrannian;	// 血吸虫病感染年份

	@Length(max=1)
	@NotNull
	private String assessmentResult;	// 危险度评估结果 cdc_dic_personinfo id=60001code=1：阴性；code=2：阳性

	@Length(max=32)
	@NotNull
	private String assessmentDoc;	// 评估医生代码

	@Length(max=64)
	@NotNull
	private String assessmentDocName;	// 评估医生姓名

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date assessmentDate;	// 评估日期

	@Length(max=1)
	@NotNull
	private String riskQualityFlag;	// 危险度评估质控标志 cdc_dic_personinfo id=60005code=1：未参与；code=2：已参与

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间

	@Length(max=1)
	private String ischange;	// 更新标志 cdc_dic_personinfo id=60013code=1：是；code=2：否


	public CrcRiskAssessment() {
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

	@JsonProperty("manxingfuxie")
	public String getManxingfuxie() {
		return manxingfuxie;
	}

	public void setManxingfuxie(String manxingfuxie) {
		this.manxingfuxie = manxingfuxie;
	}

	@JsonProperty("manxingbianmi")
	public String getManxingbianmi() {
		return manxingbianmi;
	}

	public void setManxingbianmi(String manxingbianmi) {
		this.manxingbianmi = manxingbianmi;
	}

	@JsonProperty("nianyexuebian")
	public String getNianyexuebian() {
		return nianyexuebian;
	}

	public void setNianyexuebian(String nianyexuebian) {
		this.nianyexuebian = nianyexuebian;
	}

	@JsonProperty("lanweiyan")
	public String getLanweiyan() {
		return lanweiyan;
	}

	public void setLanweiyan(String lanweiyan) {
		this.lanweiyan = lanweiyan;
	}

	@JsonProperty("dannangyan")
	public String getDannangyan() {
		return dannangyan;
	}

	public void setDannangyan(String dannangyan) {
		this.dannangyan = dannangyan;
	}

	@JsonProperty("jingshenchuangshang")
	public String getJingshenchuangshang() {
		return jingshenchuangshang;
	}

	public void setJingshenchuangshang(String jingshenchuangshang) {
		this.jingshenchuangshang = jingshenchuangshang;
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

	@JsonProperty("changxirou")
	public String getChangxirou() {
		return changxirou;
	}

	public void setChangxirou(String changxirou) {
		this.changxirou = changxirou;
	}

	@JsonProperty("xiyan")
	public String getXiyan() {
		return xiyan;
	}

	public void setXiyan(String xiyan) {
		this.xiyan = xiyan;
	}

	@JsonProperty("xuexichong")
	public String getXuexichong() {
		return xuexichong;
	}

	public void setXuexichong(String xuexichong) {
		this.xuexichong = xuexichong;
	}

	@JsonProperty("ganrannian")
	public Integer getGanrannian() {
		return ganrannian;
	}

	public void setGanrannian(Integer ganrannian) {
		this.ganrannian = ganrannian;
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

	@JsonProperty("ischange")
	public String getIschange() {
		return ischange;
	}

	public void setIschange(String ischange) {
		this.ischange = ischange;
	}


}