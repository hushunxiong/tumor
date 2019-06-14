package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.utils.excel.annotation.ExcelField;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelFile;
import lombok.Data;

/**
* @Description:    肺癌阳性导出
* @Author:         lxl
* @CreateDate:     2019/6/12 10:52
* @UpdateRemark:
* @Version:
*/
@ExcelFile(fileName = "初筛信息汇总（阳性肺癌）")
@Data
public class ExportLucPositive {

    @ExcelField(title = "姓名")
    private String xm; //姓名

    @ExcelField(title = "性别")
    private String xb; //性别

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

    @ExcelField(title = "危险度评估结果")
    private String assessmentResult;//危险度评估结果

    @ExcelField(title = "是否做CT")
    private String shifouLDCT;//是否做CT

    @ExcelField(title = "CT检查结果")
    private String LDCTJieguo;//CT检查结果

    @ExcelField(title = "是否确诊")
    private String sfqz;//是否确诊

    @ExcelField(title = "是否治疗")
    private String sfzl;//是否治疗

    @ExcelField(title = "肿瘤位置")
    private String zlwz;//肿瘤位置

    @ExcelField(title = "肿瘤期别")
    private String zlqb;//肿瘤期别

    @ExcelField(title ="初筛日期" )
    private String csrq; //初筛日期

    }
