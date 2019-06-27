package com.wonders.health.tumor.common.entity;

import lombok.Data;

/**
 * Created by Administrator on 2019/4/17.
 */
@Data
public class CancerDicArea {
    private String ccode;

    private String cname;

    private String cparent;

    private Integer clevel;

    private String yljgdm;

    private String helpcode;

    private String yxbz;	//  0-无效 1-有效

}
