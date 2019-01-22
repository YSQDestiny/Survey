package com.cykj.survey.model;

import java.util.Date;

public class ProjectModel {

    private Long id;

    private String name;

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
     * 镇
     */
    private String town;

    private String addr;

    /**
     * 施工单位
     */
    private String construction;

    /**
     * 建设单位
     */
    private String building;

    private String type;

    /**
     * 查勘类型
     */
    private String reviewType;

    /**
     * 委托方
     */
    private String client;
    /**
     * 委托方联系人
     */
    private String clientContact;
    /**
     * 委托方联系电话
     */
    private String clientPhone;

    /**
     * 地质描述
     */
    private String geological;

    /**
     * 地质分析
     */
    private String analysis;

    private String uniqueId;

    private String makeTime;

    private String score;

    private String passingPost;

    private String level;

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getGeological() {
        return geological;
    }

    public void setGeological(String geological) {
        this.geological = geological;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPassingPost() {
        return passingPost;
    }

    public void setPassingPost(String passingPost) {
        this.passingPost = passingPost;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
