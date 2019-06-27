package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
* @Description:    初筛信息汇总（阳性大肠癌）Vo
* @Author:         lxl
* @CreateDate:     2019/5/30 16:34
* @UpdateRemark:
* @Version: 
*/
@Data
public class CrcPositiveSummaryVo extends ReportBaseVo {

    private String fobtResult; //便隐血检查结果

    private String changjing; //是否做肠镜




}
