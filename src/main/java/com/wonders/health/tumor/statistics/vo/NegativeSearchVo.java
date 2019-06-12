package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

import java.util.Date;

/**
* @Description:    统计报表的查询条件
* @Author:         lxl
* @CreateDate:     2019/5/30 16:31
* @UpdateRemark:
* @Version: 
*/
@Data
public class NegativeSearchVo extends SummarySearchVo {
    /**
     * 大肠癌阴性
     */
    private Boolean crc;

    /**
     * 肝癌阴性
     */
    private Boolean lic;
    /**
     * 肺癌阴性
     */
    private Boolean luc;
    /**
     * 胃癌阴性
     */
    private Boolean sc;

    /**
     * 复选框选中几个
     */
    private Integer count;


}
