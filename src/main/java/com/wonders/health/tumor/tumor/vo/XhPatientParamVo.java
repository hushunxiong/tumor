package com.wonders.health.tumor.tumor.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *徐汇肿瘤接口参数vo
 *
 **/
@Data
@XmlAccessorType(XmlAccessType.FIELD)

// XML文件中的根标识
@XmlRootElement(name = "params")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "hzxm",
        "cardtype",
        "cardno",
        "ybdm",
        "sex",
        "sfzh",
        "birth",
        "lxdz",
        "lxdh",
        "memo",
        "qxbm",
        "sqbm"
})
public class XhPatientParamVo {

    private String hzxm;

    private String cardtype;

    private String cardno;

    private String ybdm;

    private String sex;

    private String sfzh;

    private String birth;

    private String lxdz;

    private String lxdh;

    private String memo;

    private String qxbm;

    private String sqbm;
}
