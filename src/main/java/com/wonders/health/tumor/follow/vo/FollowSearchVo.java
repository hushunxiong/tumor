package com.wonders.health.tumor.follow.vo;

import lombok.Data;

/**
 * 四种癌症随访一览搜索条件
 * @author sunyang
 */
@Data
public class FollowSearchVo {
    private String crcCheckId;

    private String licCheckId;

    private String scCheckId;

    private String lucCheckId;
}
