package com.cykj.survey.bean;

import com.cykj.survey.model.ProjectModel;

import java.util.List;

/**
 * 工程返回列表
 */
public class ResultBean {

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
   private List<ProjectModel> data;

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

    public List<ProjectModel> getData() {
        return data;
    }

    public void setData(List<ProjectModel> data) {
        this.data = data;
    }
}
