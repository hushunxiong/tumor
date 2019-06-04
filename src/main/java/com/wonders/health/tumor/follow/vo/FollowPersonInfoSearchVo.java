package com.wonders.health.tumor.follow.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;

/**
 * 随访一览搜索
 * @author sunyang
 */
public class FollowPersonInfoSearchVo extends DataGridSearch {
    private String keyword; //可按照姓名或者身份证号模糊查询
    private String csnf;  //初筛年份
    private String regarea; //登记机构所属区
    private String regorg; //登记机构
    private String beginDate; //初筛登记日期开始
    private String endDate; //初筛登记日期结束

    private String crcResult;//大肠癌判定结果
    private String licResult;//肝癌判定结果
    private String scResult; //胃癌判定结果
    private String lucResult; //肺癌判定结果

    private String crcStatus;//大肠癌随访状态
    private String licStatus;//肝癌随访状态
    private String scStatus;//胃癌随访状态
    private String lucStatus;//肺癌随访状态
    private String crcFlag;
    private String lucFlag;
    private String licFlag;
    private String scFlag;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCrcResult() {
        return crcResult;
    }

    public void setCrcResult(String crcResult) {
        this.crcResult = crcResult;
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

    public String getCrcStatus() {
        return crcStatus;
    }

    public void setCrcStatus(String crcStatus) {
        this.crcStatus = crcStatus;
    }

    public String getLicStatus() {
        return licStatus;
    }

    public void setLicStatus(String licStatus) {
        this.licStatus = licStatus;
    }

    public String getScStatus() {
        return scStatus;
    }

    public void setScStatus(String scStatus) {
        this.scStatus = scStatus;
    }

    public String getLucStatus() {
        return lucStatus;
    }

    public void setLucStatus(String lucStatus) {
        this.lucStatus = lucStatus;
    }

    public String getCrcFlag() {
        return crcFlag;
    }

    public void setCrcFlag(String crcFlag) {
        this.crcFlag = crcFlag;
    }

    public String getLucFlag() {
        return lucFlag;
    }

    public void setLucFlag(String lucFlag) {
        this.lucFlag = lucFlag;
    }

    public String getLicFlag() {
        return licFlag;
    }

    public void setLicFlag(String licFlag) {
        this.licFlag = licFlag;
    }

    public String getScFlag() {
        return scFlag;
    }

    public void setScFlag(String scFlag) {
        this.scFlag = scFlag;
    }
}
