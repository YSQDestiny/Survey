package com.cykj.survey.activity.property;

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
import com.cykj.survey.activity.AccidentActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.PropertyOptionListAdapter;
import com.cykj.survey.model.Deduction;
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
    @BindView(R.id.property_option_list)
    NoScrollListview propertyOptionList;


    private Handler handler;

    private List<PropertyOption> optionList;

    private PropertyOptionListAdapter adapter;

    private PropertyArea area;

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
        area = JSONObject.parseObject(str,PropertyArea.class);
        initTopbar();
        getPropertyOption(area.getName());
        setPoint(area.getSinglePoint());
        if (area.getImportant() == 1){
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

    private void initTopbar() {
        topbar.setTitle("评定内容");
        topbar.addRightTextButton("拍照",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertyOptionActivity.this, AccidentActivity.class);
                intent.putExtra("target","property");
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

    private void initView() {
        if (optionList != null) {
            adapter = new PropertyOptionListAdapter(this, optionList);
            propertyOptionList.setAdapter(adapter);
        }
    }

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

    Runnable UIable = new Runnable() {
        @Override
        public void run() {
            initView();
        }
    };

    private void setPoint(int point){
        List<Integer> pointData = new ArrayList<>();
        for (int i = 0;i <= point;i++){
            pointData.add(i);
        }
        ArrayAdapter<Integer> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,pointData);
        spinnerSetAdapter(adapter,propertyOptionSpinner);
    }

    private void spinnerSetAdapter(ArrayAdapter<Integer> arrayAdapter, Spinner spinner){
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /**
     * 追加扣分项提示
     */
    private void showMessagePositiveDialog(){
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
     * 完成评定提示
     */
    private void showFinshDialog(){
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

    private void setDeduction(){
        Deduction deduction = new Deduction();
        if (isMissing){
            deduction.setDeduction(area.getSinglePoint());
        }else {
            deduction.setDeduction(Integer.parseInt(propertyOptionSpinner.getSelectedItem().toString()));
        }
        deduction.setArea(area.getName());
        deduction.setMissing(isMissing);
        Constants.addDeduction(deduction);
    }

    /**
     * 缺项分输入框
     */
    private boolean isMissing = false;
    private void showMissingPointDialog(){
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
    private String reson;
    private void showResonDialog(){
        if (reson != null){
            showToastShort("当前已有扣分原因，重新输入将替换扣分原因");
        }
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
            builder.setTitle("扣分原因")
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
                           if (text != null || text.length() > 0){
                               reson = text.toString();
                               dialog.dismiss();
                           }else {
                               showToastShort("请填入扣分原因");
                           }
                       }
                   })
                   .create(mCurrentDialogStyle).show();
    }
}
