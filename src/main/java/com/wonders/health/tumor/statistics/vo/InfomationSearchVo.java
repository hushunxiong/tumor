package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
* @Description:    诊断信息收集表vo
* @Author:         lxl
* @CreateDate:     2019/6/6 16:06
* @UpdateRemark:
* @Version:
*/
@Data
public class InfomationSearchVo extends  SummarySearchVo{
    //yml中配置的相应癌症
    private Integer crcFlag;//大肠癌
    private Integer lucFlag;//肺癌
    private Integer licFlag;//肝癌
    private Integer scFlag;//胃癌
}
