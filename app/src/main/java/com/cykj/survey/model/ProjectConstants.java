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

        COMPLEX_MAP.put("复杂","强烈");
        COMPLEX_MAP.put("复杂","复杂");
        COMPLEX_MAP.put("复杂","复杂、变化大、不良");
        COMPLEX_MAP.put("复杂","不良");
        COMPLEX_MAP.put("复杂","强烈");

        COMPLEX_MAP.put("中等","中等");
        COMPLEX_MAP.put("中等","简单、单一");
        COMPLEX_MAP.put("中等","较简单、单一");
        COMPLEX_MAP.put("中等","较差");
        COMPLEX_MAP.put("中等","较强烈");

        COMPLEX_MAP.put("简单","不发育");
        COMPLEX_MAP.put("简单","较简单、单一");
        COMPLEX_MAP.put("简单","简单、单一、良好");
        COMPLEX_MAP.put("简单","良好");
        COMPLEX_MAP.put("简单","一般");

        IMPORTANT_MAP.put("重要","开发区建设");
        IMPORTANT_MAP.put("重要","城镇新区建设");
        IMPORTANT_MAP.put("重要","放射性设施");
        IMPORTANT_MAP.put("重要","军事设施");
        IMPORTANT_MAP.put("重要","核电");
        IMPORTANT_MAP.put("重要","二级（含）以上公路");
        IMPORTANT_MAP.put("重要","铁路");
        IMPORTANT_MAP.put("重要","机场");
        IMPORTANT_MAP.put("重要","大型水利工程");
        IMPORTANT_MAP.put("重要","大型电力工程");
        IMPORTANT_MAP.put("重要","大型洪口码头");
        IMPORTANT_MAP.put("重要","大型集中供水水源地");
        IMPORTANT_MAP.put("重要","大型工业建筑");
        IMPORTANT_MAP.put("重要","大型民用建筑");
        IMPORTANT_MAP.put("重要","大型垃圾处理场");
        IMPORTANT_MAP.put("重要","大型水处理厂");

        IMPORTANT_MAP.put("较重要","新建村庄");
        IMPORTANT_MAP.put("较重要","三级（含）以下公路");
        IMPORTANT_MAP.put("较重要","中型水利工程");
        IMPORTANT_MAP.put("较重要","中型电力工程");
        IMPORTANT_MAP.put("较重要","中型洪口码头");
        IMPORTANT_MAP.put("较重要","中型集中供水水源地");
        IMPORTANT_MAP.put("较重要","中型工业建筑");
        IMPORTANT_MAP.put("较重要","中型民用建筑");
        IMPORTANT_MAP.put("较重要","中型垃圾处理场");
        IMPORTANT_MAP.put("较重要","中型水处理厂");
        IMPORTANT_MAP.put("较重要","中型矿山");

        IMPORTANT_MAP.put("一般","小型水利工程");
        IMPORTANT_MAP.put("一般","小型电力工程");
        IMPORTANT_MAP.put("一般","小型洪口码头");
        IMPORTANT_MAP.put("一般","小型集中供水水源地");
        IMPORTANT_MAP.put("一般","小型工业建筑");
        IMPORTANT_MAP.put("一般","小型民用建筑");
        IMPORTANT_MAP.put("一般","小型垃圾处理场");
        IMPORTANT_MAP.put("一般","小型水处理厂");
        IMPORTANT_MAP.put("一般","小型矿山");
    }

}
