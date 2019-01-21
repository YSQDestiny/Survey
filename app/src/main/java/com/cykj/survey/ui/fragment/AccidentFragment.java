package com.cykj.survey.ui.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.ui.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.model.Accident;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PermissionUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


public class AccidentFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.accident_site_img)
    ImageView accidentSiteImg;
    @BindView(R.id.accident_surroundings_img)
    ImageView accidentSurroundingsImg;
    @BindView(R.id.accident_type_grid)
    GridView accidentTypeGrid;
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

    private String terget;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_accident_description, null);

        /**
         * 必须授权，否则在选择图片剪裁后不能进行写入到SD卡的条件，会导致设置图片失败
         */
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 6);
        }

        ButterKnife.bind(this, root);

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

        typeGridAdapter = new AccidentGridAdapter(getActivity(), typeData);
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

        possibilityAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,possibilityList);
        possibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accidentPossibilitySpinner.setAdapter(possibilityAdapter);

        frequenciesAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,frequenciesList);
        frequenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accidentFrequenciesSpinner.setAdapter(frequenciesAdapter);

        resultAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,resultList);
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
                switch (possibilityList.get(position)){
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
                switch (possibilityList.get(position)){
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

        return root;
    }

    private void initTopbar() {

        topbar.setTitle("事故说明");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAccident();
            }
        });

    }

    /**
     * 上传风险详述信息
     */
    private void postAccident() {
        showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
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
        accident.setType(accidentType);
        accidentSiteImg.setDrawingCacheEnabled(true);
        accident.setSitePhoto(ImgUtil.bitmapToBase64(accidentSiteImg.getDrawingCache()));
        accidentSurroundingsImg.setDrawingCacheEnabled(true);
        accident.setSurroundingsPhoto(ImgUtil.bitmapToBase64(accidentSurroundingsImg.getDrawingCache()));
        double D = possibilityPoint * frequenciesPoint * resultPoint;
        if (D > 320){
            accident.setLevel("1级风险");
            accident.setLevelDes("极其危险，不能继续作业");
        }else if (D > 160 & D <= 320){
            accident.setLevel("2极风险");
            accident.setLevelDes("高度危险，要立即整改");
        }else if (D > 10 & D <= 160){
            accident.setLevel("3级风险");
            accident.setLevelDes("显著危险，需要整改");
        }else if (D > 20 & D <= 70){
            accident.setLevel("4级风险");
            accident.setLevelDes("一般危险，需要注意");
        }else if (D > 0 & D <= 20){
            accident.setLevel("5级风险");
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
                tipDialogDismiss();
                ResultModel result = JSONObject.parseObject(response.body().string(), ResultModel.class);
                if (result.getCode() == 0) {
                    popBackStack();
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
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("拍照")) {
                            byCamera();
                            dialog.dismiss();
                        } else if (items[which].equals("从相册选择")) {
                            openAlbum();
                            dialog.dismiss();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    //打开相册
    private void openAlbum() {
        Intent intent = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.setType("image/*");
        startActivityForResult(intent, 0x006);
    }

    private String imagePath;

    //打开相机
    public void byCamera() {
        String savePath = "";
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/camera/";
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }

        //没有挂载SD卡，无法保存文件
        if (savePath == null || "".equals(savePath)) {
            System.out.println("无法保存图片，请检查SD卡是否挂载");
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //照片命名
        String fileName = timeStamp + ".jpg";
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);
        //该照片的绝对路径
        imagePath = savePath + fileName;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 0x008);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8) {
            if (imagePath != null && resultCode == -1) {
                Log.e(">>>>>>>>>>...", "" + imagePath);
                if (terget.equals("site")) {
                    accidentSiteImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath, 200, 200));
                } else if (terget.equals("surroundings")) {
                    accidentSurroundingsImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath, 200, 200));
                }
            }
        }
        if (requestCode == 6) {
            //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
            ContentResolver resolver = getActivity().getContentResolver();
            Uri originalUri = data.getData();//获得图片的uri
            Log.e("uri", ">>>>>>>>>>...." + originalUri);
            Bitmap bm = null;
            try {
                //显得到bitmap图片
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                //获取图片的路径
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().managedQuery(originalUri, proj, null, null, null);
                //获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                //将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                //最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                Log.e("uri", ">>>>>>>>>>...." + PermissionUtils.getBitmapByPath(path, 480, 800));
                //绑定图片到Imageview
                if (terget.equals("site")) {
                    accidentSiteImg.setImageBitmap(PermissionUtils.getBitmapByPath(path, 200, 200));
                } else if (terget.equals("surroundings")) {
                    accidentSurroundingsImg.setImageBitmap(PermissionUtils.getBitmapByPath(path, 200, 200));
                }
                //转换成file文件,上传服务器uploadImg(file)
               /* File file=new File(path);
                Log.e("uri",">>>>>>>>>>...."+file);
                uploadImg(file);*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
