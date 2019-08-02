package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

import java.util.Date;

/**
* @Description:    社区卫生服务中心肿瘤早发现进度表(社区登录的场合)
* @Author:         hushunxiong
* @CreateDate:     2019/7/19 18:17
* @UpdateRemark:
* @Version: 
*/
@Data
public class SeScheduleSearchVo extends DataGridSearch {

    /**
     *  筛查登记机构(名称)
     */
    private String regorg;

    /**
     *  筛查登记机构（代号）
     */
    private String regorgCode;

    /**
     * 初筛年份
     */
    private String year;

    /**
     *日期范围（起始）
     */
    private Date csrqStart;

    /**
     * 日期范围（结束）
     */
    private Date csrqEnd;

    /**
     *癌症种类
     * 1 为 crc（大肠癌）2 为 luc（肺癌）3 为 sc（胃癌）4 为 lic（肝癌）
     */
    private String summary;

}
