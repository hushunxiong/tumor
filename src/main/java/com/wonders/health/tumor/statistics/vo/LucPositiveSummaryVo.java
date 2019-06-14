package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
* @Description:    初筛信息汇总（阳性肺癌）Vo
* @Author:         lxl
* @CreateDate:     2019/5/30 16:34
* @UpdateRemark:
* @Version: 
*/
@Data
public class LucPositiveSummaryVo extends ReportBaseVo {

    private String shifouLDCT;//是否做CT

    private String LDCTJieguo;//CT检查结果



}
