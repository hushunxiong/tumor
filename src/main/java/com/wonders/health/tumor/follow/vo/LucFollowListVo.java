package com.wonders.health.tumor.follow.vo;


import com.wonders.health.tumor.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 肺癌随访一览列表
 * @author zhangyi
 */
@Data
public class LucFollowListVo extends BaseEntity implements Serializable {

    private String id;	// 主键

    private String suifangDate;	// 随访日期

    private String suifangyishengName;	// 随访医生名称

    private String suifangjigouName;	// 随访医疗机构名称

 }