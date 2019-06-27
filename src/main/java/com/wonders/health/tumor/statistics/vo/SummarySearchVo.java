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
public class SummarySearchVo extends DataGridSearch {

    /**
     *  登记机构所属区
     */
    private String regarea;

    /**
     * 筛查登记机构
     */
    private String regorg;

    /**
     *日期范围（起始）
     */
    private Date csrqStart;

    /**
     * 日期范围（结束）
     */
    private Date csrqEnd;
}
