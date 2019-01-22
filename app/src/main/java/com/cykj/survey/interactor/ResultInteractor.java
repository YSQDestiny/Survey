package com.cykj.survey.interactor;

import com.cykj.survey.base.BaseSubsribe;
import com.cykj.survey.bean.IDBean;
import com.cykj.survey.bean.ResultBean;
import com.cykj.survey.bean.StringBean;



import rx.Subscription;

public interface ResultInteractor {

    Subscription getProjectList(String uniqueId, BaseSubsribe<ResultBean> subsribe);

    Subscription createProject(String name,String uniqueId, BaseSubsribe<IDBean> subsribe);

    Subscription saveProject(Long id, String json, BaseSubsribe<StringBean> subsribe);

    Subscription updateProject(Long id,String target,String json,BaseSubsribe<StringBean> subsribe);

}
