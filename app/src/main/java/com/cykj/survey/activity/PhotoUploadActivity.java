package com.cykj.survey.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cykj.survey.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoUploadActivity extends AppCompatActivity {

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

    private String target;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

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

            }
        });
    }

    private void initTopBar() {
        topbar.setTitle("照片上传");
    }

    private void showMenuDialog(){
        final String[] items = new String[]{"拍照","从相册选择"};
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("拍照")){
//                            byCamera();
                            dialog.dismiss();
                        }else if (items[which].equals("从相册选择")){
//                            openAlbum();
                            dialog.dismiss();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }
}
