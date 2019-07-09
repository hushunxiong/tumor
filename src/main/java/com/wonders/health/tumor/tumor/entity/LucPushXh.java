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
 * 徐汇肺癌高危大数据推送表实体
 * @author sunyang
 */
public class LucPushXh extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	@NotNull
	private String pushid;	// 推送编号

	@NotNull
	private Date pushtime;	// 推送时间

	@Length(max=32)
	private String personcard;	// 身份证

	private Date birth;  //生日

	@Length(max=50)
	private String name;	// 姓名

	@Length(max=50)
	private String gender;	// 性别 字典表 id=60023

	@Length(max=50)
	private String telephone;	// 固定电话

	@Length(max=50)
	private String mobile;	// 移动电话

	@Length(max=32)
	private String signorg;	// 档案管理机构代码

	@Length(max=32)
	private String manageorg;	// 数据来源机构代码

	@Length(max=32)
	private String hospitalid;	// 社区卫生服务中心代码

	@Length(max=32)
	private String province;	// 居住地-省（自治区、直辖市）

	@Length(max=32)
	private String city;	// 居住地-市（地区）

	@Length(max=32)
	private String county;	// 居住地-县（区）

	@Length(max=32)
	private String town;	// 居住地-乡（镇、街道）

	@Length(max=32)
	private String committee;	// 居住地-居委会（村）

	@Length(max=200)
	private String detail;	// 居住地址-详细

	@Length(max=2)
	private String signstatus;	// 签约状态字典表 id=60055

	@Length(max=2)
	private String signtype;	// 签约类型字典表 id=60056

	@Length(max=2)
	@NotNull
	private String verifystatus;	// 核实状态字典表 id=60057

	@Length(max=32)
	@NotNull
	private String origin;	// 来源数据字典字典表=60058

	@Length(max=4)
	private String sourceId;	// 来源代码

	private String riskAccount;  //危险因素科目

	private String addressDetail; //居住详细地址

	public LucPushXh() {
		super();
	}

	@JsonProperty("pushid")
	public String getPushid() {
		return pushid;
	}

	public void setPushid(String pushid) {
		this.pushid = pushid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("pushtime")
	public Date getPushtime() {
		return pushtime;
	}

	public void setPushtime(Date pushtime) {
		this.pushtime = pushtime;
	}

	@JsonProperty("personcard")
	public String getPersoncard() {
		return personcard;
	}

	public void setPersoncard(String personcard) {
		this.personcard = personcard;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("birth")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("telephone")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@JsonProperty("mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@JsonProperty("signorg")
	public String getSignorg() {
		return signorg;
	}

	public void setSignorg(String signorg) {
		this.signorg = signorg;
	}

	@JsonProperty("manageorg")
	public String getManageorg() {
		return manageorg;
	}

	public void setManageorg(String manageorg) {
		this.manageorg = manageorg;
	}

	@JsonProperty("hospitalid")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}

	@JsonProperty("province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("county")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@JsonProperty("town")
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@JsonProperty("committee")
	public String getCommittee() {
		return committee;
	}

	public void setCommittee(String committee) {
		this.committee = committee;
	}

	@JsonProperty("detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@JsonProperty("signstatus")
	public String getSignstatus() {
		return signstatus;
	}

	public void setSignstatus(String signstatus) {
		this.signstatus = signstatus;
	}

	@JsonProperty("signtype")
	public String getSigntype() {
		return signtype;
	}

	public void setSigntype(String signtype) {
		this.signtype = signtype;
	}

	@JsonProperty("verifystatus")
	public String getVerifystatus() {
		return verifystatus;
	}

	public void setVerifystatus(String verifystatus) {
		this.verifystatus = verifystatus;
	}

	@JsonProperty("origin")
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@JsonProperty("sourceId")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getRiskAccount() {
		return riskAccount;
	}

	public void setRiskAccount(String riskAccount) {
		this.riskAccount = riskAccount;
	}

}