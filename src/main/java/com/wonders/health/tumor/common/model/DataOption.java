package com.wonders.health.tumor.common.model;

import java.io.Serializable;

/**
 * Created by xuguobing on 2017/5/15.
 */
public class DataOption implements Serializable {

    private String id;
    private String text;
    private String pinyin;

    public DataOption() {
    }

    public DataOption(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public DataOption(String id, String text, String pinyin) {
        this.id = id;
        this.text = text;
        this.pinyin = pinyin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
