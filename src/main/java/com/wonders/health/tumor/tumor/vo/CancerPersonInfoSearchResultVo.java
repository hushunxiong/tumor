package com.wonders.health.tumor.tumor.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;
/**
*   初筛对象信息结果
* @Author:  liuxiaolei
* @CreateDate:     2019/4/19 12:46
*/
@Data
public class CancerPersonInfoSearchResultVo extends DataGridSearch {
    private String id;         //序号
    private String name;       //姓名
    private String personCard; //身份证号
    //评估人确认已经不需要
    private String idNumber;        //大肠癌初筛编号
    private String crcCheckResult;  //大肠癌判定结果
    private String crcAssessment;   //大肠癌危险度评估结果
    private String firstFobtResult; //第一次便隐血检查结果
    private String secondFobtResult;//第二次便隐血检查结果
    private String fobtResult;      //便隐血检查结果
    private String licCheckResult;  //肝癌判定结果
    private String licAssessment;   //肝癌危险度评估结果
    private String licAssistResult; //肝癌初筛辅助检查结果
    private String scCheckResult;   //胃癌初筛判定结果
    private String lucCheckResult;  //肺癌初筛判定结果


}
