/**
 * 
 */
package com.wonders.health.tumor.tumor.vo;


import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.utils.DateUtils;

/**
 * 初筛对象信息搜索
 * @author zhaomeng
 */
public class CancerPersonInfoSearchVo extends DataGridSearch {

    private String keyword; //可按照姓名或者身份证号模糊查询
    private String id; //ID编号
    private String csnf;  //初筛年份
    private String regarea; //登记机构所属区
    private String regorg; //登记机构
    private String beginDateFrom; //初筛登记日期
    private String endDateFrom; //初筛登记日期
    private String status; //初筛状态
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
    private String name;       //姓名
    private String personCard; //身份证号
    private String idNumber;   //大肠癌初筛编号
    private String gender;     //性别

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonCard() {
        return personCard;
    }

    public void setPersonCard(String personCard) {
        this.personCard = personCard;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCsnf() {
        return csnf;
    }

    public void setCsnf(String csnf) {
        this.csnf = csnf;
    }

    public String getRegarea() {
        return regarea;
    }

    public void setRegarea(String regarea) {
        this.regarea = regarea;
    }

    public String getRegorg() {
        return regorg;
    }

    public void setRegorg(String regorg) {
        this.regorg = regorg;
    }

    public String getBeginDateFrom() {
        return beginDateFrom;
    }

    public void setBeginDateFrom(String beginDateFrom) {
        this.beginDateFrom = beginDateFrom;
    }

    public String getEndDateFrom() {
        return endDateFrom;
    }

    public void setEndDateFrom(String endDateFrom) {
        this.endDateFrom = endDateFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}