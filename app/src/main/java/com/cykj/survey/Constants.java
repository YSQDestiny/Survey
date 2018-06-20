package com.cykj.survey;

import com.cykj.survey.model.Options;

import java.util.ArrayList;
import java.util.List;

public class Constants {

//    public final static String TEST_SERVICE = "http://destiny.s1.natapp.cc/CYKJ";

    public final static String TEST_SERVICE = "http://192.168.0.118:8080/CYKJ";

    public static Long REPORT_ID;

    public void setReportId(Long reportId){
        this.REPORT_ID = reportId;
    }

    public static List<Options> woodenSparayList;

    static {
        woodenSparayList = new ArrayList<>();
        woodenSparayList.add(new Options(R.string.spraying_options_1,R.mipmap.wooden_sparay_option_1));
        woodenSparayList.add(new Options(R.string.spraying_options_2,R.mipmap.wooden_sparay_option_2));
        woodenSparayList.add(new Options(R.string.spraying_options_3,R.mipmap.wooden_sparay_option_3));
        woodenSparayList.add(new Options(R.string.spraying_options_4,R.mipmap.wooden_sparay_option_4));
        woodenSparayList.add(new Options(R.string.spraying_options_5,0));
        woodenSparayList.add(new Options(R.string.spraying_options_6,R.mipmap.wooden_sparay_option_5));
        woodenSparayList.add(new Options(R.string.spraying_options_7,R.mipmap.wooden_sparay_option_6));
        woodenSparayList.add(new Options(R.string.spraying_options_8,0));
        woodenSparayList.add(new Options(R.string.spraying_options_9,R.mipmap.wooden_sparay_option_9));
        woodenSparayList.add(new Options(R.string.spraying_options_10,R.mipmap.wooden_sparay_option_10));
        woodenSparayList.add(new Options(R.string.spraying_options_11,0));
        woodenSparayList.add(new Options(R.string.spraying_options_12,0));
        woodenSparayList.add(new Options(R.string.spraying_options_13,0));
        woodenSparayList.add(new Options(R.string.spraying_options_14,0));
        woodenSparayList.add(new Options(R.string.spraying_options_15,0));
    }

    public static List<Options> woodenDryList;

    static {
        woodenDryList = new ArrayList<>();
        woodenDryList.add(new Options(R.string.dry_options_1,0));
        woodenDryList.add(new Options(R.string.dry_options_2,R.mipmap.wooden_dry_option_2));
        woodenDryList.add(new Options(R.string.dry_options_3,0));
        woodenDryList.add(new Options(R.string.dry_options_4,0));
        woodenDryList.add(new Options(R.string.dry_options_5,R.mipmap.wooden_dry_option_5));
        woodenDryList.add(new Options(R.string.dry_options_6,0));
        woodenDryList.add(new Options(R.string.dry_options_7,0));
        woodenDryList.add(new Options(R.string.dry_options_8,0));
        woodenDryList.add(new Options(R.string.dry_options_9,0));
        woodenDryList.add(new Options(R.string.dry_options_10,0));
        woodenDryList.add(new Options(R.string.dry_options_11,0));
        woodenDryList.add(new Options(R.string.dry_options_12,R.mipmap.wooden_dry_option_12));
        woodenDryList.add(new Options(R.string.dry_options_13,R.mipmap.wooden_dry_option_13));
    }

    public static List<Options> woodenCuttList;

    static {
        woodenCuttList = new ArrayList<>();
        woodenCuttList.add(new Options(R.string.cutting_options_1,0));
        woodenCuttList.add(new Options(R.string.cutting_options_2,0));
        woodenCuttList.add(new Options(R.string.cutting_options_3,0));
        woodenCuttList.add(new Options(R.string.cutting_options_4,0));
        woodenCuttList.add(new Options(R.string.cutting_options_5,0));
        woodenCuttList.add(new Options(R.string.cutting_options_6,0));
        woodenCuttList.add(new Options(R.string.cutting_options_7,0));
        woodenCuttList.add(new Options(R.string.cutting_options_8,0));
        woodenCuttList.add(new Options(R.string.cutting_options_9,0));
        woodenCuttList.add(new Options(R.string.cutting_options_10,0));
        woodenCuttList.add(new Options(R.string.cutting_options_11,0));
        woodenCuttList.add(new Options(R.string.cutting_options_12,0));
        woodenCuttList.add(new Options(R.string.cutting_options_13,R.mipmap.wooden_cutt_option_12));
    }

