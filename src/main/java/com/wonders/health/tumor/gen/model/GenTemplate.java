package com.wonders.health.tumor.gen.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 生成方案Entity
 *
 * @version 2013-10-15
 */
@XmlRootElement(name = "template")
public class GenTemplate implements Serializable {

    private String name;    // 名称
    private String fileName;        // 文件名
    private String content;        // 内容


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}


