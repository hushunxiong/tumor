package com.wonders.health.tumor.statistics.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelField;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelFile;
import lombok.Data;

/**
* @Description:    初筛信息汇总表（阴性）导出
* @Author:         lxl
* @CreateDate:     2019/6/12 12:53
* @UpdateRemark:
* @Version:
*/
@Data
@ExcelFile(fileName = "初筛信息汇总表（阴性）")
public class ExportNegative  {

    @ExcelField(title = "姓名")
    private String xm; //姓名

    @ExcelField(title = "性别")
    private String xb; //性别

    @JsonFormat(pattern = "yyyy-MM-dd") //Jackson包使用注解
    @ExcelField(title = "出生日期")
    private String birthday; //出生日期

    @ExcelField(title = "居住地址")
    private String address; //居住地址

    @ExcelField(title = "移动电话")
    private String mobile;//移动电话

    @ExcelField(title = "固定电话")
    private String telephone;//固定电话

    @ExcelField(title = "筛查登记机构")
    private String regorg; //筛查登记机构

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelField(title ="初筛日期" )
    private String csrq; //初筛日期
}
