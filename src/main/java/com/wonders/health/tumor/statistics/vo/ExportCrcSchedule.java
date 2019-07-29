package com.wonders.health.tumor.statistics.vo;

import com.wonders.health.tumor.common.utils.excel.annotation.ExcelField;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelFile;
import lombok.Data;

/**
 * 社区卫生服务中心肿瘤早发现进度表(社区登录的场合)导出
 */
@ExcelFile(fileName = "社区卫生服务中心肿瘤早发现进度表（社区登录的场合）")
@Data
public class ExportCrcSchedule {
    @ExcelField(title = "月份")
    private String yf; //月份

    @ExcelField(title = "登记人数")
    private String djrs; //登记人数

    @ExcelField(title = "完成筛查人数")
    private String wcscrs;//完成筛查人数

    @ExcelField(title = "筛查阳性数")
    private String scyxs; //筛查阳性数

    @ExcelField(title = "筛查阳性率")
    private String scyxl;  //筛查阳性率

    @ExcelField(title="肠镜检查数")
    private String cjjcs;  //肠镜检查数

    @ExcelField(title="肠镜检查率")
    private String cjjcl;  //肠镜检查率

    @ExcelField(title="癌前期病变数")
    private String aqqbbs;  //癌前期病变数

    @ExcelField(title="癌前期病变率")
    private String aqqbbl;  //癌前期病变率

    @ExcelField(title="大肠癌病例数")
    private String dcabls;  //大肠癌病例数

    @ExcelField(title="确诊早期数")
    private String qzzqs;  //确诊早期数

    @ExcelField(title="确诊早期率")
    private String qzzql; //确诊早期率

}
