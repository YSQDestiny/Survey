package com.cykj.survey.bean;

public class IndustryBean {

    @TreeNodeId
    private int id;

    @TreeNodePid
    private int parentId;

    @TreeNodeLabel
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
