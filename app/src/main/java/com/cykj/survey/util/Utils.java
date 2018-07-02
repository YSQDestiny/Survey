package com.cykj.survey.util;

import com.cykj.survey.model.BasicOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    /**
     * 根据所选保险判断
     * @param mData 数据源
     * @param insurance 当前选择的保险
     * @return 筛选后的数据
     */
    public static List<BasicOptions> insuranceJudge(List<BasicOptions> mData,String insurance){

        Map<String,BasicOptions> map = new HashMap<>();
        List<Integer> removeList = new ArrayList<>();

        for (int j = 0;j < mData.size();j++){
            map.put(mData.get(j).getName(),mData.get(j));
            if (insurance.length() > mData.get(j).getInsurance().length()){
                if (insurance.indexOf(mData.get(j).getInsurance()) == -1){
                    map.remove(mData.get(j).getName());
                }
            }else if (insurance.length() == mData.get(j).getInsurance().length()){
                if (!insurance.equals(mData.get(j).getInsurance())){
                    map.remove(mData.get(j).getName());
                }
            }else {
                if (mData.get(j).getInsurance().indexOf(insurance) == -1){
                    map.remove(mData.get(j).getName());
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
