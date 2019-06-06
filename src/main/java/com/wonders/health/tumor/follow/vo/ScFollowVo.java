package com.wonders.health.tumor.follow.vo;


import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.follow.entity.ScFollow;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 胃癌随访
 * @author zhangyi
 */
@Data
public class ScFollowVo extends BaseEntity implements Serializable {

    private String manageId; //个人管理编号

    private String checkYear; //初筛年份

    private String scRegcaseId; //胃癌初筛id

    private String personcardType;	// 证件类型

    private String personcard;	// 证件号码

    private String name;	// 姓名

    private String gender;	// 性别

    private Date birth;	// 出生日期

    private String telephone;	// 固定电话

    private String mobile;	// 移动电话

    private String address; //居住地址

    private Date regdate;	// 登记日期

    private String checkResult;	// 判定结果

    private String zdzt; //诊断状态

    private ScFollow scFollow; //胃癌随访信息

    private User user; //登陆人信息

    private String flag; //标志

 }