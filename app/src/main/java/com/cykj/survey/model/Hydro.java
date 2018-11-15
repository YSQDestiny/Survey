package com.cykj.survey.model;

import java.io.Serializable;
import java.util.Date;

public class Hydro implements Serializable {

    private Long id;

    private String name;

    /**
     * 地址
     */
    private String addr;
    /**
     *城市
     */
    private String city;
    /**
     *区/县
     */
    private String county;
    /**
     *省
     */
    private String province;
    /**
     * 形式
     */
    private String form;
    /**
     * 大坝类型
     */
    private String type;
    /**
     *大坝材质
     */
    private String material;
    /**
     *轴顶长度
     */
    private double length;
    /**
     *高
     */
    private double high;
    /**
     *正常水位
     */
    private double normal;
    /**
     *设计水位
     */
    private double design;
    /**
     *校核水位
     */
    private double checkWater;
    /**
     * 主要用途
     */
    private String purpose;
    /**
     *投产年限
     */
    private int year;
    /**
     * 机组
     */
    private String crew;
    /**
     * 机组样式
     */
    private String crewStyle;
    /**
     * 状态
     */
    private String state;
    /**
     * 进度
     */
    private String progress;

    /**
     * 是否上传，0未上传，1上传
     */
    private int isUpload;

    /**
     * 设备编码
     */
    private String uniqueId;

    /**
     * 地质
     */
    private String geology;
    /**
     * 火灾
     */
    private String distaster;
    /**
     * 机电设备
     */
    private String electromechanical;
    /**
     * 建筑
     */
    private String building;
    /**
     * 其他
     */
    private String other;
    /**
     * 创建时间
     */
    private Date makeTime;
    /**
     *描述
     */
    private String describe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getDesign() {
        return design;
    }

    public void setDesign(double design) {
        this.design = design;
    }

    public double getCheckWater() {
        return checkWater;
    }

    public void setCheckWater(double checkWater) {
        this.checkWater = checkWater;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getCrewStyle() {
        return crewStyle;
    }

    public void setCrewStyle(String crewStyle) {
        this.crewStyle = crewStyle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(int isUpload) {
        this.isUpload = isUpload;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getGeology() {
        return geology;
    }

    public void setGeology(String geology) {
        this.geology = geology;
    }

    public String getDistaster() {
        return distaster;
    }

    public void setDistaster(String distaster) {
        this.distaster = distaster;
    }

    public String getElectromechanical() {
        return electromechanical;
    }

    public void setElectromechanical(String electromechanical) {
        this.electromechanical = electromechanical;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
