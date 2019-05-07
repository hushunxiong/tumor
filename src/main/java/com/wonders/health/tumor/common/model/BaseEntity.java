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
    public static final String[] IGNORES = {"delFlag", "createDate", "createBy", "updateDate", "updateBy"};

    protected String delFlag;

    protected Date createDate;

    protected String createBy;

    protected Date updateDate;

    protected String updateBy;

    protected String remarks;

    public void initByUpdate() {
        this.updateDate = new Date();
    }

    public void initByUpdate(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            this.updateBy = userId;
        }
        this.updateDate = new Date();
    }

    public void init(String userId) {
        init();
        this.createBy = userId;
        this.updateBy = userId;
    }

    public void init() {
        this.delFlag = DEL_FLAG_NORMAL;
        this.createDate = new Date();
        this.updateDate = this.createDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
