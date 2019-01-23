package com.cykj.survey.bean;

import com.cykj.survey.model.Disaster;

import java.util.List;

public class DisasterBean {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private List<Disaster> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Disaster> getData() {
        return data;
    }

    public void setData(List<Disaster> data) {
        this.data = data;
    }
}
