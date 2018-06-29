package com.cykj.survey.model;

import java.util.Date;
import java.util.List;

public class Company {

    private Long id;
    private String addr;
    private String industry;
    private String linkman;
    private Date makeTime;
    private String manager;
    private String name;
    private String safe;
    private String uniqueId;
    private String viceManager;
    private Integer wokerNormal;
    private Integer wokerSpecial;
    private String businessPhoto;
    private String industryPhoto;
    private String systemPhoto;
    private Integer amount;
    private Integer assets;
    private String coverage;
    private String insurance;
    private String city;
    private String county;
    private String province;
    private String companyCode;
    private String other;
    private String phoneNumber;
    private String town;
    private String client;
    private String clientContact;
    private String clientContactPhone;

    public Company() {
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

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getViceManager() {
        return viceManager;
    }

    public void setViceManager(String viceManager) {
        this.viceManager = viceManager;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public Integer getWokerNormal() {
        return wokerNormal;
    }

    public void setWokerNormal(Integer wokerNormal) {
        this.wokerNormal = wokerNormal;
    }

    public Integer getWokerSpecial() {
        return wokerSpecial;
    }

    public void setWokerSpecial(Integer wokerSpecial) {
        this.wokerSpecial = wokerSpecial;
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }


    public Integer getAssets() {
        return assets;
    }

    public void setAssets(Integer assets) {
        this.assets = assets;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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

    public String getClientContactPhone() {
        return clientContactPhone;
    }

    public void setClientContactPhone(String clientContactPhone) {
        this.clientContactPhone = clientContactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()){ return false;}

        Company that = (Company) o;
        if (id != null ? !id.equals(that.id) : that.id != null){ return  false;}
        if (addr != null ? !addr.equals(that.addr) : that.addr != null){ return false;}
        if (industry != null ? !industry.equals(that.industry) : that.industry != null){ return false;}
        if (linkman != null ? !linkman.equals(that.linkman) : that.linkman != null){ return false;}
        if (makeTime != null ? !makeTime.equals(that.makeTime) : that.makeTime != null){ return false;}
        if (manager != null ? !manager.equals(that.manager) : that.manager != null){ return false;}
        if (name != null ? !name.equals(that.name) : that.name != null){ return false;}
        if (safe != null ? !safe.equals(that.safe) : that.safe != null){ return false;}
        if (uniqueId != null ? !uniqueId.equals(that.uniqueId) : that.uniqueId != null){ return false;}
        if (viceManager != null ? !viceManager.equals(that.viceManager) : that.viceManager != null){ return false;}
        if (wokerNormal != null ? !wokerNormal.equals(that.wokerNormal) : that.wokerNormal != null){ return false;}
        if (wokerSpecial != null ? !wokerSpecial.equals(that.wokerSpecial) : that.wokerSpecial != null){ return false;}
        if (businessPhoto != null ? !businessPhoto.equals(that.businessPhoto) : that.businessPhoto != null)
        { return false;}
        if (industryPhoto != null ? !industryPhoto.equals(that.industryPhoto) : that.industryPhoto != null)
        { return false;}
        if (systemPhoto != null ? !systemPhoto.equals(that.systemPhoto) : that.systemPhoto != null){ return false;}
        if (amount != null ? !amount.equals(that.amount) : that.amount != null){ return false;}
        if (assets != null ? !assets.equals(that.assets) : that.assets != null) { return false;}
        if (coverage != null ? !coverage.equals(that.coverage) : that.coverage != null){ return false;}
        if (insurance != null ? !insurance.equals(that.insurance) : that.insurance != null) { return false;}
        if (city != null ? !city.equals(that.city) : that.city != null) { return false;}
        if (county != null ? !county.equals(that.county) : that.county != null) { return false;}
        if (province != null ? !province.equals(that.province) : that.province != null) { return false;}
        if (companyCode != null ? !companyCode.equals(that.companyCode) : that.companyCode != null) { return false;}
        if (other != null ? !other.equals(that.other) : that.other != null) { return false;}
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) { return false;}
        if (town != null ? !town.equals(that.town) : that.town != null) { return false;}
        if (client != null ? !client.equals(that.client) : that.client != null) { return false;}
        if (clientContact != null ? !clientContact.equals(that.clientContact) : that.clientContact != null)
        { return false;}
        if (clientContactPhone != null ? !clientContactPhone.equals(that.clientContactPhone) : that.clientContactPhone != null)
        { return false;}

        return true;
    }
}
