package com.cykj.survey.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.cykj.survey.Constants;
import com.cykj.survey.MainActivity;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.index.IndustryFragment;
import com.cykj.survey.model.CompanyConstants;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PhotoUtils;
import com.cykj.survey.util.Utils;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PhotoUploadActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.business_license_img)
    ImageView businessLicenseImg;
    @BindView(R.id.industry_license_img)
    ImageView industryLicenseImg;
    @BindView(R.id.system_leve_img)
    ImageView systemLeveImg;
    @BindView(R.id.license_upload_button)
    Button licenseUploadButton;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri imageUri;

    private String target;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.fragment_license_upload, null);
        ButterKnife.bind(this,root);
        initTopBar();
        setContentView(root);

        businessLicenseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = "business";
                showMenuDialog();
            }
        });
        industryLicenseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = "industry";
                showMenuDialog();
            }
        });
        systemLeveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = "system";
                showMenuDialog();
            }
        });

        licenseUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadPhoto();
            }
        });
    }

    private void initTopBar() {
        topbar.setTitle("照片上传");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showMenuDialog(){
        final String[] items = new String[]{"拍照","从相册选择"};
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("拍照")){
                            takePhoto(items[which]);
                            dialog.dismiss();
                        }else if (items[which].equals("从相册选择")){
                            takePhoto(items[which]);
                            dialog.dismiss();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
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
                                imageUri = FileProvider.getUriForFile(PhotoUploadActivity.this,"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(PhotoUploadActivity.this,imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(PhotoUploadActivity.this,"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(PhotoUploadActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(PhotoUploadActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(PhotoUploadActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
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
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,PhotoUploadActivity.this);
                    if (bitmap != null){
                        switch (target){
                            case "business":
                                businessLicenseImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "industry":
                                industryLicenseImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "system":
                                systemLeveImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
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
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri,PhotoUploadActivity.this);
                        if (bitmap1 != null){
                            switch (target){
                                case "business":
                                    businessLicenseImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "industry":
                                    industryLicenseImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "system":
                                    systemLeveImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
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

    private void upLoadPhoto(){
        String url = Constants.TEST_SERVICE + "/company/uploadPhoto";

        OkHttpClient client = new OkHttpClient();

        businessLicenseImg.setDrawingCacheEnabled(true);
        String businessPhoto = ImgUtil.bitmapToBase64(businessLicenseImg.getDrawingCache());

        industryLicenseImg.setDrawingCacheEnabled(true);
        String indusrtyPhoto = ImgUtil.bitmapToBase64(businessLicenseImg.getDrawingCache());

        systemLeveImg.setDrawingCacheEnabled(true);
        String systemPhoto = ImgUtil.bitmapToBase64(systemLeveImg.getDrawingCache());

        Long companyId = Constants.REPORT_ID;

        RequestBody body = new FormBody.Builder()
                .add("companyId",Long.toString(CompanyConstants.COMPANY_ID))
                .add("businessPhoto",businessPhoto)
                .add("industryPhoto",indusrtyPhoto)
                .add("systemPhoto",systemPhoto)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    finish();
                }
            }
        });
    }

}