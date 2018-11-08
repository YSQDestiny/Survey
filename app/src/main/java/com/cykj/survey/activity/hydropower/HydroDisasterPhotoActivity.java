package com.cykj.survey.activity.hydropower;

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
import android.widget.AdapterView;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.ImageGridAdapter;
import com.cykj.survey.model.HydroImage;
import com.cykj.survey.util.PermissionUtils;
import com.cykj.survey.util.PhotoUtils;
import com.cykj.survey.view.CustomGridView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HydroDisasterPhotoActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.grid)
    CustomGridView grid;

    private List<HydroImage> dataList = new ArrayList<>();
    private ImageGridAdapter adapter;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri imageUri;
    private String terget;
    private HydroImage hydroImage;

    private String[] items = new String[]{"消防报警系统","厂房内部","消防器材放置位置","透平油等易燃物放置位置","中控室采集监控系统","升压站/变压器","变压器铭牌",
                                            "变压器近景","厂房内动力电缆排布"};

    private int pos = -1;

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

    private void initTopbar() {
        topbar.setTitle("现场照片");
        topbar.addRightTextButton("添加照片", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemMenuDialog(items);
            }
        });
    }

    private void initView() {
        adapter = new ImageGridAdapter(this,dataList);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                showItemMenuDialog(items);
            }
        });
    }

    private void showItemMenuDialog(final String[] items) {
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (pos != -1){
                            hydroImage = new HydroImage();
                            hydroImage.setName(items[which]);
                        }else {
                            dataList.get(pos).setName(items[which]);
                        }
                        dialog.dismiss();
                        showMenuDialog();
                    }
                })
                .create(mCurrentDialogStyle).show();
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
                                imageUri = FileProvider.getUriForFile(HydroDisasterPhotoActivity.this,"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(HydroDisasterPhotoActivity.this,imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(HydroDisasterPhotoActivity.this,"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(HydroDisasterPhotoActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(HydroDisasterPhotoActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(HydroDisasterPhotoActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }

    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,HydroDisasterPhotoActivity.this);
                    if (bitmap != null){
                        if (pos != -1){
                            dataList.get(pos).setImg(PermissionUtils.compressImage(bitmap));
                            pos = -1;
                        }else {
                            hydroImage.setImg(PermissionUtils.compressImage(bitmap));
                            dataList.add(hydroImage);
                            hydroImage = null;
                        }

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
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri,HydroDisasterPhotoActivity.this);
                        if (bitmap1 != null){
                            if (pos != -1){
                                dataList.get(pos).setImg(PermissionUtils.compressImage(bitmap1));
                                pos = -1;
                            }else {
                                hydroImage.setImg(PermissionUtils.compressImage(bitmap1));
                                dataList.add(hydroImage);
                                hydroImage = null;
                            }
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
