package com.cykj.survey.model;

import java.util.Date;

public class ProjectModel {

    private String name;

    private String department;

    private String project_target;

    private String start;

    private String ending;

    private String area;

    private double length;

    private String geological;

    private String uniqueId;

    private Date makeTime;

    private String type;

    private String score;

    private String passingPost;

    public String getPassingPost() {
        return passingPost;
    }

    public void setPassingPost(String passingPost) {
        this.passingPost = passingPost;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProject_target() {
        return project_target;
    }

    public void setProject_target(String project_target) {
        this.project_target = project_target;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getGeological() {
        return geological;
    }

    public void setGeological(String geological) {
        this.geological = geological;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
