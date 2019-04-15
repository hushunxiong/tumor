package com.wonders.health.tumor.common.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuguobing on 2016/10/28 0028.
 */
public class BaseEntity implements Serializable {

    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String[] IGNORES = {"delFlag", "createDt", "createBy", "updateDt", "updateBy"};

    protected String delFlag;

    protected Date createDt;

    protected String createBy;

    protected Date updateDt;

    protected String updateBy;

    protected String remarks;

    public void initByUpdate() {
        this.updateDt = new Date();
    }

    public void initByUpdate(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            this.updateBy = userId;
        }
        this.updateDt = new Date();
    }

    public void init(String userId) {
        init();
        this.createBy = userId;
        this.updateBy = userId;
    }

    public void init() {
        this.delFlag = DEL_FLAG_NORMAL;
        this.createDt = new Date();
        this.updateDt = this.createDt;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}
