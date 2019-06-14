package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
* @Description:    初筛信息汇总（阳性胃癌）Vo
* @Author:         lxl
* @CreateDate:     2019/5/30 16:34
* @UpdateRemark:
* @Version: 
*/
@Data
public class ScPositiveSummaryVo extends ReportBaseVo {

    private String sfwj;//是否做胃镜

    private String wjjcjg;//胃镜检查结果

}
