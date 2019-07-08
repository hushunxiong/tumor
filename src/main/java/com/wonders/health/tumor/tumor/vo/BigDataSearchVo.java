package com.wonders.health.tumor.tumor.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

/**
 * 大数据筛查搜索
 * @author sunyang
 */
@Data
public class BigDataSearchVo extends DataGridSearch {
    private String keyword; //可按照姓名或者身份证号模糊查询

    private String jzdcounty;

    private String jzdtown;

    private String jzdcommittee;

    private String gender;

    private String risk;

    private String[] risks;

    private String orgcode;
}
