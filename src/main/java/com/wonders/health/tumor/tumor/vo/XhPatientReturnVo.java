package com.wonders.health.tumor.tumor.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by suny on 2019/6/26.
 */
@Data
@XmlRootElement(name="response")
public class XhPatientReturnVo {
    private String resultCode;

    private String resultMessage;

    private XhPatientResultVo result;
}
