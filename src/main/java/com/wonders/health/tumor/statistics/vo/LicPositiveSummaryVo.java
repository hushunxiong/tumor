package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
* @Description:    初筛信息汇总（阳性肝癌）Vo
* @Author:         lxl
* @CreateDate:     2019/5/30 16:34
* @UpdateRemark:
* @Version: 
*/
@Data
public class LicPositiveSummaryVo extends ReportBaseVo {

    private String GTP;//GTP检查结果

    private String Hbeg;//HBsAg检查结果

    private String AFP;//AFP检查结果

    private String Bus;//肝脏B超检查结果




}
