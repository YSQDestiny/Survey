package com.cykj.survey.ui.activity.property;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.bean.IndustryBean;
import com.cykj.survey.bean.Node;
import com.cykj.survey.bean.SimpleTreeAdapter;
import com.cykj.survey.bean.TreeListViewAdapter;
import com.cykj.survey.ui.fragment.adapter.PropertyAreaAdapter;
import com.cykj.survey.model.DeductionModel;
import com.cykj.survey.model.Industry;
import com.cykj.survey.model.Property;
import com.cykj.survey.model.PropertyArea;
import com.cykj.survey.model.PropertyModel;
import com.cykj.survey.model.PropertyOption;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.model.UploadModel;
import com.cykj.survey.util.DeviceUtils;
import com.cykj.survey.util.JsonUtil;
import com.cykj.survey.util.LocalDataCache;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PropertyAreaActivity extends BaseFragmentActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.industry_list)
    ListView industryList;


    private Handler handler;

    private List<PropertyArea> areaList = new ArrayList<>();

    private PropertyAreaAdapter areaAdapter;

    private List<IndustryBean> mDatas;
    private TreeListViewAdapter mAdapter;

    private String area = "";

    private Property property;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_area);
        ButterKnife.bind(this);
        handler = new Handler();
        Intent intent = getIntent();
        String json = intent.getStringExtra("property");
        property = JSONObject.parseObject(json,Property.class);
        initData();
        initTopbar();

        //树形结构list
        try {
            mAdapter = new SimpleTreeAdapter<IndustryBean>(industryList,this,mDatas,0);
            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()){
                        Node parent = node.getParent();
                        if (parent != null){
                            area += parent.getName()+"-";
                            area += node.getName();
                        }else {
                            area += node.getName();
                        }
                        showMessagePositiveDialog(area);
                        area = "";
                    }
                }
            });
            industryList.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 已查勘区域提示框
     */
    private List<String> stringList = new ArrayList<>();
    private void showTipdialog(){
        String tipStr = "";
        for (String str : stringList){
            tipStr += str +"\n";
        }
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("已查勘区域")
                .setMessage(tipStr)
                .addAction("知道了", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     *
     */
    private void showTipdia(){
        int i = 0;
    }

    /**
     * 完成查勘提示框
     */
    private void showFinishTip(){
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("确认完成此次查勘吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        finish();
                        Object object = LocalDataCache.getLoaclData(PropertyAreaActivity.this,DeviceUtils.getUniqueId(PropertyAreaActivity.this) + "property");
                        List<Property> properties = (List<Property>) object;
                        properties.add(property);
                        LocalDataCache.save(PropertyAreaActivity.this,properties,DeviceUtils.getUniqueId(PropertyAreaActivity.this) + "property");

                        PropertyModel propertyModel = new PropertyModel();
                        propertyModel.setProperty(property);
                        if (Constants.propertyAccidentList.size() > 0){
                            propertyModel.setAccidentList(Constants.propertyAccidentList);
                        }
                        UploadModel uploadModel = new UploadModel();
                        uploadModel.setTarget("property");
                        uploadModel.setJson(JSONObject.toJSONString(propertyModel));
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 开始查勘信息框
     * @param areastr
     */
    private void showMessagePositiveDialog(final String areastr){
        if (stringList != null & stringList.size() > 0){
            if (!stringList.contains(areastr)){
                stringList.add(areastr);
            }
        }else {
            stringList.add(areastr);
        }
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("开始查勘")
                .setMessage("确定要开始查勘吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        PropertyArea temp = null;
                        for (PropertyArea area1 : areaList){
                            if (area1.getName().equals(areastr)){
                                temp = area1;
                            }
                        }
                        String str = JSONObject.toJSONString(temp);
                        Intent intent = new Intent(PropertyAreaActivity.this,PropertyOptionActivity.class);
                        intent.putExtra("area",str);
                        startActivity(intent);
                    }
                }).create(mCurrentDialogStyle).show();
    }

    /**
     * 数据初始化
     */
    private void initData() {
        Constants.cleanPropertyAccidentList();
        Constants.cleanDeductionJson();
        String jsonStr = JsonUtil.getJson("property.json",this);
        Industry industry = JSONObject.parseObject(jsonStr,Industry.class);
        mDatas = industry.getBean();
        getArea();
    }

    /**
     * 标题栏初始化
     */
    private void initTopbar() {
        topbar.setTitle("区域选择");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFinishTip();//oojiprivate int yangshiqi
            }
        });
        topbar.addRightTextButton("查勘记录",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipdialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        property.setDeduction(Constants.DEDUCTION_JSON);
    }


    Runnable Tipable = new Runnable() {
        @Override
        public void run() {
            showToastShort("保存成功");
            showTipdialog();
        }
    };

    private List<DeductionModel> deductionModelList = new ArrayList<>();

    /**
     * 获取区域信息
     */
    private void getArea(){

        Object object2 = LocalDataCache.getLoaclData(this,"propertyArea");

        if (object2 == null){
            String url = Constants.TEST_SERVICE + "/property/getAreaAndOptions";
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                    if (result.getCode() == 0){
                        Map<PropertyArea,List<PropertyOption>> optionsMap = JSONObject.parseObject(result.getData(),new TypeReference<Map<PropertyArea, List<PropertyOption>>>(){});
                        if (optionsMap != null){
                            LocalDataCache.save(PropertyAreaActivity.this,optionsMap,"propertyArea");
                            for (PropertyArea area : optionsMap.keySet()){
                                areaList.add(area);
                            }
                            for (PropertyArea area : areaList){
                                DeductionModel deductionModel = new DeductionModel();
                                deductionModel.setArea(area.getName());
                                deductionModel.setMissing(false);
                                deductionModel.setDeduction(0);
                                deductionModel.setImportant(area.getImportant());
                                deductionModel.setTotal(area.getSinglePoint());
                                deductionModelList.add(deductionModel);
                            }
                            Constants.setDeductionJson(JSONObject.toJSONString(deductionModelList));
                        }
                    }
                }
            });
        }else {
            Map<PropertyArea,List<PropertyOption>> optionsMap = (Map<PropertyArea, List<PropertyOption>>) object2;
            Iterator it = optionsMap.keySet().iterator();
            while (it.hasNext()){
                areaList.add((PropertyArea) it.next());
            }
            for (PropertyArea area : areaList){
                DeductionModel deductionModel = new DeductionModel();
                deductionModel.setArea(area.getName());
                deductionModel.setMissing(false);
                deductionModel.setDeduction(0);
                deductionModel.setImportant(area.getImportant());
                deductionModel.setTotal(area.getSinglePoint());
                deductionModelList.add(deductionModel);
            }
            Constants.setDeductionJson(JSONObject.toJSONString(deductionModelList));
        }
    }

}
