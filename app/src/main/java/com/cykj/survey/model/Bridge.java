package com.cykj.survey.model;

import java.io.Serializable;

/**
 * 桥梁属性
 */
public class Bridge implements Serializable{

    /**
     * 桥梁名称
     */
    private String bridgeName;

    /**
     * 桩号
     */
    private String stationNumber;

    /**
     * 桥长
     */
    private double length;

    /**
     * 孔跨类型
     */
    private String porespanType;

    /**
     * 桥墩以及基础
     */
    private String basis;

    /**
     * 桥梁类型
     */
    private String type;

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getPorespanType() {
        return porespanType;
    }

    public void setPorespanType(String porespanType) {
        this.porespanType = porespanType;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
