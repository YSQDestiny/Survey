package com.cykj.survey.model;

import java.util.Date;

public class Property {

    private Long id;
    /**
     *项目名称
     */
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
     *详细地址
     */
    private String address;
    /**
     *建成日期
     */
    private String createDate;
    /**
     *占地面积
     */
    private String acreage;
    /**
     *住宅
     */
    private String residence;
    /**
     *商铺
     */
    private String shops;
    /**
     * 住户
     */
    private String household;
    /**
     * 租户
     */
    private String tenant;
    /**
     * 收费标准
     */
    private String charge;
    /**
     * 地面停车位
     */
    private String groundParking;
    /**
     * 地下停车位
     */
    private String underGroundParkting;
    /**
     * 配套设备
     */
    private String equiment;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 工商注册时间
     */
    private String companyDate;
    /**
     * 工商注册地址
     */
    private String companyAddr;
    /**
     * 注册资金
     */
    private String capital;
    /**
     * 服务开始时间
     */
    private String enterDate;
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
     * 设备码
     */
    private String uniqueId;

    /**
     * 创建时间

     */
    private Date makeTime;

    /**
     * 三张照片
     */
    private String businessPhoto;
    private String industryPhoto;
    private String systemPhoto;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getShops() {
        return shops;
    }

    public void setShops(String shops) {
        this.shops = shops;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getGroundParking() {
        return groundParking;
    }

    public void setGroundParking(String groundParking) {
        this.groundParking = groundParking;
    }

    public String getUnderGroundParkting() {
        return underGroundParkting;
    }

    public void setUnderGroundParkting(String underGroundParkting) {
        this.underGroundParkting = underGroundParkting;
    }

    public String getEquiment() {
        return equiment;
    }

    public void setEquiment(String equiment) {
        this.equiment = equiment;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDate() {
        return companyDate;
    }

    public void setCompanyDate(String companyDate) {
        this.companyDate = companyDate;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
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

    public String getBusinessPhoto() {
        return businessPhoto;
    }

    public void setBusinessPhoto(String businessPhoto) {
        this.businessPhoto = businessPhoto;
    }

    public String getIndustryPhoto() {
        return industryPhoto;
    }

    public void setIndustryPhoto(String industryPhoto) {
        this.industryPhoto = industryPhoto;
    }

    public String getSystemPhoto() {
        return systemPhoto;
    }

    public void setSystemPhoto(String systemPhoto) {
        this.systemPhoto = systemPhoto;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
