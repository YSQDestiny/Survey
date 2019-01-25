package com.cykj.survey.ui.activity.project;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ProjectAccident;
import com.cykj.survey.model.ProjectConstants;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PhotoUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectAccidentDescActivity extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.project_accident_name)
    EditText projectAccidentName;
    @BindView(R.id.project_accident_edit)
    EditText projectAccidentEdit;
    @BindView(R.id.project_accident_site_img)
    ImageView projectAccidentSiteImg;
    @BindView(R.id.project_accident_surroundings_img)
    ImageView projectAccidentSurroundingsImg;
    @BindView(R.id.project_accident_type)
    EditText projectAccidentType;
    @BindView(R.id.project_accident_suggestion)
    EditText projectAccidentSuggestion;


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
        setContentView(R.layout.activity_project_accident);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String accidentJson = intent.getStringExtra("accident");
        if (accidentJson != null) {
            ProjectAccident accident = JSONObject.parseObject(accidentJson, ProjectAccident.class);
            projectAccidentName.setText(accident.getName());
            projectAccidentEdit.setText(accident.getInstructions());
            projectAccidentType.setText(accident.getType());
            projectAccidentSuggestion.setText(accident.getSuggestion());
            projectAccidentSiteImg.setImageBitmap(ImgUtil.base64ToBitmap(accident.getSitePhoto()));
            projectAccidentSurroundingsImg.setImageBitmap(ImgUtil.base64ToBitmap(accident.getSurroundingsPhoto()));
        }
        projectAccidentSiteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terget = "site";
                showMenuDialog();
            }
        });

        projectAccidentSurroundingsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terget = "surroundings";
                showMenuDialog();
            }
        });
    }


    private void initTopbar() {
        topbar.setTitle("添加风险点");
        topbar.addRightTextButton("完成", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccident();
            }
        });
    }

    private void saveAccident() {

        ProjectAccident accident = new ProjectAccident();
        accident.setProjectId(ProjectConstants.PROJECT_ID);
        accident.setName(projectAccidentName.getText().toString());
        accident.setInstructions(projectAccidentEdit.getText().toString());
        accident.setType(projectAccidentType.getText().toString());
        accident.setSuggestion(projectAccidentSuggestion.getText().toString());
        projectAccidentSiteImg.setDrawingCacheEnabled(true);
//        accident.setSitePhoto(ImgUtil.bitmapToBase64(projectAccidentSiteImg.getDrawingCache()));

        projectAccidentSurroundingsImg.setDrawingCacheEnabled(true);
//        accident.setSurroundingsPhoto(ImgUtil.bitmapToBase64(projectAccidentSurroundingsImg.getDrawingCache()));

        List<ProjectAccident> list = Constants.projectAccidentList;
        Constants.addProjectAccident(accident);
        finish();
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void takePhoto(String temp) {
        switch (temp) {
            case "拍照":
                requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        if (hasSdcard()) {
                            imageUri = Uri.fromFile(fileUri);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                imageUri = FileProvider.getUriForFile(ProjectAccidentDescActivity.this, "com.cykj.survey.fileprovider", fileUri);
                                PhotoUtils.takePicture(ProjectAccidentDescActivity.this, imageUri, CODE_CAMERA_REQUEST);
                            }
                        } else {
                            Toast.makeText(ProjectAccidentDescActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                            Log.e("erro", "设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(ProjectAccidentDescActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(ProjectAccidentDescActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(ProjectAccidentDescActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri, ProjectAccidentDescActivity.this);
                    if (bitmap != null) {
                        switch (terget) {
                            case "site":
                                projectAccidentSiteImg.setImageBitmap(ImgUtil.zoomImg(bitmap, 200, 300));
                                break;
                            case "surroundings":
                                projectAccidentSurroundingsImg.setImageBitmap(ImgUtil.zoomImg(bitmap, 200, 300));
                                break;
                        }
                    }
                    break;
                //访问相册回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        String str = PhotoUtils.getPath(this, data.getData());
                        Uri newUri = Uri.parse(str);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(this, "com.cykj.survey.fileprovider", new File(newUri.getPath()));
                        }
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri, ProjectAccidentDescActivity.this);
                        if (bitmap1 != null) {
                            switch (terget) {
                                case "site":
                                    projectAccidentSiteImg.setImageBitmap(ImgUtil.zoomImg(bitmap1, 200, 300));
                                    break;
                                case "surroundings":
                                    projectAccidentSurroundingsImg.setImageBitmap(ImgUtil.zoomImg(bitmap1, 200, 300));
                                    break;
                            }
                        }
                    } else {
                        Toast.makeText(this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
