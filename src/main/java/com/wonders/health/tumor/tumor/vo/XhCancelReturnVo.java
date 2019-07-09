package com.wonders.health.tumor.tumor.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by suny on 2019/7/6.
 */
@Data
@XmlRootElement(name="response")
public class XhCancelReturnVo {
    private XhCancelVo itemList;
}
