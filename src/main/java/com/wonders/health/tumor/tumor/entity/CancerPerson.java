package com.wonders.health.tumor.tumor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wonders.health.tumor.common.model.BaseEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 初筛对象信息实体
 * @Author 孟良海
 */

public class CancerPerson extends BaseEntity {
    @NotNull
    @Length(max=32)
    private String id;

    @Length(max=2)
    private String personcardType;

    @Length(max=50)
    private String personcard;

    @Length(max=50)
    private String name;

    @Length(max=2)
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @Length(max=50)
    private String telephone;

    @Length(max=50)
    private String mobile;

    @Length(max=2)
    private String nation;

    @Length(max=16)
    private String nationOther;

    @Length(max=2)
    private String marriage;

    @Length(max=2)
    private String education;

    @Length(max=2)
    private String paymentSituation;

    @Length(max=2)
    private String profession;

    @Length(max=200)
    private String otherProfession;

    @Length(max=32)
    private String paddressProvince;

    @Length(max=32)
    private String paddressCity;

    @Length(max=32)
    private String paddressCounty;

    @Length(max=32)
    private String paddressTown;

    @Length(max=32)
    private String paddressCommittee;

    @Length(max=200)
    private String paddressDetail;

    @Length(max=32)
    private String addressProvince;

    @Length(max=32)
    private String addressCity;

    @Length(max=32)
    private String addressCounty;

    @Length(max=32)
    private String addressTown;

    @Length(max=32)
    private String addressCommittee;

    @Length(max=200)
    private String addressDetail;

    @Length(max=32)
    private String regorg;

    @Length(max=32)
    private String regdoc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regdate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersoncardType() {
        return personcardType;
    }

    public void setPersoncardType(String personcardType) {
        this.personcardType = personcardType;
    }

    public String getPersoncard() {
        return personcard;
    }

    public void setPersoncard(String personcard) {
        this.personcard = personcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationOther() {
        return nationOther;
    }

    public void setNationOther(String nationOther) {
        this.nationOther = nationOther;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPaymentSituation() {
        return paymentSituation;
    }

    public void setPaymentSituation(String paymentSituation) {
        this.paymentSituation = paymentSituation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOtherProfession() {
        return otherProfession;
    }

    public void setOtherProfession(String otherProfession) {
        this.otherProfession = otherProfession;
    }

    public String getPaddressProvince() {
        return paddressProvince;
    }

    public void setPaddressProvince(String paddressProvince) {
        this.paddressProvince = paddressProvince;
    }

    public String getPaddressCity() {
        return paddressCity;
    }

    public void setPaddressCity(String paddressCity) {
        this.paddressCity = paddressCity;
    }

    public String getPaddressCounty() {
        return paddressCounty;
    }

    public void setPaddressCounty(String paddressCounty) {
        this.paddressCounty = paddressCounty;
    }

    public String getPaddressTown() {
        return paddressTown;
    }

    public void setPaddressTown(String paddressTown) {
        this.paddressTown = paddressTown;
    }

    public String getPaddressCommittee() {
        return paddressCommittee;
    }

    public void setPaddressCommittee(String paddressCommittee) {
        this.paddressCommittee = paddressCommittee;
    }

    public String getPaddressDetail() {
        return paddressDetail;
    }

    public void setPaddressDetail(String paddressDetail) {
        this.paddressDetail = paddressDetail;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressCommittee() {
        return addressCommittee;
    }

    public void setAddressCommittee(String addressCommittee) {
        this.addressCommittee = addressCommittee;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getRegorg() {
        return regorg;
    }

    public void setRegorg(String regorg) {
        this.regorg = regorg;
    }

    public String getRegdoc() {
        return regdoc;
    }

    public void setRegdoc(String regdoc) {
        this.regdoc = regdoc;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
