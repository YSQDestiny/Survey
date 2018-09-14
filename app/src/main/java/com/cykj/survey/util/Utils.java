package com.cykj.survey.util;

import android.util.Log;

import com.cykj.survey.model.BasicOptions;
import com.cykj.survey.model.Options;
import com.cykj.survey.model.OptionsConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static void main(String[] args){
        String str = "\n" +
                "    $(function () {\n" +
                "    \t$('#nav-05').trigger('mouseenter');\n" +
                "    \tsunriseset(103.52, 30.6);\n" +
                "    \tinitReal('56285');\n" +
                "    \tinitAqi('56285');\n" +
                "    \tdrawTemperature('56285');\n" +
                "    \tchangeTab();\n" +
                "    \tclimate([9.25,11.19,16.16,21.56,25.61,27.55,29.5,29.4,25.23,20.42,15.68,10.66],[2.67,4.52,8.18,12.72,17.06,20.1,21.99,21.46,18.64,14.65,9.75,4.53],[11.26,16.99,31.01,58.17,89.02,115.62,258.72,262.52,146.97,61.22,23.78,9.55],'大邑');\n" +
                "    });\n" +
                "\n" +
                "    ";




                String substring = str.substring(str.indexOf("climate("), str.indexOf("});"));
                System.out.println(substring);

    }

    /**
     * 根据所选保险判断
     * @param mData 数据源
     * @param insurance 当前选择的保险
     * @return 筛选后的数据
     */
    public static List<Options> insuranceJudge(List<Options> mData, String insurance){

        Map<String,Options> map = new HashMap<>();
        String[] temp = insurance.split(",");
        boolean isHave = false;
        for (String str : temp){
            for (Options options : mData){
                if (options.getInsuranceType().indexOf(str) != -1){
                    if (!map.containsKey(options.getName())){
                        map.put(options.getName(),options);
                    }
                }
            }
        }

        List<Options> data = new ArrayList<>();
        for (String key : map.keySet()){
            data.add(map.get(key));
        }

        return data;
    }

}
