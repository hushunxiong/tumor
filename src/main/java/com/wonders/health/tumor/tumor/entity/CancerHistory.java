/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 历史实体
 * @author xuguobing
 */
public class CancerHistory extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 癌症史id

	@Length(max=32)
	@NotNull
	private String manageid;	// 个人管理编号

	@NotNull
	private Integer checkYear;	// 初筛年度

	@Length(max=12)
	private String icd10;	// 癌症种类ICD10编码

	@Length(max=200)
	private String cancerName;	// 癌症名称

	
	private Integer age;	// 年龄

	@Length(max=32)
	private String hospitalCode;	// 诊断医疗机构代码

	@Length(max=100)
	private String hospitalName;	// 诊断医疗机构名称

	@Length(max=1)
	private String ischange;	// 更新标志 cdc_dic_personinfo id=60013 code=1：是；code=2：否


	public CancerHistory() {
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

	@JsonProperty("checkYear")
	public Integer getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(Integer checkYear) {
		this.checkYear = checkYear;
	}

	@JsonProperty("icd10")
	public String getIcd10() {
		return icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	@JsonProperty("cancerName")
	public String getCancerName() {
		return cancerName;
	}

	public void setCancerName(String cancerName) {
		this.cancerName = cancerName;
	}

	@JsonProperty("age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonProperty("hospitalCode")
	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	@JsonProperty("hospitalName")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@JsonProperty("ischange")
	public String getIschange() {
		return ischange;
	}

	public void setIschange(String ischange) {
		this.ischange = ischange;
	}


}