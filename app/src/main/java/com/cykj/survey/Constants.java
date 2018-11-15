package com.cykj.survey;

import com.cykj.survey.model.Deduction;
import com.cykj.survey.model.Hydro;
import com.cykj.survey.model.Options;
import com.cykj.survey.model.PhotoModel;
import com.cykj.survey.model.ProjectAccident;
import com.cykj.survey.model.Property;
import com.cykj.survey.model.PropertyAccident;
import com.cykj.survey.model.UploadModel;

import java.util.ArrayList;
import java.util.List;

public class Constants {

//    public final static String TEST_SERVICE = "http://destiny.s1.natapp.cc/CYKJ";

//      public final static String TEST_SERVICE = "http://47.92.209.204:80/CYKJ";

      public final static String TEST_SERVICE = "http://192.168.0.112:8080/CYKJ";

//    public final static String TEST_SERVICE = "http://192.168.199.167:8080/CYKJ";

    public static boolean NETWORK_FLAG;

    public static   Long REPORT_ID;

    public static Long PROJECT_ID;

    public static Long PROPERTY_ID;

    public static Long HYDRO_ID;

    public static String DEDUCTION_JSON;

    public static void setDeductionJson(String json){
        DEDUCTION_JSON = json;
    }

    public static void cleanDeductionJson(){
        DEDUCTION_JSON = "";
    }

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

    public static void reDeduction(Deduction deduction){
        deductionList.remove(deduction);
    }

    public static PhotoModel photoModel;

    public static void setPhotoModel(PhotoModel model){
        photoModel = model;
    }

    public static List<PropertyAccident> propertyAccidentList = new ArrayList<>();

    public static void addPropertyAccident(PropertyAccident propertyAccident){
        propertyAccidentList.add(propertyAccident);
    }

    public static void cleanPropertyAccidentList(){
        propertyAccidentList.clear();
    }

    public static List<UploadModel> uploadModelList = new ArrayList<>();

    public static void addUploadModel(UploadModel uploadModel){
        uploadModelList.add(uploadModel);
    }

    public static void cleanUploadList(){
        uploadModelList.clear();
    }

    public static Hydro hydro;

    public static void setHydro(Hydro hydro1){
        hydro = hydro1;
    }
}
