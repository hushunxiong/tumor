/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.lang.String;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 医疗机构表实体
 * @author zhaomeng
 */
public class DicHospitalInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=11)
	@NotNull
	private String hospitalId;	// 医疗机构代码

	@Length(max=64)
	private String hospitalName;	// 医疗机构名称

	@Length(max=2)
	private String hospitalType;	// 原：医院类型 01:综合医院 02：专科医院 03：卫生服务中心;新：医院类型 H1-专科疾病防治院、A1-综合医院、A2-中医医院、A3-中西医结合医院、A5-专科医院、A8-部队医院、B1-社区卫生服务中心

	@Length(max=30)
	private String hospitalLevel;	// 医院等级类型：1-社区卫生服务中心、2-二级、3-三级、9-未评

	@Length(max=32)
	private String addressCounty;	// 地址-县（区）

	@Length(max=4)
	private String sourceId;	

	@Length(max=22)
	private String hospitalId22;	// 医疗机构代码-22位与医疗机构代码一一对应


	public DicHospitalInfo() {
		super();
	}

	@JsonProperty("hospitalId")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@JsonProperty("hospitalName")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@JsonProperty("hospitalType")
	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	@JsonProperty("hospitalLevel")
	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	@JsonProperty("addressCounty")
	public String getAddressCounty() {
		return addressCounty;
	}

	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}

	@JsonProperty("sourceId")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@JsonProperty("hospitalId22")
	public String getHospitalId22() {
		return hospitalId22;
	}

	public void setHospitalId22(String hospitalId22) {
		this.hospitalId22 = hospitalId22;
	}


}