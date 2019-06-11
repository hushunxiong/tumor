package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 初筛信息汇总表（阴性）Vo
 * @Author: lxl
 * @CreateDate: 2019/5/30 16:20
 * @UpdateRemark:
 * @Version:
 */
@Data
public class NegativeSummaryVo  {

    private String xm; //姓名

    private String xb; //性别

    private String birthday; //出生日期

    private String address; //居住地址

    private String mobile;//移动电话

    private String telephone;//固定电话

    private String regorg; //筛查登记机构

    private String csrq; //初筛日期

    private String addressProvince; //居住地-省（自治区、直辖市）12位统计用区划代码和城乡划分代码

    private String addressCity; //居住地-市（地区）12位统计用区划代码和城乡划分代码

    private String addressCounty; //居住地-县（区）12位统计用区划代码和城乡划分代码

    private String addressTown; //居住地-乡（镇、街道）12位统计用区划代码和城乡划分代码

    private String addressCommittee; //居住地-居委会、村 12位统计用区划代码和城乡划分代码

    private String addressDetail; //居住地址-详细

}
