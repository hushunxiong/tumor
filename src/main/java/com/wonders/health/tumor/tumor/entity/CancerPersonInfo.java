/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 初筛对象信息实体
 * @author zhaomeng
 */
public class CancerPersonInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 个人管理编号

	@Length(max=2)
	private String personcardType;	// 证件类型

	@Length(max=50)
	private String personcard;	// 证件号码

	@Length(max=50)
	private String name;	// 姓名

	@Length(max=1)
	private String gender;	// 性别

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birth;	// 出生日期

	@Length(max=50)
	private String telephone;	// 固定电话

	@Length(max=50)
	private String mobile;	// 移动电话

	@Length(max=2)
	private String nation;	// 民族 cdc_dic_personinfo id=00003

	@Length(max=16)
	private String nationOther;	// 其他民族

	@Length(max=2)
	private String marriage;	// 婚姻状况代码cdc_dic_personinfo id=00009

	@Length(max=2)
	private String education;	// 文化程度(学历)代码cdc_dic_personinfo id=00006

	@Length(max=2)
	private String paymentSituation;	// 医疗保险类型 cdc_dic_personinfo id=00241

	@Length(max=2)
	private String profession;	// 职业代码,参照cdc_dic_personinfo,id=00007，pcode is not null 第一级代码

	@Length(max=200)
	private String otherProfession;	// 其他职业

	@Length(max=32)
	private String paddressProvince;	// 户籍地-省（自治区、直辖市） 

	@Length(max=32)
	private String paddressCity;	// 户籍地-市（地区） 

	@Length(max=32)
	private String paddressCounty;	// 户籍地-县（区） 

	@Length(max=32)
	private String paddressTown;	// 户籍地-乡（镇、街道） 

	@Length(max=32)
	private String paddressCommittee;	// 户籍地-居委会、村 

	@Length(max=200)
	private String paddressDetail;	// 户籍地址-详细

	@Length(max=32)
	private String addressProvince;	// 居住地-省（自治区、直辖市） 

	@Length(max=32)
	private String addressCity;	// 居住地-市（地区） 

	@Length(max=32)
	private String addressCounty;	// 居住地-县（区） 

	@Length(max=32)
	private String addressTown;	// 居住地-乡（镇、街道） 

	@Length(max=32)
	private String addressCommittee;	// 居住地-居委会、村 

	@Length(max=200)
	private String addressDetail;	// 居住地址-详细

	@Length(max=32)
	private String regorg;	// 登记医疗机构代码

	@Length(max=32)
	private String regdoc;	// 登记医生代码

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regdate;	// 登记日期

	@Length(max=1)
	private String ischange;	// 更新标志cdc_dic_personinfo id=60013 code=1：是；code=2：否

	@Length(max=12)
	private String patid;

	@Length(max=12)
	private String blh;

	private  String crcCheckId; //大肠癌初筛信息登记id

	private  String lucCheckId; //肺癌初筛信息登记id

	private  String scCheckId;  //胃癌初筛信息登记id

	private  String licCheckId;  //肝癌初筛信息登记id

	private  Integer historyDelflag;//危险度评估-癌症史表 删除标记

	private String csnf;//初筛年份

	private Integer crc;

	private Integer luc;

	private Integer lic;

	private Integer sc;

	private Integer delRecordsFlag;

	private String idNumber;   //大肠癌初筛编号
	private String crcAssessmentResult; //大肠癌危险评估结果
	private String firstFobtResult; //第一次便隐血结果
	private String secondFobtResult; //第二次便隐血结果
	private String fobtResult; //便隐血检查结果
	private String crcResult; //大肠癌判定结果
	private String licAssessmentResult; //肝癌危险评估结果
	private String licAssistResult; //肝癌辅助检查结果
	private String licResult; //肝癌判定结果
	private String scResult; //胃癌判定结果
	private String lucResult; //肺癌判定结果

    private String crcCheckYear;
    private String licCheckYear;
    private String scCheckYear;
    private String lucCheckYear;


    private String isNew;	//检查是否为健康档案及表以外的新增信息


	public CancerPersonInfo() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("personcardType")
	public String getPersoncardType() {
		return personcardType;
	}

	public void setPersoncardType(String personcardType) {
		this.personcardType = personcardType;
	}

	@JsonProperty("personcard")
	public String getPersoncard() {
		return personcard;
	}

	public void setPersoncard(String personcard) {
		this.personcard = personcard;
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("birth")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

	@JsonProperty("nation")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@JsonProperty("nationOther")
	public String getNationOther() {
		return nationOther;
	}

	public void setNationOther(String nationOther) {
		this.nationOther = nationOther;
	}

	@JsonProperty("marriage")
	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	@JsonProperty("education")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@JsonProperty("paymentSituation")
	public String getPaymentSituation() {
		return paymentSituation;
	}

	public void setPaymentSituation(String paymentSituation) {
		this.paymentSituation = paymentSituation;
	}

	@JsonProperty("profession")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@JsonProperty("otherProfession")
	public String getOtherProfession() {
		return otherProfession;
	}

	public void setOtherProfession(String otherProfession) {
		this.otherProfession = otherProfession;
	}

	@JsonProperty("paddressProvince")
	public String getPaddressProvince() {
		return paddressProvince;
	}

	public void setPaddressProvince(String paddressProvince) {
		this.paddressProvince = paddressProvince;
	}

	@JsonProperty("paddressCity")
	public String getPaddressCity() {
		return paddressCity;
	}

	public void setPaddressCity(String paddressCity) {
		this.paddressCity = paddressCity;
	}

	@JsonProperty("paddressCounty")
	public String getPaddressCounty() {
		return paddressCounty;
	}

	public void setPaddressCounty(String paddressCounty) {
		this.paddressCounty = paddressCounty;
	}

	@JsonProperty("paddressTown")
	public String getPaddressTown() {
		return paddressTown;
	}

	public void setPaddressTown(String paddressTown) {
		this.paddressTown = paddressTown;
	}

	@JsonProperty("paddressCommittee")
	public String getPaddressCommittee() {
		return paddressCommittee;
	}

	public void setPaddressCommittee(String paddressCommittee) {
		this.paddressCommittee = paddressCommittee;
	}

	@JsonProperty("paddressDetail")
	public String getPaddressDetail() {
		return paddressDetail;
	}

	public void setPaddressDetail(String paddressDetail) {
		this.paddressDetail = paddressDetail;
	}

	@JsonProperty("addressProvince")
	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	@JsonProperty("addressCity")
	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@JsonProperty("addressCounty")
	public String getAddressCounty() {
		return addressCounty;
	}

	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}

	@JsonProperty("addressTown")
	public String getAddressTown() {
		return addressTown;
	}

	public void setAddressTown(String addressTown) {
		this.addressTown = addressTown;
	}

	@JsonProperty("addressCommittee")
	public String getAddressCommittee() {
		return addressCommittee;
	}

	public void setAddressCommittee(String addressCommittee) {
		this.addressCommittee = addressCommittee;
	}

	@JsonProperty("addressDetail")
	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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

	@JsonProperty("ischange")
	public String getIschange() {
		return ischange;
	}

	public void setIschange(String ischange) {
		this.ischange = ischange;
	}


	public String getCrcCheckId() {
		return crcCheckId;
	}

	public void setCrcCheckId(String crcCheckId) {
		this.crcCheckId = crcCheckId;
	}

	public String getLucCheckId() {
		return lucCheckId;
	}

	public void setLucCheckId(String lucCheckId) {
		this.lucCheckId = lucCheckId;
	}

	public String getScCheckId() {
		return scCheckId;
	}

	public void setScCheckId(String scCheckId) {
		this.scCheckId = scCheckId;
	}

	public String getLicCheckId() {
		return licCheckId;
	}

	public void setLicCheckId(String licCheckId) {
		this.licCheckId = licCheckId;
	}

	public Integer getHistoryDelflag() {
		return historyDelflag;
	}

	public void setHistoryDelflag(Integer historyDelflag) {
		this.historyDelflag = historyDelflag;
	}

	public String getCsnf() {
		return csnf;
	}

	public void setCsnf(String csnf) {
		this.csnf = csnf;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCrcAssessmentResult() {
		return crcAssessmentResult;
	}

	public void setCrcAssessmentResult(String crcAssessmentResult) {
		this.crcAssessmentResult = crcAssessmentResult;
	}

	public String getFirstFobtResult() {
		return firstFobtResult;
	}

	public void setFirstFobtResult(String firstFobtResult) {
		this.firstFobtResult = firstFobtResult;
	}

	public String getSecondFobtResult() {
		return secondFobtResult;
	}

	public void setSecondFobtResult(String secondFobtResult) {
		this.secondFobtResult = secondFobtResult;
	}

	public String getFobtResult() {
		return fobtResult;
	}

	public void setFobtResult(String fobtResult) {
		this.fobtResult = fobtResult;
	}

	public String getCrcResult() {
		return crcResult;
	}

	public void setCrcResult(String crcResult) {
		this.crcResult = crcResult;
	}

	public String getLicAssessmentResult() {
		return licAssessmentResult;
	}

	public void setLicAssessmentResult(String licAssessmentResult) {
		this.licAssessmentResult = licAssessmentResult;
	}

	public String getLicAssistResult() {
		return licAssistResult;
	}

	public void setLicAssistResult(String licAssistResult) {
		this.licAssistResult = licAssistResult;
	}

	public String getLicResult() {
		return licResult;
	}

	public void setLicResult(String licResult) {
		this.licResult = licResult;
	}

	public String getScResult() {
		return scResult;
	}

	public void setScResult(String scResult) {
		this.scResult = scResult;
	}

	public String getLucResult() {
		return lucResult;
	}

	public void setLucResult(String lucResult) {
		this.lucResult = lucResult;
	}

    public Integer getCrc() {
        return crc;
    }

    public void setCrc(Integer crc) {
        this.crc = crc;
    }

    public Integer getLuc() {
        return luc;
    }

    public void setLuc(Integer luc) {
        this.luc = luc;
    }

    public Integer getLic() {
        return lic;
    }

    public void setLic(Integer lic) {
        this.lic = lic;
    }

    public Integer getSc() {
        return sc;
    }

    public void setSc(Integer sc) {
        this.sc = sc;
    }

    public String getCrcCheckYear() {
        return crcCheckYear;
    }

    public void setCrcCheckYear(String crcCheckYear) {
        this.crcCheckYear = crcCheckYear;
    }

    public String getLicCheckYear() {
        return licCheckYear;
    }

    public void setLicCheckYear(String licCheckYear) {
        this.licCheckYear = licCheckYear;
    }

    public String getScCheckYear() {
        return scCheckYear;
    }

    public void setScCheckYear(String scCheckYear) {
        this.scCheckYear = scCheckYear;
    }

    public String getLucCheckYear() {
        return lucCheckYear;
    }

    public void setLucCheckYear(String lucCheckYear) {
        this.lucCheckYear = lucCheckYear;
    }

	public Integer getDelRecordsFlag() {
		return delRecordsFlag;
	}

	public void setDelRecordsFlag(Integer delRecordsFlag) {
		this.delRecordsFlag = delRecordsFlag;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	@JsonProperty("patid")
	public String getPatid() {
		return patid;
	}

	public void setPatid(String patid) {
		this.patid = patid;
	}

	@JsonProperty("blh")
	public String getBlh() {
		return blh;
	}

	public void setBlh(String blh) {
		this.blh = blh;
	}
}