    public static List<Options> woodenGrindList;
    static {
        woodenGrindList = new ArrayList<>();
        woodenGrindList.add(new Options(R.string.grinding_options_1,0));
        woodenGrindList.add(new Options(R.string.grinding_options_2,0));
        woodenGrindList.add(new Options(R.string.grinding_options_3,0));
        woodenGrindList.add(new Options(R.string.grinding_options_4,0));
        woodenGrindList.add(new Options(R.string.grinding_options_5,0));
        woodenGrindList.add(new Options(R.string.grinding_options_6,0));
        woodenGrindList.add(new Options(R.string.grinding_options_7,0));
        woodenGrindList.add(new Options(R.string.grinding_options_8,0));
        woodenGrindList.add(new Options(R.string.grinding_options_9,R.mipmap.wooden_grind_options_9));
        woodenGrindList.add(new Options(R.string.grinding_options_10,0));
    }

    public static List<Options> woodenDustList;
    static {
        woodenDustList = new ArrayList<>();
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_1,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_2,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_3,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_4,R.mipmap.wooden_dust_option_4));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_5,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_6,R.mipmap.wooden_dust_option_6));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_7,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_8,R.mipmap.wooden_dust_option_8));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_9,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_10,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_11,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_12,0));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_13,R.mipmap.wooden_dust_option_13));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_14,R.mipmap.wooden_dust_option_14));
        woodenDustList.add(new Options(R.string.dustRemoval_optinos_15,0));
    }

    public static List<Options> woodenMaterialList;
    static {
        woodenMaterialList = new ArrayList<>();
        woodenMaterialList.add(new Options(R.string.material_options_1,R.mipmap.wooden_material_options_1));
        woodenMaterialList.add(new Options(R.string.material_options_2,0));
        woodenMaterialList.add(new Options(R.string.material_options_3,0));
        woodenMaterialList.add(new Options(R.string.material_options_4,0));
        woodenMaterialList.add(new Options(R.string.material_options_5,0));
        woodenMaterialList.add(new Options(R.string.material_options_6,0));
        woodenMaterialList.add(new Options(R.string.material_options_7,0));
        woodenMaterialList.add(new Options(R.string.material_options_8,0));
        woodenMaterialList.add(new Options(R.string.material_options_9,0));
        woodenMaterialList.add(new Options(R.string.material_options_10,0));
        woodenMaterialList.add(new Options(R.string.material_options_11,0));
        woodenMaterialList.add(new Options(R.string.material_options_12,0));
        woodenMaterialList.add(new Options(R.string.material_options_13,0));
        woodenMaterialList.add(new Options(R.string.material_options_14,0));
    }

    public static List<Options> woodenDangerList;
    static {
        woodenDangerList = new ArrayList<>();
        woodenDangerList.add(new Options(R.string.danger_options_1,0));
        woodenDangerList.add(new Options(R.string.danger_options_2,R.mipmap.wooden_danger_options_2));
        woodenDangerList.add(new Options(R.string.danger_options_3,0));
        woodenDangerList.add(new Options(R.string.danger_options_4,R.mipmap.wooden_danger_options_4));
        woodenDangerList.add(new Options(R.string.danger_options_5,0));
        woodenDangerList.add(new Options(R.string.danger_options_6,R.mipmap.wooden_danger_options_6));
        woodenDangerList.add(new Options(R.string.danger_options_7,0));
        woodenDangerList.add(new Options(R.string.danger_options_8,0));
        woodenDangerList.add(new Options(R.string.danger_options_9,0));
        woodenDangerList.add(new Options(R.string.danger_options_10,0));
        woodenDangerList.add(new Options(R.string.danger_options_11,0));
        woodenDangerList.add(new Options(R.string.danger_options_12,0));
        woodenDangerList.add(new Options(R.string.danger_options_13,0));
        woodenDangerList.add(new Options(R.string.danger_options_14,0));
        woodenDangerList.add(new Options(R.string.danger_options_15,0));
    }

}
