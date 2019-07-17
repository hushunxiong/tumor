package com.wonders.health.tumor.tumor.vo;

import com.wonders.health.tumor.tumor.entity.*;
import lombok.Data;

import java.util.List;

/**
 *综合vo
 *
 **/
@Data
public class ScreeningVo {

    private static final long serialVersionUID = 1L;

    private String checkYear;             //初筛年度

    private String idNumber;             //初筛ID

    private CancerPersonInfo personInfo;  //初筛对象信息

    private CrcRegcase crcRegcase;     //大肠癌初筛信息登记表

    private LicRegcase licRegcase;     //肝癌初筛信息登记表

    private ScRegcase scRegcase;       //胃癌初筛信息登记表

    private LucRegcase lucRegcase;     //肺癌初筛信息登记表

    private CrcRiskAssessment crcRisk; //大肠癌危险度评估表

    private LicRiskAssessment licRisk; //肝癌危险度评估表

    private ScRiskAssessment scRisk;   //胃癌危险度评估表

    private LucRiskAssessment lucRisk; //肺癌危险度评估表

    private CrcFobt crcFobt;           //大肠癌便隐血检查

    private LicAssistCheck licCheck;   //肝癌辅助检查表

    //癌症史表
    private List<CancerHistory> historyList;

    //一级亲癌症史表
    private List<FamilyCancerHistory> familyCancerHistoryList;

    //一级亲癌症史表（徐汇）
    private List<LucFamilyCancerHistoryXH> lucFamilyCancerHistoryXHList;



}
