package com.wonders.health.tumor.tumor.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by suny on 2019/7/6.
 */
@Data
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.NONE)
public class XhCancelReturnVo {
    @XmlElement(name = "ItemList")
    private XhCancelVo itemList;
}
