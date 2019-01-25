package com.cykj.survey.interactor;

import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.bean.DisasterBean;
import com.cykj.survey.bean.IDBean;
import com.cykj.survey.bean.ResultBean;
import com.cykj.survey.bean.StringBean;



import rx.Subscription;

public interface ResultInteractor {

    //获取工程项目列表
    Subscription getProjectList(String uniqueId, BaseSubsribe<ResultBean> subsribe);

    //创建项目
    Subscription createProject(String name,String uniqueId, BaseSubsribe<IDBean> subsribe);

    //保存项目基础信息
    Subscription saveProject(Long id, String json, BaseSubsribe<StringBean> subsribe);

    //更新项目信息
    Subscription updateProject(Long id,String target,String json,BaseSubsribe<StringBean> subsribe);

    //获取地灾隐患点
    Subscription searchYHD(String json, BaseSubsribe<DisasterBean> subsribe);

    //提交现场风险
    Subscription postAccident(String json,BaseSubsribe<StringBean> subsribe);

    /*********************************************************物业***********************************************/
    //创建物业项目
    Subscription postProperty(String josn,BaseSubsribe<IDBean> subsribe);
}
