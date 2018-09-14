package com.cykj.survey.model;

import java.util.ArrayList;
import java.util.List;

public class OptionsConstants {

    public static String INSURANCE;

    public static void setINSURANCE(String INSURANCE) {
        OptionsConstants.INSURANCE = INSURANCE;
    }

    //基础版-实木家具-生产-喷涂区
    public static List<BasicOptions> woodenSparayList;
    static {
        woodenSparayList = new ArrayList<>();
        woodenSparayList.add(new BasicOptions("1.漏电保护器、电气开关能否满足防爆要求",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenSparayList.add(new BasicOptions("2.电气线路是否采用镀锌管套管保护",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenSparayList.add(new BasicOptions("3.照明是否符合防爆要求",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenSparayList.add(new BasicOptions("4.通风模式是否采用上送下抽",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenSparayList.add(new BasicOptions("5.风机及电机是否防爆",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenSparayList.add(new BasicOptions("6.安全警示标志是否齐全",0,"团体意外险,雇主责任险"));
        woodenSparayList.add(new BasicOptions("7.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
    }

    //基础版-实木家具-生产-干燥区
    public static List<BasicOptions> woodenDryList;
    static {
        woodenDryList = new ArrayList<>();
        woodenDryList.add(new BasicOptions("1.风机电机是否防爆",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDryList.add(new BasicOptions("2.作业环境是否整洁清洁",0,"雇主责任险"));
        woodenDryList.add(new BasicOptions("3.烤灯灯管、防护罩是否完好",0,"财产综合险,财产一切险,财产基本险"));
        woodenDryList.add(new BasicOptions("4.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        woodenDryList.add(new BasicOptions("5.气动工具是否使用正常",0,"财产综合险,财产一切险,财产基本险"));
        woodenDryList.add(new BasicOptions("6.是否采用防静电措施\n",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDryList.add(new BasicOptions("7.漏电保护器、电气开关能否满足防爆要求",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDryList.add(new BasicOptions("8.电气线路是否采用镀锌管套管保护",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-生产-拼装
    public static List<BasicOptions> woodenCuttList;
    static {
        woodenCuttList = new ArrayList<>();
        woodenCuttList.add(new BasicOptions("1.设备线路是否完好,接线是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenCuttList.add(new BasicOptions("2.作业环境是否整洁清洁",0,"雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenCuttList.add(new BasicOptions("3.是否定点操作,不占用其他区域及通道",0,"团体意外险,雇主责任险"));
        woodenCuttList.add(new BasicOptions("4.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        woodenCuttList.add(new BasicOptions("5.作业环境送排风是否正常工作",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenCuttList.add(new BasicOptions("6.电控箱是否清洁整洁,接线是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenCuttList.add(new BasicOptions("7.电控箱进出线孔是否封堵",0,"财产综合险,财产一切险,财产基本险"));
        woodenCuttList.add(new BasicOptions("8.电气照明是否有防尘保护",0,"财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-生产-打磨
    public static List<BasicOptions> woodenGrindList;
    static {
        woodenGrindList = new ArrayList<>();
        woodenGrindList.add(new BasicOptions("1.风机是否开启且运转良好",0,"雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenGrindList.add(new BasicOptions("2.是否定点操作,不占用其他区域及通道",0,"团体意外险,雇主责任险"));
        woodenGrindList.add(new BasicOptions("3.作业环境是否整洁清洁",0,"雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenGrindList.add(new BasicOptions("4.电控箱是否清洁整洁,接线是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenGrindList.add(new BasicOptions("5.电控箱进出线孔是否封堵",0,"财产综合险,财产一切险,财产基本险"));
        woodenGrindList.add(new BasicOptions("6.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        woodenGrindList.add(new BasicOptions("7.电气照明是否有防尘保护",0,"财产综合险,财产一切险,财产基本险"));
        woodenGrindList.add(new BasicOptions("8.是否定期定时清洗作业区域",0,"财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-生产-除尘
    public static List<BasicOptions> woodenDustList;
    static {
        woodenDustList = new ArrayList<>();
        woodenDustList.add(new BasicOptions("1.是否定期清理",0,"财产综合险,财产一切险,财产基本险"));
        woodenDustList.add(new BasicOptions("2.产尘点是否装有吸尘罩",0,"雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDustList.add(new BasicOptions("3.设备转动部位是否有防护设施",0,"团体意外险,雇主责任险"));
        woodenDustList.add(new BasicOptions("4.电控柜是否清洁整洁,接线是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDustList.add(new BasicOptions("5.管线照明是否有防尘保护",0,"财产综合险,财产一切险,财产基本险"));
        woodenDustList.add(new BasicOptions("6.布袋清理时人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        woodenDustList.add(new BasicOptions("7.风机房是否无杂物堆放",0,"团体意外险,雇主责任险"));
        woodenDustList.add(new BasicOptions("8.是否在除尘器前面设有火星捕集器",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-库存-原料
    public static List<BasicOptions> woodenMaterialList;
    static {
        woodenMaterialList = new ArrayList<>();
        woodenMaterialList.add(new BasicOptions("1. 是否有自动灭火装置",0,"财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("2. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("3. 现场是否张贴安全警示标志",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("4. 库房是否执行定置定位管理",0,"团体意外险,雇主责任险"));
        woodenMaterialList.add(new BasicOptions("5. 配电箱柜是否被阻挡（半径1M范围内不得有杂物",0,"财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("6. 消防器材是否被阻挡（半径1M范围内不得有杂物）",0,"财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("7. 电气线路敷设是否符合要求",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("8. 是否分垛储存",0,"财产综合险,财产一切险,财产基本险"));
        woodenMaterialList.add(new BasicOptions("9.保管人员离库时,是否拉闸断电",0,"财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-库存-危化库
    public static List<BasicOptions> woodenDangerList;
    static {
        woodenDangerList = new ArrayList<>();
        woodenDangerList.add(new BasicOptions("1. 操作人员是否穿戴不产生静电的工作服、帽",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("2. 是否在进口设置了静电释放装置",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("3. 现场是否张贴安全警示标志",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("4. 是否有自动灭火装置",0,"财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("5. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("6. 是否设置有可燃气体检测报警装置",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("7. 风机电机是否防爆",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("8.照明、开关是否防爆",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("9.电气线路是否穿金属管",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenDangerList.add(new BasicOptions("10.是否执行双人双锁",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-库存-危废库
    public static List<BasicOptions> woodenWasteList;
    static {
        woodenWasteList = new ArrayList<>();
        woodenWasteList.add(new BasicOptions("1. 委外的危废回收机构是否具备相应资质与技术条件",0,"财产综合险,财产一切险,财产基本险"));
        woodenWasteList.add(new BasicOptions("2. 贮存、运输是否分类",0,"财产综合险,财产一切险,财产基本险"));
        woodenWasteList.add(new BasicOptions("3. 可燃危废品贮存区是否设置相应防爆措施",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenWasteList.add(new BasicOptions("4. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        woodenWasteList.add(new BasicOptions("5. 现场是否张贴安全警示标志",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenWasteList.add(new BasicOptions("6. 照明、开关是否防爆、电气线路是否穿金属管",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-库存-备件
    public static List<BasicOptions> woodenSpareList;
    static {
        woodenSpareList = new ArrayList<>();
        woodenSpareList.add(new BasicOptions("1. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        woodenSpareList.add(new BasicOptions("2. 电气线路敷设是否合规",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-办公区
    public static List<BasicOptions> woodenOfficeList;
    static {
        woodenOfficeList = new ArrayList<>();
        woodenOfficeList.add(new BasicOptions("1.多层办公楼是否具备至少2个应急出口",0,"团体意外险,雇主责任险"));
        woodenOfficeList.add(new BasicOptions("2.是否配置了足量且有效的消防器材",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenOfficeList.add(new BasicOptions("3.排水是否畅通",0,"财产综合险,财产一切险,财产基本险"));
        woodenOfficeList.add(new BasicOptions("4.相关应急疏散标志是否良好",0,"团体意外险,雇主责任险"));
        woodenOfficeList.add(new BasicOptions("5.是否备有应急物资",0,"财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-建、构筑物
    public static List<BasicOptions> woodenConstructionList;
    static {
        woodenConstructionList = new ArrayList<>();
        woodenConstructionList.add(new BasicOptions("1.建、构筑物是否有明显缺陷、裂痕",0,"财产综合险,财产一切险,财产基本险"));
        woodenConstructionList.add(new BasicOptions("2.厂房内搭建是否合规",0,"财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-生产环境
    public static List<BasicOptions> woodenSurroundingList;
    static {
        woodenSurroundingList = new ArrayList<>();
        woodenSurroundingList.add(new BasicOptions("1.周边道路排水是否良好",0,"财产综合险,财产一切险,财产基本险"));
        woodenSurroundingList.add(new BasicOptions("2.厂区外25M内是否有火灾爆炸风险",0,"财产综合险,财产一切险,财产基本险"));
        woodenSurroundingList.add(new BasicOptions("3.周边消防救援机构是否在5分钟内可以到达",0,"财产综合险,财产一切险,财产基本险"));
    }

    //基础版-实木家具-辅助
    public static List<BasicOptions> woodenAidList;
    static {
        woodenAidList = new ArrayList<>();
        woodenAidList.add(new BasicOptions("1.入厂是否设有限速标志及减速梗",0,"团体意外险,雇主责任险"));
        woodenAidList.add(new BasicOptions("2.厂区内是否已设置人车分流地线",0,"团体意外险,雇主责任险"));
        woodenAidList.add(new BasicOptions("3.厂区内是否设置避雷设施,各点位是否检测",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("4.厂区内通道是否满足消防车行驶,且有消防车调头区域",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("5.厂区内排水是否良好",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("6.厂区内机动车、非机动车管理是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("7.厂区内弱电系统是否良好",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("8.相关预案是否定期演练",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("9.各类操作人员是否持证",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("10.空压设备安全附件是否良好,运行环境是否良好",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("11.锅炉运行环境是否良好,电气设备、管线是否符合要求",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("12.配电室是否有火灾报警及自动灭火装置",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("13.配电室小动物防护措施是否良好",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("14.配电室通风是否良好",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("15.配电室相关绝缘措施及工器具是否齐全良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("16.备用电源是否按要求独立设置",0,"财产综合险,财产一切险,财产基本险"));
        woodenAidList.add(new BasicOptions("17.柴油贮存室电气是否符合要求",0,"财产综合险,财产一切险,财产基本险"));
    }

    //木材加工及人造板-生产-裁、切、干燥
    public static List<BasicOptions> timberDryList;
    static {
        timberDryList = new ArrayList<>();
        timberDryList.add(new BasicOptions("1.送排风装置是否完好且正常运行",0,"财产综合险,财产一切险,财产基本险"));
        timberDryList.add(new BasicOptions("2.电气线路是否穿管保护",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDryList.add(new BasicOptions("3.消防器材是否充足、完好",0,"财产综合险,财产一切险,财产基本险"));
        timberDryList.add(new BasicOptions("4.安全警示标志是否齐全",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDryList.add(new BasicOptions("5.设备接线是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberDryList.add(new BasicOptions("6.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        timberDryList.add(new BasicOptions("7.干燥后的木料或初始单板是否进行良好散热",0,"财产综合险,财产一切险,财产基本险"));
    }

    //    //木材加工及人造板-生产-制胶、涂胶、组坯
    public static List<BasicOptions> timberGlueList;
    static {
        timberGlueList = new ArrayList<>();//木材加工及人造板-生产-制胶、涂胶、组坯
        timberGlueList.add(new BasicOptions("1.设备线路是否完好,接线是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("2.作业环境是否整洁清洁",0,"财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("3.电气开关能否满足防爆要求",0,"财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("4.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("5.安全警示标志是否齐全",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("6.是否采用防静电措施",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("7.排风设备电机是否防爆",0,"财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("8.电控设施是否良好防爆",0,"财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("9.照明是否防爆",0,"财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("10.消防器材是否充足、完好",0,"财产综合险,财产一切险,财产基本险"));
        timberGlueList.add(new BasicOptions("11.是否设置洗眼、淋浴设施",0,"团体意外险,雇主责任险"));
    }

    //木材加工-生产-压合（冷、热）
    public static List<BasicOptions> timberLaminationList;
    static {
        timberLaminationList = new ArrayList<>();    //木材加工及人造板-生产-压合（冷、热）
        timberLaminationList.add(new BasicOptions("1.热压后是否摊开散热",0,"财产综合险,财产一切险,财产基本险"));
        timberLaminationList.add(new BasicOptions("2.风机是否开启且运转良好",0,"财产综合险,财产一切险,财产基本险"));
        timberLaminationList.add(new BasicOptions("3.压合后的板材是否定位管理,不占用其他区域及通道",0,"团体意外险,雇主责任险"));
        timberLaminationList.add(new BasicOptions("4.电控箱是否清洁整洁,接线是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberLaminationList.add(new BasicOptions("5.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        timberLaminationList.add(new BasicOptions("6.电气照明是否有防尘保护",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberLaminationList.add(new BasicOptions("7.是否定期定时清洗作业区域",0,"财产综合险,财产一切险,财产基本险"));
        timberLaminationList.add(new BasicOptions("8.消防器材是否充足、完好",0,"财产综合险,财产一切险,财产基本险"));

    }

    //木材加工-生产-修补、砂光、剪裁
    public static List<BasicOptions> timberTailoringList;
    static {
        timberTailoringList = new ArrayList<>();//木材加工及人造板-生产-修补、砂光、剪裁
        timberTailoringList.add(new BasicOptions("1.手持工具线路是否完好,接线是否良好",0,"团体意外险,雇主责任险"));
        timberTailoringList.add(new BasicOptions("2.刨花、锯末每班是否打扫干净,倒在指定地点",0,"财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("3.是否定点操作,不占用其他区域及通道",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("4.作业环境是否整洁清洁",0,"财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("5.电控箱是否清洁整洁,接线是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("6.电控箱进出线孔是否封堵",0,"财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("7.人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        timberTailoringList.add(new BasicOptions("8.电气照明是否有防尘保护",0,"财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("9.是否定期定时清洗作业区域",0,"财产综合险,财产一切险,财产基本险"));
        timberTailoringList.add(new BasicOptions("10.消防器材是否充足、完好",0,"财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-生产-除尘
    public static List<BasicOptions> timberDustremovalList;
    static {
        timberDustremovalList = new ArrayList<>();//木材加工及人造板-生产-除尘
        timberDustremovalList.add(new BasicOptions("1.是否定期清理",0,"财产综合险,财产一切险,财产基本险"));
        timberDustremovalList.add(new BasicOptions("2.产尘点是否装有吸尘罩",0,"雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDustremovalList.add(new BasicOptions("3.设备转动部位是否有防护设施",0,"团体意外险,雇主责任险"));
        timberDustremovalList.add(new BasicOptions("4.电控柜是否清洁整洁,接线是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDustremovalList.add(new BasicOptions("5.管线照明是否有防尘保护",0,"团体意外险,雇主责任险,财产综合险"));
        timberDustremovalList.add(new BasicOptions("6.布袋清理时人员防护穿戴是否正确有效",0,"团体意外险,雇主责任险"));
        timberDustremovalList.add(new BasicOptions("7.风机房是否无杂物堆放",0,"团体意外险,雇主责任险"));
        timberDustremovalList.add(new BasicOptions("8.是否在除尘器前面设有火星捕集器",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-库存-原料
    public static List<BasicOptions> timberMaterialList;
    static {
        timberMaterialList = new ArrayList<>();//木材加工及人造板   库存-成品、木料堆场
        timberMaterialList.add(new BasicOptions("1. 木料堆放是否稳定牢靠,是否成正梯型",0,"财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("2. 现场是否张贴安全警示标志",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("3. 木料堆场是否良好执行禁火管理",0,"团体意外险,雇主责任险"));
        timberMaterialList.add(new BasicOptions("4. 配电箱柜是否被阻挡（半径1M范围内不得有杂物）",0,"财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("5. 消防器材是否被阻挡（半径1M范围内不得有杂物）",0,"财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("6. 电气线路敷设是否符合要求",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("7. 是否分垛储存",0,"财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("8.库房外是否单独安装开关箱",0,"财产综合险,财产一切险,财产基本险"));
        timberMaterialList.add(new BasicOptions("9.保管人员离库时,是否拉闸断电",0,"财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-库存-危化品库、储罐
    public static List<BasicOptions> timberDangerList;
    static {
        timberDangerList = new ArrayList<>();//木材加工及人造板     库存-危化品库、储罐
        timberDangerList.add(new BasicOptions("1. 操作人员是否穿戴不产生静电的工作服、帽",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("2. 是否在进口设置了静电释放装置",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("3. 现场是否张贴安全警示标志",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("4. 是否有自动灭火装置",0,"财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("5. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("6. 是否设置了满足要求的围堰",0,"财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("7. 是否设置有可燃气体检测报警装置",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("8. 风机电机是否防爆",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("9.照明、开关是否防爆",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("10.电气线路是否穿金属管",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberDangerList.add(new BasicOptions("11.库房是否执行双人双锁",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-库存-危废库
    public static List<BasicOptions> timberWasteList;
    static {
        timberWasteList = new ArrayList<>();//          木材加工及人造板        库存-危废库
        timberWasteList.add(new BasicOptions("1. 委外的危废回收机构是否具备相应资质与技术条件",0,"财产综合险,财产一切险,财产基本险"));
        timberWasteList.add(new BasicOptions("2. 贮存、运输是否分类",0,"财产综合险,财产一切险,财产基本险"));
        timberWasteList.add(new BasicOptions("3. 可燃危废品贮存区是否设置相应防爆措施",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberWasteList.add(new BasicOptions("4. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        timberWasteList.add(new BasicOptions("5. 现场是否张贴安全警示标志",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberWasteList.add(new BasicOptions("6. 照明、开关是否防爆、电气线路是否穿金属管",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-库存-备件
    public static List<BasicOptions> timberSpareList;
    static {
        timberSpareList = new ArrayList<>();//木材加工及人造板  库存-备品备件库
        timberSpareList.add(new BasicOptions("1. 是否配置了足量且有效的消防器材",0,"财产综合险,财产一切险,财产基本险"));
        timberSpareList.add(new BasicOptions("2. 电气线路敷设是否合规",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-办公
    public static List<BasicOptions> timberOfficeList;
    static {
        timberOfficeList = new ArrayList<>();//木材加工及人造板-办公区
        timberOfficeList.add(new BasicOptions("1.多层办公楼是否具备至少2个应急出口",0,"团体意外险,雇主责任险"));
        timberOfficeList.add(new BasicOptions("2.办公用电是否符合防火管理",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberOfficeList.add(new BasicOptions("3.排水是否畅通",0,"财产综合险,财产一切险,财产基本险"));
        timberOfficeList.add(new BasicOptions("4.相关应急疏散标志是否良好",0,"团体意外险,雇主责任险"));
        timberOfficeList.add(new BasicOptions("5.是否备有应急物资",0,"财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-环境
    public static List<BasicOptions> timberSurroundingList;
    static {
        timberSurroundingList = new ArrayList<>();//木材加工及人造板 周边环境-生产环境
        timberSurroundingList.add(new BasicOptions("1.周边道路排水是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberSurroundingList.add(new BasicOptions("2.厂区外25M内是否有火灾爆炸风险",0,"财产综合险,财产一切险,财产基本险"));
        timberSurroundingList.add(new BasicOptions("3.周边消防救援机构是否在5分钟内可以到达",0,"财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-建构
    public static List<BasicOptions> timberConswtructionList;
    static {
        timberConswtructionList = new ArrayList<>();//建、构筑物 木材加工及人造板
        timberConswtructionList.add(new BasicOptions("1.建、构筑物是否有明显缺陷、裂痕",0,"财产综合险,财产一切险,财产基本险"));
        timberConswtructionList.add(new BasicOptions("2.厂房内搭建是否合规",0,"财产综合险,财产一切险,财产基本险"));
    }

    //木材加工-辅助
    public static List<BasicOptions> timberAidList;
    static {
        timberAidList = new ArrayList<>();//生产辅助 木材加工及人造板
        timberAidList.add(new BasicOptions("1.入厂是否设有限速标志及减速梗",0,"团体意外险,雇主责任险"));
        timberAidList.add(new BasicOptions("2.厂区内是否已设置人车分流地线",0,"团体意外险,雇主责任险"));
        timberAidList.add(new BasicOptions("3.厂区内是否设置避雷设施,各点位是否检测",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("4.厂区内通道是否满足消防车行驶,且有消防车调头区域",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("5.厂区内排水是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("6.厂区内机动车、非机动车管理是否良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("7.厂区内弱电系统是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("8.相关预案是否定期演练",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("9.各类操作人员是否持证",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("10.空压设备安全附件是否良好,运行环境是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("11.锅炉运行环境是否良好,电气设备、管线是否符合要求",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("12.配电室是否有火灾报警及自动灭火装置",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("13.配电室小动物防护措施是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("14.配电室通风是否良好",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("15.配电室相关绝缘措施及工器具是否齐全良好",0,"团体意外险,雇主责任险,财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("16.备用电源是否按要求独立设置",0,"财产综合险,财产一切险,财产基本险"));
        timberAidList.add(new BasicOptions("17.柴油贮存室电气是否符合要求",0,"财产综合险,财产一切险,财产基本险"));
    }
}
