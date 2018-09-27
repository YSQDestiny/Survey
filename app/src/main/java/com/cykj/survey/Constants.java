package com.cykj.survey;

import com.cykj.survey.model.Deduction;
import com.cykj.survey.model.Options;
import com.cykj.survey.model.ProjectAccident;

import java.util.ArrayList;
import java.util.List;

public class Constants {

//    public final static String TEST_SERVICE = "http://destiny.s1.natapp.cc/CYKJ";

//      public final static String TEST_SERVICE = "http://47.92.209.204:80/CYKJ";

      public final static String TEST_SERVICE = "http://192.168.0.123:8080/CYKJ";

//    public final static String TEST_SERVICE = "http://192.168.199.167:8080/CYKJ";

    public static   Long REPORT_ID;

    public static Long PROJECT_ID;

    public static Long PROPERTY_ID;

    public void setProjectId(Long projectId){
        this.PROJECT_ID = projectId;
    }

    public void setReportId(Long reportId){
        this.REPORT_ID = reportId;
    }

    public void setPropertyId(Long PROPERTY_ID){
        this.PROPERTY_ID = PROPERTY_ID;
    }

    public static List<ProjectAccident> projectAccidentList = new ArrayList<>();

    public static void addProjectAccident(ProjectAccident accident){
        projectAccidentList.add(accident);
    }

    public static List<String> districtList;

    public static void setDistrictList(List<String> districtList){
        Constants.districtList = districtList;
    }

    public static List<Deduction> deductionList = new ArrayList<>();

    public static void addDeduction(Deduction deduction){
        deductionList.add(deduction);
    }

    public static void removeDeduction() {
        deductionList.clear();
    }
}
