/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;

import com.wonders.health.auth.client.vo.User;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 肝癌辅助检查表实体
 * @author menglianghai
 */
public class LicAssistCheck extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String licCheckId;	// 肝癌初筛信息登记id

	
	private Integer gtp;	// GTP检查结果

	@Length(max=32)
	private String hbeg;	// HBeg检查结果

	
	private Integer afp;	// AFP检查结果

	@Length(max=1)
	private String bus;	// B超检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

	@Length(max=1)
	private String licAssistResult;	// 肝癌辅助检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

	@Length(max=32)
	@NotNull
	private String licAssistDoc;	// 检查医生代码

	@Length(max=64)
	@NotNull
	private String licAssistDocName;	// 检查医生姓名

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date licAssistDate;	// 检查日期

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间


	public LicAssistCheck() {
		super();
	}
	public LicAssistCheck(User user) {
		super();
		this.licAssistDate=new Date();
		this.licAssistDoc=user.getId();
		this.licAssistDocName=user.getName();
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

	@JsonProperty("gtp")
	public Integer getGtp() {
		return gtp;
	}

	public void setGtp(Integer gtp) {
		this.gtp = gtp;
	}

	@JsonProperty("hbeg")
	public String getHbeg() {
		return hbeg;
	}

	public void setHbeg(String hbeg) {
		this.hbeg = hbeg;
	}

	@JsonProperty("afp")
	public Integer getAfp() {
		return afp;
	}

	public void setAfp(Integer afp) {
		this.afp = afp;
	}

	@JsonProperty("bus")
	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	@JsonProperty("licAssistResult")
	public String getLicAssistResult() {
		return licAssistResult;
	}

	public void setLicAssistResult(String licAssistResult) {
		this.licAssistResult = licAssistResult;
	}

	@JsonProperty("licAssistDoc")
	public String getLicAssistDoc() {
		return licAssistDoc;
	}

	public void setLicAssistDoc(String licAssistDoc) {
		this.licAssistDoc = licAssistDoc;
	}

	@JsonProperty("licAssistDocName")
	public String getLicAssistDocName() {
		return licAssistDocName;
	}

	public void setLicAssistDocName(String licAssistDocName) {
		this.licAssistDocName = licAssistDocName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("licAssistDate")
	public Date getLicAssistDate() {
		return licAssistDate;
	}

	public void setLicAssistDate(Date licAssistDate) {
		this.licAssistDate = licAssistDate;
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