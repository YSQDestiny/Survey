package com.cykj.survey.activity.property;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.activity.PhotoUploadActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PhotoUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyUploadActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.property_business_img)
    ImageView propertyBusinessImg;
    @BindView(R.id.property_industry_img)
    ImageView propertyIndustryImg;
    @BindView(R.id.property_system_leve_img)
    ImageView propertySystemLeveImg;
    @BindView(R.id.property_upload_button)
    Button propertyUploadButton;

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
        setContentView(R.layout.activity_property_upload);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        propertyBusinessImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = "business";
                showMenuDialog();
            }
        });
        propertyIndustryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = "industry";
                showMenuDialog();
            }
        });
        propertySystemLeveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = "system";
                showMenuDialog();
            }
        });

        propertyUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadPhoto();
            }
        });
    }

    /**
     * 上传照片
     */
    private void upLoadPhoto() {

    }

    /**
     * 显示拍照Dialog
     */
    private void showMenuDialog() {
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

    /**
     * 初始化标题栏
     */
    private void initTopbar() {
        topbar.setTitle("执照上传");
        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertyUploadActivity.this,PropertyAreaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * 拍照
     * @param temp
     */
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
                                imageUri = FileProvider.getUriForFile(PropertyUploadActivity.this,"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(PropertyUploadActivity.this,imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(PropertyUploadActivity.this,"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(PropertyUploadActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(PropertyUploadActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(PropertyUploadActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,PropertyUploadActivity.this);
                    if (bitmap != null){
                        switch (target){
                            case "business":
                                propertyBusinessImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "industry":
                                propertyIndustryImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "system":
                                propertySystemLeveImg.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
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
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri,PropertyUploadActivity.this);
                        if (bitmap1 != null){
                            switch (target){
                                case "business":
                                    propertyBusinessImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "industry":
                                    propertyIndustryImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "system":
                                    propertySystemLeveImg.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
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
