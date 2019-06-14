/**
 * 
 */
package com.wonders.health.tumor.closingcase.vo;


import com.wonders.health.tumor.common.model.DataGridSearch;
import lombok.Data;

/**
 * 肺癌结案搜索
 * @author zhaomeng
 */
@Data
public class LucClosingCaseSearchVo extends DataGridSearch {

    private String keyword; //可按照姓名或者证件号码模糊查询
    private String csnf;  //初筛年份
    private String regarea; //登记机构所属区
    private String regorg; //登记机构
    private String checkResult; //判定结果
    private String closeResult; //结案结果

}