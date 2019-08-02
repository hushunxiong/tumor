package com.wonders.health.tumor.statistics.vo;

import lombok.Data;

/**
 * @Description:    社区卫生服务中心肿瘤早发现进度表(下拉數據)
 * @Author:         hushunxiong
 * @CreateDate:     2019/7/19 18:22
 * @UpdateRemark:
 * @Version:
 */
public class BoxData {
    private  String code;
    private  String name;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
