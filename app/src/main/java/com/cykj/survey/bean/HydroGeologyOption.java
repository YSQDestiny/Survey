package com.cykj.survey.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HydroGeologyOption {

    public static List<String> mounthList = new ArrayList<>();

    public static Map<String, String> data1_1 = new HashMap<>();
    public static List<String> data1_5 = new ArrayList<>();
    public static List<String> data1_6 = new ArrayList<>();
    public static List<String> data1_7 = new ArrayList<>();
    public static List<String> data1_9 = new ArrayList<>();
    public static List<String> data1_10 = new ArrayList<>();
    public static List<String> data1_11 = new ArrayList<>();
    public static List<String> data1_12 = new ArrayList<>();

    public static List<String> data2_1 = new ArrayList<>();
    public static List<String> data2_2 = new ArrayList<>();
    public static List<String> data2_3 = new ArrayList<>();
    public static List<String> data2_4 = new ArrayList<>();
    public static List<String> data2_6 = new ArrayList<>();
    public static List<String> data2_7 = new ArrayList<>();

    public static List<String> data3_3 = new ArrayList<>();
    public static List<String> data3_4 = new ArrayList<>();
    public static List<String> data3_5 = new ArrayList<>();
    public static List<String> data3_6 = new ArrayList<>();


    public static List<String> data4_3 = new ArrayList<>();
    public static List<String> data4_4 = new ArrayList<>();
    public static List<String> data4_5 = new ArrayList<>();
    public static List<String> data4_6 = new ArrayList<>();
    public static List<String> data4_8 = new ArrayList<>();
    public static List<String> data4_9 = new ArrayList<>();

    public static List<String> data5_1 = new ArrayList<>();
    public static List<String> data5_2 = new ArrayList<>();
    public static List<String> data5_3 = new ArrayList<>();
    public static List<String> data5_4 = new ArrayList<>();
    public static List<String> data5_5 = new ArrayList<>();
    public static List<String> data5_6 = new ArrayList<>();
    public static List<String> data5_7 = new ArrayList<>();
    public static List<String> data5_8 = new ArrayList<>();
    public static List<String> data5_9 = new ArrayList<>();
    public static List<String> data5_10 = new ArrayList<>();
    public static List<String> data5_11 = new ArrayList<>();
    public static List<String> data5_12 = new ArrayList<>();
    public static List<String> data5_13 = new ArrayList<>();
    public static List<String> data5_14 = new ArrayList<>();
    public static List<String> data5_15 = new ArrayList<>();

    static {
        mounthList.add("1月");
        mounthList.add("2月");
        mounthList.add("3月");
        mounthList.add("4月");
        mounthList.add("5月");
        mounthList.add("6月");
        mounthList.add("7月");
        mounthList.add("8月");
        mounthList.add("9月");
        mounthList.add("10月");
        mounthList.add("11月");
        mounthList.add("12月");

        data1_1.put("热带季风", "终年高温，存在明显的雨季、旱季，降水集中在雨季，且降雨量大，季风气候显著，多热带气旋");
        data1_1.put("亚热带季风", "夏季高温多雨，冬季温和少雨，四季分明，降水丰沛，冬夏干湿差别不大");
        data1_1.put("温带季风", "夏季高温多雨，冬季寒冷干燥，季风性显著，夏秋季节易受热带气旋影响");
        data1_1.put("高原山地", "日照时间长，太阳辐射强，空气稀薄，干燥少云，早晚寒凉晌午热，整体气温较低，局部多大风");
        data1_1.put("温带大陆性", "冬季寒冷干燥，夏季炎热湿润，气温年较差、日较差均大，降雨地区差异明显");
        data1_1.put("热带雨林", "全年高温多雨，太阳辐射年变化较小，局部地区多热带气旋");
        data1_5.add("存在");
        data1_5.add("不存在");
        data1_6.add("明显");
        data1_6.add("不明显");
        data1_7.add("较好");
        data1_7.add("一般");
        data1_7.add("较差");
        data1_9.add("较多");
        data1_9.add("较少");
        data1_10.add("较大");
        data1_10.add("一般");
        data1_10.add("较小");
        data1_10.add("无");
        data1_11.add("畅通");
        data1_11.add("堵塞");
        data1_12.add("完备（设排水泵与应急排水泵，其排水流量大于厂区暴雨积水流量）");
        data1_12.add("不完备");

        data2_1.add("较大（≥0.1亿m³）");
        data2_1.add("较小（＜0.1亿m³）");
        data2_2.add("较好");
        data2_2.add("一般");
        data2_2.add("较差");
        data2_3.add("降雨");
        data2_3.add("地下水");
        data2_3.add("冰雪融水");
        data2_3.add("其他");
        data2_4.add("均匀");
        data2_4.add("不均匀");
        data2_6.add("存在");
        data2_6.add("不存在");
        data2_7.add("可以");
        data2_7.add("不可以");

        data3_3.add("容易（冬季雨雪、冰冻天数每年＞15天）");
        data3_3.add("不容易");
        data3_4.add("有");
        data3_4.add("无");
        data3_5.add("低度（0＜覆冰厚度≤10mm）");
        data3_5.add("中度（10mm＜覆冰厚度≤20mm）");
        data3_5.add("高度（覆冰厚度＞20mm）");
        data3_6.add("存在");
        data3_6.add("不存在");

        data4_3.add("少雷区");
        data4_3.add("多雷区");
        data4_3.add("高雷区");
        data4_3.add("强雷区");
        data4_3.add("无雷区");
        data4_4.add("春季");
        data4_4.add("夏季");
        data4_4.add("秋季");
        data4_4.add("冬季");
        data4_5.add("偏低");
        data4_5.add("正常");
        data4_6.add("偏多");
        data4_6.add("偏少");
        data4_6.add("差不多");
        data4_8.add("正常");
        data4_8.add("存在损坏");
        data4_9.add("正常（≤4Ω）");
        data4_9.add("偏高（≤10Ω）");
        data4_9.add("高（＞10Ω）");

        data5_1.add("砂岩");
        data5_1.add("砾岩");
        data5_1.add("页岩");
        data5_1.add("碳酸盐岩");
        data5_1.add("变质岩");
        data5_1.add("岩浆岩");
        data5_1.add("碎屑堆积物");
        data5_2.add("复杂（地形坡角≥30°，自然坡高≥15m）");
        data5_2.add("中等（地形坡角30°～15°，自然坡高15～8m）");
        data5_2.add("简单（地形坡角＜15°，自然坡高＜8m）");
        data5_3.add("复杂（土层厚度≥10m，岩层厚度薄层，岩土多元组合）");
        data5_3.add("中等（土层厚度10～3m，岩层厚度中～厚层，岩土二元组合）");
        data5_3.add("简单（土层厚度＜3m，岩层厚度厚层，岩性单一）");
        data5_4.add("复杂（断裂带发育，裂隙间距＜0.3m，地震基本烈度Ⅶ）");
        data5_4.add("中等（断裂带一般发育，裂隙间距1～0.3m，地震基本烈度Ⅵ）");
        data5_4.add("简单（断裂带不发育，裂隙间距＞1m，地震基本烈度Ⅴ）");
        data5_5.add("复杂（地表水对岩土体影响大，地下水对岩土体影响大） ");
        data5_5.add("中等（地表水对岩土体影响中等，地下水对岩土体影响中等）");
        data5_5.add("简单（地表水对岩土体影响小，地下水对岩土体影响小）");
        data5_6.add("滑坡");
        data5_6.add("崩塌危岩");
        data5_6.add("泥石流");
        data5_6.add("不稳定斜坡");
        data5_6.add("地面塌陷");
        data5_6.add("地裂缝");
        data5_6.add("冻土");
        data5_6.add("无");
        data5_7.add("影响较大");
        data5_7.add("影响较小");
        data5_7.add("不会影响");
        data5_8.add("≥30%");
        data5_8.add("30%～15%");
        data5_8.add("＜15%");
        data5_9.add("不存在");
        data5_9.add("存在");
        data5_10.add("较大");
        data5_10.add("一般");
        data5_10.add("较小");
        data5_10.add("无");
        data5_11.add("稳定");
        data5_11.add("较稳定");
        data5_11.add("不稳定");
        data5_12.add("存在");
        data5_12.add("不存在");
        data5_13.add("有");
        data5_13.add("无");
        data5_14.add("发生过");
        data5_14.add("未发生过");
        data5_15.add("复杂");
        data5_15.add("中等");
        data5_15.add("简单");
    }

}
