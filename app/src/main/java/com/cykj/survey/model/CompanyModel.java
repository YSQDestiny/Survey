package com.cykj.survey.model;

import java.util.List;

public class CompanyModel {

    private Company companyEntity;
    private List<Record> records;
    private List<Accident> accidents;

    public Company getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(Company companyEntity) {
        this.companyEntity = companyEntity;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<Accident> accidents) {
        this.accidents = accidents;
    }
}
