package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.utils.excel.annotation.ExcelField;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelFile;
import lombok.Data;

/**
 * 社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出
 */
@ExcelFile(fileName = "社区卫生服务中心肿瘤早发现进度表（区疾控登录的场合）")
@Data
public class ExportLucSeAreaControl {
    @ExcelField(title = "机构名称")
    private String jgmc; //机构名称

    @ExcelField(title = "登记人数")
    private String djrs; //登记人数

    @ExcelField(title = "完成筛查人数")
    private String wcscrs;//完成筛查人数

    @ExcelField(title = "筛查阳性数")
    private String scyxs; //筛查阳性数

    @ExcelField(title = "筛查阳性率")
    private String scyxl;  //筛查阳性率

    @ExcelField(title="CT检查数")
    private String ctjcs; //CT检查数

    @ExcelField(title="CT检查率")
    private String ctjcl; //CT检查率

    @ExcelField(title="肺癌病例数")
    private String fabls; //肺癌病例数

    @ExcelField(title="确诊早期数")
    private String qzzqs;  //确诊早期数

    @ExcelField(title="确诊早期率")
    private String qzzql; //确诊早期率

}
