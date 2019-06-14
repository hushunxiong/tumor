package com.wonders.health.tumor.follow.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

/**
 * 随访一览搜索
 * @author sunyang
 */
@Data
public class FollowPersonInfoSearchVo extends DataGridSearch {
    private String keyword; //可按照姓名或者身份证号模糊查询
    private String csnf;  //初筛年份
    private String regarea; //登记机构所属区
    private String regorg; //登记机构
    private String beginDate; //初筛登记日期开始
    private String endDate; //初筛登记日期结束

    private String crcResult;//大肠癌判定结果
    private String licResult;//肝癌判定结果
    private String scResult; //胃癌判定结果
    private String lucResult; //肺癌判定结果

    private String crcStatus;//大肠癌随访状态
    private String licStatus;//肝癌随访状态
    private String scStatus;//胃癌随访状态
    private String lucStatus;//肺癌随访状态
    private String crcFlag;
    private String lucFlag;
    private String licFlag;
    private String scFlag;
}
