package com.wonders.health.tumor.common.model;

import java.io.Serializable;

/**
 * Created by xuguobing on 2016/10/26 0026.
 */
public class AjaxReturn<T> implements Serializable {
    private Boolean ok;
    private String msg;
    private T value;

    public AjaxReturn() {
    }

    public AjaxReturn(Boolean ok) {
        this.ok = ok;
    }

    public AjaxReturn(Boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public AjaxReturn(Boolean ok, String msg, T value) {
        this.ok = ok;
        this.msg = msg;
        this.value = value;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
