package com.cykj.survey.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.model.Accident;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.PropertyAccident;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PhotoUtils;
import com.cykj.survey.view.CustomGridView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccidentActivity extends BaseFragmentActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.accident_site_img)
    ImageView accidentSiteImg;
    @BindView(R.id.accident_surroundings_img)
    ImageView accidentSurroundingsImg;
    @BindView(R.id.accident_type_grid)
    CustomGridView accidentTypeGrid;
    @BindView(R.id.accident_possibility_spinner)
    Spinner accidentPossibilitySpinner;
    @BindView(R.id.accident_frequencies_spinner)
    Spinner accidentFrequenciesSpinner;
    @BindView(R.id.accident_result_spinner)
    Spinner accidentResultSpinner;


    private ArrayAdapter<String> possibilityAdapter;
    private ArrayAdapter<String> frequenciesAdapter;
    private ArrayAdapter<String> resultAdapter;

    private double possibilityPoint = 0;
    private double frequenciesPoint = 0;
    private double resultPoint = 0;

    List<String> possibilityList;
    List<String> frequenciesList;
    List<String> resultList;

    private AccidentGridAdapter typeGridAdapter;
    private List<AccidentGridModel> typeData;

    private Map<String, String> typeMap = new HashMap<>();

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri imageUri;
    private String terget;

    private String temp;

    @Override
    protected int getContextViewId() {
        return R.id.activity_accident;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_accident_description);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        temp = intent.getStringExtra("target");
        initTopbar();
        initData();

        accidentSiteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terget = "site";
                showMenuDialog();
            }
        });

        accidentSurroundingsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terget = "surroundings";
                showMenuDialog();
            }
        });

        typeGridAdapter = new AccidentGridAdapter(this, typeData);
        accidentTypeGrid.setAdapter(typeGridAdapter);
        accidentTypeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (typeData.get(position).isSelect()) {
                    typeData.get(position).setSelect(false);
                    typeMap.remove(typeData.get(position).getName());
                } else {
                    typeData.get(position).setSelect(true);
                    typeMap.put(typeData.get(position).getName(), typeData.get(position).getName());
                }
                typeGridAdapter.notifyDataSetChanged();
            }
        });

        possibilityAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,possibilityList);
        possibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accidentPossibilitySpinner.setAdapter(possibilityAdapter);

        frequenciesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,frequenciesList);
        frequenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accidentFrequenciesSpinner.setAdapter(frequenciesAdapter);

        resultAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,resultList);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accidentResultSpinner.setAdapter(resultAdapter);

        accidentPossibilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (possibilityList.get(position)){
                    case "请选择":
                        possibilityPoint = 0;
                        break;
                    case "必然发生":
                        possibilityPoint = 10;
                        break;
                    case "相当可能":
                        possibilityPoint = 6;
                        break;
                    case "可能":
                        possibilityPoint = 3;
                        break;
                    case "可能性小，完全意外":
                        possibilityPoint = 1;
                        break;
                    case "很不可能，可以设想":
                        possibilityPoint = 0.5;
                        break;
                    case "不可能":
                        possibilityPoint = 0.2;
                        break;
                    case "不会发生":
                        possibilityPoint = 0.1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        accidentFrequenciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (frequenciesList.get(position)){
                    case "请选择":
                        frequenciesPoint = 0;
                        break;
                    case "24小时持续暴露":
                        frequenciesPoint = 10;
                        break;
                    case "每天工作时间内暴露":
                        frequenciesPoint = 6;
                        break;
                    case "每周一次或偶然暴露":
                        frequenciesPoint = 3;
                        break;
                    case "每月一次暴露":
                        frequenciesPoint = 2;
                        break;
                    case "每年几次暴露":
                        frequenciesPoint = 1;
                        break;
                    case "非常罕见暴露":
                        frequenciesPoint = 0.5;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        accidentResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (resultList.get(position)){
                    case "请选择":
                        resultPoint = 0;
                        break;
                    case "10人以上死亡或500万以上直接经济损失":
                        resultPoint = 100;
                        break;
                    case "3～9人死亡或500万以下直接经济损失":
                        resultPoint = 40;
                        break;
                    case "1～2人死亡或100万以下直接经济损失":
                        resultPoint = 15;
                        break;
                    case "多人伤残或50万以下直接经济损失":
                        resultPoint = 7;
                        break;
                    case "个体伤残或10万以下直接经济损失":
                        resultPoint = 3;
                        break;
                    case "引人注意":
                        resultPoint = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }

    private void initTopbar() {

        topbar.setTitle("事故说明");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp != null & temp.equals("property")){
                    postPropertyAccident();
                }else {
                    postAccident();
                }
            }
        });

    }

    private void initData() {

        typeData = new ArrayList<>();
        typeData.add(new AccidentGridModel("触电事故", false));
        typeData.add(new AccidentGridModel("火灾、爆炸", false));
        typeData.add(new AccidentGridModel("雷击事故", false));
        typeData.add(new AccidentGridModel("暴乱事件", false));
        typeData.add(new AccidentGridModel("踩踏事故", false));
        typeData.add(new AccidentGridModel("交通事故", false));
        typeData.add(new AccidentGridModel("淹溺、灼烫", false));
        typeData.add(new AccidentGridModel("物体打击", false));
        typeData.add(new AccidentGridModel("机械伤害", false));
        typeData.add(new AccidentGridModel("化学伤害", false));
        typeData.add(new AccidentGridModel("其他", false));

        possibilityList = new ArrayList<>();
        possibilityList.add("请选择");
        possibilityList.add("必然发生");
        possibilityList.add("相当可能");
        possibilityList.add("可能");
        possibilityList.add("可能性小，完全意外");
        possibilityList.add("很不可能，可以设想");
        possibilityList.add("不可能");
        possibilityList.add("不会发生");

        frequenciesList = new ArrayList<>();
        frequenciesList.add("请选择");
        frequenciesList.add("24小时持续暴露");
        frequenciesList.add("每天工作时间内暴露");
        frequenciesList.add("每周一次或偶然暴露");
        frequenciesList.add("每月一次暴露");
        frequenciesList.add("每年几次暴露");
        frequenciesList.add("非常罕见暴露");

        resultList = new ArrayList<>();
        resultList.add("请选择");
        resultList.add("10人以上死亡或500万以上直接经济损失");
        resultList.add("3～9人死亡或500万以下直接经济损失");
        resultList.add("1～2人死亡或100万以下直接经济损失");
        resultList.add("多人伤残或50万以下直接经济损失");
        resultList.add("个体伤残或10万以下直接经济损失");
        resultList.add("引人注意");
    }

    private void showMenuDialog() {
        final String[] items = new String[]{"拍照", "从相册选择"};
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("拍照")) {
                            takePhoto(items[which]);
                            dialog.dismiss();
                        } else if (items[which].equals("从相册选择")) {
                            takePhoto(items[which]);
                            dialog.dismiss();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void postAccident(){

        String url = Constants.TEST_SERVICE + "/accident/postAccident";

        OkHttpClient client = new OkHttpClient();

        Accident accident = new Accident();
        accident.setCompanyId(Constants.REPORT_ID);
        accident.setInstructions(accidentEdit.getText().toString());
        String accidentType = "";
        if (typeMap.size() != 0) {
            int i = 0;
            for (String type : typeMap.values()) {
                if (i == 0) {
                    accidentType += type;
                    i++;
                } else {
                    accidentType += "," + type;
                }
            }
        }
        accident.setResult(accidentResultSpinner.getSelectedItem().toString());
        accident.setType(accidentType);
        accidentSiteImg.setDrawingCacheEnabled(true);
        accident.setSitePhoto(ImgUtil.bitmapToBase64(accidentSiteImg.getDrawingCache()));
        accidentSurroundingsImg.setDrawingCacheEnabled(true);
        accident.setSurroundingsPhoto(ImgUtil.bitmapToBase64(accidentSurroundingsImg.getDrawingCache()));
        double D = possibilityPoint * frequenciesPoint * resultPoint;
        if (D > 320){
            accident.setLevel("1");
            accident.setLevelDes("极其危险，不能继续作业");
        }else if (D > 160 & D <= 320){
            accident.setLevel("2");
            accident.setLevelDes("高度危险，要立即整改");
        }else if (D > 10 & D <= 160){
            accident.setLevel("3");
            accident.setLevelDes("显著危险，需要整改");
        }else if (D > 20 & D <= 70){
            accident.setLevel("4");
            accident.setLevelDes("一般危险，需要注意");
        }else if (D > 0 & D <= 20){
            accident.setLevel("5");
            accident.setLevelDes("稍有危险，可以接受");
        }

        RequestBody body = new FormBody.Builder()
                .add("accident", JSONObject.toJSONString(accident))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

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

    private void postPropertyAccident(){
        PropertyAccident propertyAccident = new PropertyAccident();

        String url = Constants.TEST_SERVICE + "/property/postAccident";

        OkHttpClient client = new OkHttpClient();

        propertyAccident.setPropertyId(Constants.PROPERTY_ID);
        propertyAccident.setInstructions(accidentEdit.getText().toString());
        String accidentType = "";
        if (typeMap.size() != 0) {
            int i = 0;
            for (String type : typeMap.values()) {
                if (i == 0) {
                    accidentType += type;
                    i++;
                } else {
                    accidentType += "," + type;
                }
            }
        }
        propertyAccident.setResult(accidentResultSpinner.getSelectedItem().toString());
        propertyAccident.setType(accidentType);
        accidentSiteImg.setDrawingCacheEnabled(true);
        propertyAccident.setSitePhoto(ImgUtil.bitmapToBase64(accidentSiteImg.getDrawingCache()));
        accidentSurroundingsImg.setDrawingCacheEnabled(true);
        propertyAccident.setSurroundingsPhoto(ImgUtil.bitmapToBase64(accidentSurroundingsImg.getDrawingCache()));
        double D = possibilityPoint * frequenciesPoint * resultPoint;
        if (D > 320){
            propertyAccident.setLevel("1");
            propertyAccident.setLevelDes("极其危险，不能继续作业");
        }else if (D > 160 & D <= 320){
            propertyAccident.setLevel("2");
            propertyAccident.setLevelDes("高度危险，要立即整改");
        }else if (D > 10 & D <= 160){
            propertyAccident.setLevel("3");
            propertyAccident.setLevelDes("显著危险，需要整改");
        }else if (D > 20 & D <= 70){
            propertyAccident.setLevel("4");
            propertyAccident.setLevelDes("一般危险，需要注意");
        }else if (D > 0 & D <= 20){
            propertyAccident.setLevel("5");
            propertyAccident.setLevelDes("稍有危险，可以接受");
        }

        RequestBody body = new FormBody.Builder()
                .add("accident", JSONObject.toJSONString(propertyAccident))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void takePhoto(String temp){
        switch (temp){
            case "拍照":
                requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        if (hasSdcard()){
                            imageUri = Uri.fromFile(fileUri);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                                imageUri = FileProvider.getUriForFile(AccidentActivity.this,"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(AccidentActivity.this,imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(AccidentActivity.this,"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(AccidentActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(AccidentActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(AccidentActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,AccidentActivity.this);
                    if (bitmap != null){
                        switch (terget){
                            case "site":
                                accidentSiteImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "surroundings":
                                accidentSurroundingsImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                        }
                    }
                    break;
                //访问相册回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()){
                        String str = PhotoUtils.getPath(this,data.getData());
                        Uri newUri = Uri.parse(str);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            newUri = FileProvider.getUriForFile(this,"com.cykj.survey.fileprovider",new File(newUri.getPath()));
                        }
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri,AccidentActivity.this);
                        if (bitmap1 != null){
                            switch (terget){
                                case "site":
                                    accidentSiteImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "surroundings":
                                    accidentSurroundingsImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                            }
                        }
                    }else {
                        Toast.makeText(this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
