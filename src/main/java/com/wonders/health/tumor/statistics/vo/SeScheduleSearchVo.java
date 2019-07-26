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
     *  筛查登记机构
     */
    private String regorg;

    /**
     * 初筛年份
     */
    private String year;

    /**
     *癌症种类
     */
    private String summary;

}
