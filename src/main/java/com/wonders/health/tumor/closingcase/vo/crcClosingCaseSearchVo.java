/**
 * 
 */
package com.wonders.health.tumor.closingcase.vo;


import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.utils.DateUtils;

/**
 * 大肠癌结案搜索
 * @author zhaomeng
 */
public class crcClosingCaseSearchVo extends DataGridSearch {

    private String keyword; //可按照姓名或者身份证号模糊查询
    private String idNumber;   //大肠癌初筛编号
    private String csnf;  //初筛年份
    private String regarea; //登记机构所属区
    private String regorg; //登记机构
    private String crcResult; //大肠癌判定结果
    private String closeStatus; //大肠癌结案结果

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCsnf() {
        return csnf;
    }

    public void setCsnf(String csnf) {
        this.csnf = csnf;
    }

    public String getRegarea() {
        return regarea;
    }

    public void setRegarea(String regarea) {
        this.regarea = regarea;
    }

    public String getRegorg() {
        return regorg;
    }

    public void setRegorg(String regorg) {
        this.regorg = regorg;
    }

    public String getCrcResult() {
        return crcResult;
    }

    public void setCrcResult(String crcResult) {
        this.crcResult = crcResult;
    }

    public String getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(String closeStatus) {
        this.closeStatus = closeStatus;
    }
}