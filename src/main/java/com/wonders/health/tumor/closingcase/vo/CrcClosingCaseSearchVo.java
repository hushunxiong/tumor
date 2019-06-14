/**
 * 
 */
package com.wonders.health.tumor.closingcase.vo;


import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;
/**
 * 大肠癌结案搜索
 * @author zhaomeng
 */
@Data
public class CrcClosingCaseSearchVo extends DataGridSearch {

    private String keyword; //可按照姓名或者身份证号模糊查询
    private String idNumber;   //大肠癌初筛编号
    private String csnf;  //初筛年份
    private String regarea; //登记机构所属区
    private String regorg; //登记机构
    private String checkResult; //大肠癌判定结果
    private String closeResult; //大肠癌结案结果


}