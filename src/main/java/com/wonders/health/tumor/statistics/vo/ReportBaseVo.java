package com.wonders.health.tumor.statistics.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
* @Description:    抽取报表相同字段作为基类被继承
* @Author:         lxl
* @CreateDate:     2019/5/31 9:21
* @UpdateRemark:
* @Version:
*/
@Data
public class ReportBaseVo {
    private String xm; //姓名

    private String xb; //性别

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //Jackson包使用注解
    private String birthday; //出生日期

    private String address; //居住地址

    private String mobile;//移动电话

    private String telephone;//固定电话

    private String regorg; //筛查登记机构
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //Jackson包使用注解
    private String csrq; //初筛日期

    private String assessmentResult;//危险度评估结果

    private String sfqz;//是否确诊

    private String sfzl;//是否治疗

    private String zlwz;//肿瘤位置

    private String zlqb;//肿瘤期别

    private String addressProvince; //居住地-省（自治区、直辖市）12位统计用区划代码和城乡划分代码

    private String addressCity; //居住地-市（地区）12位统计用区划代码和城乡划分代码

    private String addressCounty; //居住地-县（区）12位统计用区划代码和城乡划分代码

    private String addressTown; //居住地-乡（镇、街道）12位统计用区划代码和城乡划分代码

    private String addressCommittee; //居住地-居委会、村 12位统计用区划代码和城乡划分代码

    private String addressDetail; //居住地址-详细
}
