package com.cykj.survey.model;

public class AccidentGridModel {

    private String name;

    private boolean isSelect;

    public AccidentGridModel(String name,boolean isSelect){
        this.name = name;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
