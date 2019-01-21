package com.cykj.survey.ui.activity.property;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.ui.activity.AccidentActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.ui.fragment.adapter.PropertyOptionListAdapter;
import com.cykj.survey.model.DeductionModel;
import com.cykj.survey.model.PropertyArea;
import com.cykj.survey.model.PropertyOption;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.view.NoScrollListview;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PropertyOptionActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.property_option_spinner)
    Spinner propertyOptionSpinner;
    @BindView(R.id.property_option_missingitem)
    TextView propertyOptionMissingitem;
    @BindView(R.id.property_option_reson)
    TextView propertyOptionReson;
    @BindView(R.id.property_option_total_points)
    TextView propertyOptionTotalPoints;
    @BindView(R.id.property_option_current)
    TextView propertyOptionCurrent;
    @BindView(R.id.property_option_list)
    NoScrollListview propertyOptionList;
    @BindView(R.id.property_option_standard)
    TextView propertyOptionStandard;

    private Handler handler;

    private List<PropertyOption> optionList;

    private PropertyOptionListAdapter adapter;

    private PropertyArea area;

    private List<String> resonList = new ArrayList<>();

    private DeductionModel deductionModel;

    private int deduction;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_option);
        ButterKnife.bind(this);
        handler = new Handler();
        Intent intent = getIntent();
        String str = intent.getStringExtra("area");
        area = JSONObject.parseObject(str, PropertyArea.class);
        initData();
        initTopbar();
        getPropertyOption(area.getName());
        setPoint(area.getSinglePoint());
        if (area.getImportant() == 1) {
            showMessagePositiveDialog();
        }

        propertyOptionMissingitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMissingPointDialog();
            }
        });
        propertyOptionReson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResonDialog();
            }
        });
    }

    /**
     * 初始化标题栏
     */
    private void initTopbar() {
        topbar.setTitle("评定内容");
        topbar.addRightTextButton("拍照", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertyOptionActivity.this, AccidentActivity.class);
                intent.putExtra("target", "property");
                startActivity(intent);
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFinshDialog();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String json = Constants.DEDUCTION_JSON;
        List<DeductionModel> deductionModels = JSONObject.parseArray(json, DeductionModel.class);
        for (DeductionModel model : deductionModels) {
            if (model.getArea().equals(area.getName())) {
                deductionModel = model;
            }
        }
        if (deductionModel.getDeduction() != 0) {
            deduction = deductionModel.getDeduction();
            propertyOptionCurrent.setText(Integer.toString((area.getSinglePoint() - deductionModel.getDeduction())));
            showTipDialog(deductionModel);
        } else {
            deduction = 0;
            propertyOptionCurrent.setText(Integer.toString(area.getSinglePoint()));
        }
        isMissing = deductionModel.isMissing();
        if (deductionModel.getReson() != null) {
            resonList = deductionModel.getReson();
        }
    }

    /**
     * 初始化view
     */
    private void initView() {
        propertyOptionStandard.setText(area.getStandard());
        if (optionList != null) {
            adapter = new PropertyOptionListAdapter(this, optionList);
            propertyOptionList.setAdapter(adapter);
        }
    }

    /**
     * 获取详细查勘内容
     *
     * @param area
     */
    private void getPropertyOption(String area) {
        String url = Constants.TEST_SERVICE + "/property/getOption";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("area", area)
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                optionList = JSONObject.parseArray(result.getData(), PropertyOption.class);
                handler.post(UIable);
            }
        });
    }


    /**
     * 毫无意义
     */
    private void zhuangYangzi(){

    }

    /**
     * UI线程
     */
    Runnable UIable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    /**
     * 设置分数
     *
     * @param point
     */
    private void setPoint(int point) {
        propertyOptionTotalPoints.setText(Integer.toString(area.getSinglePoint()));
        List<Integer> pointData = new ArrayList<>();
        for (int i = 0; i <= point; i++) {
            pointData.add(i);
        }
        ArrayAdapter<Integer> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pointData);
        spinnerSetAdapter(adapter, propertyOptionSpinner);
    }

    private void spinnerSetAdapter(ArrayAdapter<Integer> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /**
     * 追加扣分项提示
     */
    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("本项为追加扣分项")
                .addAction("知道了", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();

    }

    /**
     * 查勘提示
     *
     * @param model
     */
    private void showTipDialog(DeductionModel model) {
        String str = "";
        str += "扣分：" + model.getDeduction() + "\n";
        if (model.isMissing()) {
            str += "是否缺项：是" + "\n" + "原因：" + "\n";
        } else {
            str += "是否缺项：否" + "\n" + "原因：" + "\n";
        }
        if (model.getReson() != null) {
            for (String resonStr : model.getReson()) {
                str += resonStr + "\n";
            }
        }
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("之前评定结果")
                .setMessage(str)
                .addAction("知道了", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();

    }

    /**
     * 完成评定提示
     */
    private void showFinshDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("确定完成当前项的评定吗？")
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
                        setDeduction();
                        finish();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 设置扣分信息
     */
    private void setDeduction() {
        List<DeductionModel> deductionModels = JSONObject.parseArray(Constants.DEDUCTION_JSON, DeductionModel.class);
        for (DeductionModel model : deductionModels) {
            if (model.getArea().equals(area.getName())) {
                if (isMissing) {
                    model.setDeduction(area.getSinglePoint());
                } else {
                    model.setDeduction(Integer.parseInt(propertyOptionSpinner.getSelectedItem().toString()) + deduction);
                }
                model.setMissing(isMissing);
                if (resonList != null & resonList.size() > 0) {
                    model.setReson(resonList);
                }
            }
        }
        Constants.setDeductionJson(JSONObject.toJSONString(deductionModels));
    }

    /**
     * 缺项分输入框
     */
    private boolean isMissing = false;

    private void showMissingPointDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("确定完成当前评定项目为缺项吗？")
                .addAction("否", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        isMissing = false;
                    }
                })
                .addAction("是", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        isMissing = true;
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 扣分原因输入框
     */
    private void showResonDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("添加扣分原因")
                .setPlaceholder("在此输入扣分原因")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null || text.length() > 0) {
                            resonList.add(text.toString() + " -" + propertyOptionSpinner.getSelectedItem().toString() + "分");
                            dialog.dismiss();
                        } else {
                            showToastShort("请填入扣分原因");
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 提交扣分信息
     *
     * @param json
     */
    private void postDeduction(String json) {
        String url = Constants.TEST_SERVICE + "/property/postDeduction";
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("id", Constants.PROPERTY_ID.toString())
                .add("json", json)
                .build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                if (result.getCode() == 0) {
                    finish();
                }
            }
        });

    }
}
