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
 * 大肠癌便隐血检查表实体
 * @author menglianghai
 */
public class CrcFobt extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	@NotNull
	private String crcCheckId;	// 大肠癌初筛信息登记id

	private Date cbqReturnDate1;	//第一次采便器交回日期

	private Date cbqReturnDate2;	//第二次采便器交回日期

	@Length(max=1)
	private String firstFobtResult;	// 第一次便隐血检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

	@Length(max=1)
	private String secondFobtResult;	// 第二次便隐血检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date firstFobtDate;	// 第一次便隐血检查时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date secondFobtDate;	// 第一次便隐血检查时间

	@Length(max=1)
	private String fobtResult;	// 便隐血检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

	@Length(max=32)
	@NotNull
	private String fobtDoc;	// 检查医生代码

	@Length(max=64)
	@NotNull
	private String fobtDocName;	// 检查医生姓名

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间

	@Length(max=1)
	private String ischange;	// 更新标志 cdc_dic_personinfo id=60013 code=1：是；code=2：否


	public CrcFobt() {
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

	public Date getCbqReturnDate1() {
		return cbqReturnDate1;
	}

	public void setCbqReturnDate1(Date cbqReturnDate1) {
		this.cbqReturnDate1 = cbqReturnDate1;
	}

	public Date getCbqReturnDate2() {
		return cbqReturnDate2;
	}

	public void setCbqReturnDate2(Date cbqReturnDate2) {
		this.cbqReturnDate2 = cbqReturnDate2;
	}

	@JsonProperty("firstFobtResult")
	public String getFirstFobtResult() {
		return firstFobtResult;
	}

	public void setFirstFobtResult(String firstFobtResult) {
		this.firstFobtResult = firstFobtResult;
	}

	@JsonProperty("secondFobtResult")
	public String getSecondFobtResult() {
		return secondFobtResult;
	}

	public void setSecondFobtResult(String secondFobtResult) {
		this.secondFobtResult = secondFobtResult;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("firstFobtDate")
	public Date getFirstFobtDate() {
		return firstFobtDate;
	}

	public void setFirstFobtDate(Date firstFobtDate) {
		this.firstFobtDate = firstFobtDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("secondFobtDate")
	public Date getSecondFobtDate() {
		return secondFobtDate;
	}

	public void setSecondFobtDate(Date secondFobtDate) {
		this.secondFobtDate = secondFobtDate;
	}

	@JsonProperty("fobtResult")
	public String getFobtResult() {
		return fobtResult;
	}

	public void setFobtResult(String fobtResult) {
		this.fobtResult = fobtResult;
	}

	@JsonProperty("fobtDoc")
	public String getFobtDoc() {
		return fobtDoc;
	}

	public void setFobtDoc(String fobtDoc) {
		this.fobtDoc = fobtDoc;
	}

	@JsonProperty("fobtDocName")
	public String getFobtDocName() {
		return fobtDocName;
	}

	public void setFobtDocName(String fobtDocName) {
		this.fobtDocName = fobtDocName;
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