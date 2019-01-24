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

    public static List<String> PROJECT_TYPE = new ArrayList<>();

    public static String REVIEW_TYPE = "";

    public static Map<String,String> COMPLEX_MAP = new HashMap<>();
    public static Map<String,String> IMPORTANT_MAP = new HashMap<>();

    /**
     * 初始化静态数据
     */
    static {
        ANALYSIS_SPINNER_ITEM.add("请选择");
        ANALYSIS_SPINNER_ITEM.add("不易");
        ANALYSIS_SPINNER_ITEM.add("普通");
        ANALYSIS_SPINNER_ITEM.add("容易");
        ANALYSIS_SPINNER_ITEM.add("极易");

        //
        PROJECT_TYPE.add("开发区建设");
        PROJECT_TYPE.add("城镇新区建设");
        PROJECT_TYPE.add("放射性设施");
        PROJECT_TYPE.add("军事设施");
        PROJECT_TYPE.add("核电");
        PROJECT_TYPE.add("二级（含）以上公路");
        PROJECT_TYPE.add("铁路");
        PROJECT_TYPE.add("机场");
        PROJECT_TYPE.add("大型水利工程");
        PROJECT_TYPE.add("大型电力工程");
        PROJECT_TYPE.add("大型洪口码头");
        PROJECT_TYPE.add("大型集中供水水源地");
        PROJECT_TYPE.add("大型工业建筑");
        PROJECT_TYPE.add("大型民用建筑");
        PROJECT_TYPE.add("大型垃圾处理场");
        PROJECT_TYPE.add("大型水处理厂");

        PROJECT_TYPE.add("新建村庄");
        PROJECT_TYPE.add("三级（含）以下公路");
        PROJECT_TYPE.add("中型水利工程");
        PROJECT_TYPE.add("中型电力工程");
        PROJECT_TYPE.add("中型洪口码头");
        PROJECT_TYPE.add("中型集中供水水源地");
        PROJECT_TYPE.add("中型工业建筑");
        PROJECT_TYPE.add("中型民用建筑");
        PROJECT_TYPE.add("中型垃圾处理场");
        PROJECT_TYPE.add("中型水处理厂");
        PROJECT_TYPE.add("中型矿山");

        PROJECT_TYPE.add("小型水利工程");
        PROJECT_TYPE.add("小型电力工程");
        PROJECT_TYPE.add("小型洪口码头");
        PROJECT_TYPE.add("小型集中供水水源地");
        PROJECT_TYPE.add("小型工业建筑");
        PROJECT_TYPE.add("小型民用建筑");
        PROJECT_TYPE.add("小型垃圾处理场");
        PROJECT_TYPE.add("小型水处理厂");
        PROJECT_TYPE.add("小型矿山");

        COMPLEX_MAP.put("强烈","复杂");
        COMPLEX_MAP.put("复杂","复杂");
        COMPLEX_MAP.put("复杂、变化大、不良","复杂");
        COMPLEX_MAP.put("不良","复杂");
        COMPLEX_MAP.put("活动强烈","复杂");

        COMPLEX_MAP.put("中等","中等");
        COMPLEX_MAP.put("简单、单一","中等");
        COMPLEX_MAP.put("较复杂、不稳定、较差","中等");
        COMPLEX_MAP.put("较差","中等");
        COMPLEX_MAP.put("活动较强烈","中等");

        COMPLEX_MAP.put("不发育","简单");
        COMPLEX_MAP.put("较简单、单一","简单");
        COMPLEX_MAP.put("简单、单一、良好","简单");
        COMPLEX_MAP.put("良好","简单");
        COMPLEX_MAP.put("活动一般","简单");

        IMPORTANT_MAP.put("开发区建设","重要");
        IMPORTANT_MAP.put("城镇新区建设","重要");
        IMPORTANT_MAP.put("放射性设施","重要");
        IMPORTANT_MAP.put("军事设施","重要");
        IMPORTANT_MAP.put("核电","重要");
        IMPORTANT_MAP.put("二级（含）以上公路","重要");
        IMPORTANT_MAP.put("铁路","重要");
        IMPORTANT_MAP.put("机场","重要");
        IMPORTANT_MAP.put("大型水利工程","重要");
        IMPORTANT_MAP.put("大型电力工程","重要");
        IMPORTANT_MAP.put("大型洪口码头","重要");
        IMPORTANT_MAP.put("大型集中供水水源地","重要");
        IMPORTANT_MAP.put("大型工业建筑","重要");
        IMPORTANT_MAP.put("大型民用建筑","重要");
        IMPORTANT_MAP.put("大型垃圾处理场","重要");
        IMPORTANT_MAP.put("大型水处理厂","重要");

        IMPORTANT_MAP.put("新建村庄","较重要");
        IMPORTANT_MAP.put("三级（含）以下公路","较重要");
        IMPORTANT_MAP.put("中型水利工程","较重要");
        IMPORTANT_MAP.put("中型电力工程","较重要");
        IMPORTANT_MAP.put("中型洪口码头","较重要");
        IMPORTANT_MAP.put("中型集中供水水源地","较重要");
        IMPORTANT_MAP.put("中型工业建筑","较重要");
        IMPORTANT_MAP.put("中型民用建筑","较重要");
        IMPORTANT_MAP.put("中型垃圾处理场","较重要");
        IMPORTANT_MAP.put("中型水处理厂","较重要");
        IMPORTANT_MAP.put("中型矿山","较重要");

        IMPORTANT_MAP.put("小型水利工程","一般");
        IMPORTANT_MAP.put("小型电力工程","一般");
        IMPORTANT_MAP.put("小型洪口码头","一般");
        IMPORTANT_MAP.put("小型集中供水水源地","一般");
        IMPORTANT_MAP.put("小型工业建筑","一般");
        IMPORTANT_MAP.put("小型民用建筑","一般");
        IMPORTANT_MAP.put("小型垃圾处理场","一般");
        IMPORTANT_MAP.put("小型水处理厂","一般");
        IMPORTANT_MAP.put("小型矿山","一般");
    }

}
