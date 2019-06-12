package com.wonders.health.tumor.busremind.vo;

import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

@Data
public class BusRemindSearchVo  extends DataGridSearch {
    private String keyword;     //可按照姓名或者身份证号模糊查询
    private String year;        //筛查年份
    private String orgCode;     //登记机构
    private String remindType;        //业务提醒类型
    private String status;      //业务提醒状态
    private String startDate;   //计划提醒开始时间
    private String endDate;     //计划提醒结束时间

    private String crcFlag;
    private String licFlag;
    private String scFlag;
    private String lucFlag;
}
