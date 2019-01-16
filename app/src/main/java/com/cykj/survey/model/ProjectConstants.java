package com.cykj.survey.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectConstants {

    public static Long PROJECT_ID;

    public static ProjectModel projectModel;

    public static Map<String,String> sqlMap = new HashMap<>();

    public static List<String> ANALYSIS_SPINNER_ITEM = new ArrayList<>();
    static {
        ANALYSIS_SPINNER_ITEM.add("不易");
        ANALYSIS_SPINNER_ITEM.add("普通");
        ANALYSIS_SPINNER_ITEM.add("容易");
        ANALYSIS_SPINNER_ITEM.add("极易");
    }
}
