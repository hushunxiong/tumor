package com.wonders.health.tumor.tumor.vo;

import lombok.Data;

/**
 * Created by suny on 2019/7/18.
 */
@Data
public class ReturnExternalPerson {
    private String result;
    private String msg;
    private ExternalPersonInfoVo data;
}
