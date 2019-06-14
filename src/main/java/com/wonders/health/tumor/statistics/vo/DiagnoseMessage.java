package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.utils.excel.annotation.ExcelField;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelFile;
import lombok.Data;

/**
 * 诊断信息收集表导出
 */
@ExcelFile(fileName = "诊断信息收集")
@Data
public class DiagnoseMessage {
    @ExcelField(title = "姓名")
    private String xm; //姓名

    @ExcelField(title = "证件类型")
    private String personcardType;//证件类型

    @ExcelField(title = "证件号码")
    private String personcard;//证件号码

    @ExcelField(title = "筛查登记机构")
    private String regorg; //筛查登记机构

    @ExcelField(title = "检查项目")
    private String jcxm;  //检查项目

    @ExcelField(title="病变部位")
    private String bbbw;  //病变部位

    @ExcelField(title="肿瘤大小（最大直径）")
    private String zldx;  //肿瘤大小（最大直径）

    @ExcelField(title="是否活检")
    private String sfhj;  //是否活检

    @ExcelField(title="活检结果")
    private String hjjg;  //活检结果

    @ExcelField(title="是否病理")
    private String sfbl;  //是否病理

    @ExcelField(title="病理类型")
    private String bllx;  //病理类型

    @ExcelField(title="TNM分期")
    private String TNMfq; //TNM分期

    @ExcelField(title="随访日期")
    private String sfrq;  //随访日期



}
