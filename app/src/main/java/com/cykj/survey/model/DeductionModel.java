package com.cykj.survey.model;

import java.util.List;

public class DeductionModel {

    /**
     * 扣分
     */
    private int deduction;

    /**
     * 区域
     */
    private String area;

    /**
     * 扣分原因
     */
    private List<String> reson;

    /**
     * 是否重点区域
     */
    private int important;

    /**
     * 总分
     */
    private int total;

    /**
     * 是否缺项
     */
    private boolean isMissing;

    public int getDeduction() {
        return deduction;
    }

    public void setDeduction(int deduction) {
        this.deduction = deduction;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getReson() {
        return reson;
    }

    public void setReson(List<String> reson) {
        this.reson = reson;
    }

    public boolean isMissing() {
        return isMissing;
    }

    public void setMissing(boolean missing) {
        isMissing = missing;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }
}
