package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
* @Description:    诊断信息收集Vo
* @Author:         lxl
* @CreateDate:     2019/5/30 17:20
* @UpdateRemark:
* @Version:
*/
@Data
public class InformationCollectionVo {
    private String xm; //姓名

    private String personcardType;//证件类型

    private String personcard;//证件号码

    private String regorg; //筛查登记机构

    private String jcxm;  //检查项目

    private String bbbw;  //病变部位

    private String zldx;  //肿瘤大小（最大直径）

    private String sfhj;  //是否活检

    private String hjjg;  //活检结果

    private String sfbl;  //是否病理

    private String bllx;  //病理类型

    private String TNMfq; //TNM分期

    private String sfrq;  //随访日期

    private String  ZHONGLIUTNM_T;

    private String ZHONGLIUTNM_N;

    private String ZHONGLIUTNM_M;

    private String shifouweijing; //是否胃镜,为1是，是则在页面检查项处显示“胃镜”

    private String changjing ;    // 是否肠镜,为1是，是则在页面检查项处显示“肠镜”

    private String shifouLDCT;    // 是否ct,为1是，是则在页面检查项处显示“ct”


}
