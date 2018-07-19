package com.cykj.survey.util;

import com.cykj.survey.model.BasicOptions;
import com.cykj.survey.model.OptionsConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static void main(String[] args){
        String industry = "团体意外险,财产基本险";
        List<BasicOptions> options = OptionsConstants.woodenDangerList;
        List<BasicOptions> options1 = insuranceJudge(options,industry);

        System.out.print(options.size());
        System.out.print(options1.size());
    }

    /**
     * 根据所选保险判断
     * @param mData 数据源
     * @param insurance 当前选择的保险
     * @return 筛选后的数据
     */
    public static List<BasicOptions> insuranceJudge(List<BasicOptions> mData,String insurance){

        Map<String,BasicOptions> map = new HashMap<>();
        String[] temp = insurance.split(",");
        boolean isHave = false;
        for (String str : temp){
            for (BasicOptions basicOptions : mData){
                if (basicOptions.getInsurance().indexOf(str) != -1){
                    if (!map.containsKey(basicOptions.getName())){
                        map.put(basicOptions.getName(),basicOptions);
                    }
                }
            }
        }

        List<BasicOptions> data = new ArrayList<>();
        for (String key : map.keySet()){
            data.add(map.get(key));
        }

        return data;
    }

}
