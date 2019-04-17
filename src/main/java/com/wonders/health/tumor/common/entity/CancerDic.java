package com.wonders.health.tumor.common.entity;

import com.wonders.health.tumor.common.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by sunyang on 2019/4/16.
 */
public class CancerDic extends BaseEntity {
    private String id;	// 字典类型

    private String code;	// 字典编码

    private String name;	// 字典名称

    private String pcode;	// 父编码

    private String pingyin;	// 拼音

    private String sourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
