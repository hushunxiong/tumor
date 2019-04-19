package com.wonders.health.tumor.tumor.vo;

import com.wonders.health.tumor.tumor.entity.*;

import java.util.List;

public class ScreeningVo {

    private static final long serialVersionUID = 1L;

    CancerPersonInfo personInfo;  //初筛对象信息

    CrcRegcase crcRegcase;     //大肠癌初筛信息登记表

    LicRegcase licRegcase;     //肝癌初筛信息登记表

    ScRegcase scRegcase;       //胃癌初筛信息登记表

    LucRegcase lucRegcase;     //肺癌初筛信息登记表

    CrcRiskAssessment crcRisk; //大肠癌危险度评估表

    LicRiskAssessment licRisk; //肝癌危险度评估表

    ScRiskAssessment scRisk;   //胃癌危险度评估表

    LucRiskAssessment lucRisk; //肺癌危险度评估表

    CrcFobt crcFobt;           //大肠癌便隐血检查

    LicAssistCheck licCheck;   //肝癌辅助检查表

    //癌症史表
    private List<CancerHistory> historyList;

    //一级亲癌症史表
    private List<FamilyCancerHistory> familyCancerHistoryList;

    //一级亲癌症史表（徐汇）
    private List<LucFamilyCancerHistoryXH> lucFamilyCancerHistoryXHList;

    public CancerPersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(CancerPersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public CrcRegcase getCrcRegcase() {
        return crcRegcase;
    }

    public void setCrcRegcase(CrcRegcase crcRegcase) {
        this.crcRegcase = crcRegcase;
    }

    public LicRegcase getLicRegcase() {
        return licRegcase;
    }

    public void setLicRegcase(LicRegcase licRegcase) {
        this.licRegcase = licRegcase;
    }

    public ScRegcase getScRegcase() {
        return scRegcase;
    }

    public void setScRegcase(ScRegcase scRegcase) {
        this.scRegcase = scRegcase;
    }

    public LucRegcase getLucRegcase() {
        return lucRegcase;
    }

    public void setLucRegcase(LucRegcase lucRegcase) {
        this.lucRegcase = lucRegcase;
    }

    public CrcRiskAssessment getCrcRisk() {
        return crcRisk;
    }

    public void setCrcRisk(CrcRiskAssessment crcRisk) {
        this.crcRisk = crcRisk;
    }

    public LicRiskAssessment getLicRisk() {
        return licRisk;
    }

    public void setLicRisk(LicRiskAssessment licRisk) {
        this.licRisk = licRisk;
    }

    public ScRiskAssessment getScRisk() {
        return scRisk;
    }

    public void setScRisk(ScRiskAssessment scRisk) {
        this.scRisk = scRisk;
    }

    public LucRiskAssessment getLucRisk() {
        return lucRisk;
    }

    public void setLucRisk(LucRiskAssessment lucRisk) {
        this.lucRisk = lucRisk;
    }

    public CrcFobt getCrcFobt() {
        return crcFobt;
    }

    public void setCrcFobt(CrcFobt crcFobt) {
        this.crcFobt = crcFobt;
    }

    public LicAssistCheck getLicCheck() {
        return licCheck;
    }

    public void setLicCheck(LicAssistCheck licCheck) {
        this.licCheck = licCheck;
    }

    public List<CancerHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<CancerHistory> historyList) {
        this.historyList = historyList;
    }

    public List<FamilyCancerHistory> getFamilyCancerHistoryList() {
        return familyCancerHistoryList;
    }

    public void setFamilyCancerHistoryList(List<FamilyCancerHistory> familyCancerHistoryList) {
        this.familyCancerHistoryList = familyCancerHistoryList;
    }

    public List<LucFamilyCancerHistoryXH> getLucFamilyCancerHistoryXHList() {
        return lucFamilyCancerHistoryXHList;
    }

    public void setLucFamilyCancerHistoryXHList(List<LucFamilyCancerHistoryXH> lucFamilyCancerHistoryXHList) {
        this.lucFamilyCancerHistoryXHList = lucFamilyCancerHistoryXHList;
    }
}