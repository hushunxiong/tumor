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
@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.NONE)
public class XhCancelItem {
    @XmlElement(name = "BookSID")
    private String BookSID;
    @XmlElement(name = "Success")
    private String Success;
    @XmlElement(name = "Message")
    private String Message;
}
