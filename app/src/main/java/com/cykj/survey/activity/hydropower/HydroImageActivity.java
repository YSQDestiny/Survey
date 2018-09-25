package com.cykj.survey.activity.hydropower;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.activity.AccidentActivity;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.ImageGridAdapter;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PhotoUtils;
import com.cykj.survey.view.CustomGridView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HydroImageActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.grid)
    CustomGridView grid;

    private List<Bitmap> dataList = new ArrayList<>();
    private ImageGridAdapter adapter;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri imageUri;
    private String terget;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_hydro_image);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initView() {
        adapter = new ImageGridAdapter(this,dataList);
        grid.setAdapter(adapter);
    }

    private void initTopbar() {
        topbar.setTitle("现场照片");
        topbar.addRightTextButton("添加照片", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog();
            }
        });
    }

    private Bitmap getBitMap(){
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.icon_add_img)).getBitmap();
        return bitmap;
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
                                imageUri = FileProvider.getUriForFile(HydroImageActivity.this,"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(HydroImageActivity.this,imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(HydroImageActivity.this,"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(HydroImageActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(HydroImageActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(HydroImageActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
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
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,HydroImageActivity.this);
                    if (bitmap != null){
                        dataList.add(bitmap);
                        adapter.notifyDataSetChanged();
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
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri,HydroImageActivity.this);
                        if (bitmap1 != null){
                            dataList.add(bitmap1);
                            adapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
