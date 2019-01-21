package com.cykj.survey.ui.fragment.index;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PhotoUtils;
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


public class LicenseUploadFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.business_license_img)
    ImageView businessLicenseImg;
    @BindView(R.id.industry_license_img)
    ImageView industryLicenseImg;
    @BindView(R.id.system_leve_img)
    ImageView systemLeveImg;
    @BindView(R.id.license_upload_button)
    Button mButton;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private String target;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri imageUri;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_license_upload, null);
        ButterKnife.bind(this, root);
        initTopBar();
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

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadPhoto();
            }
        });

        return root;
    }

    private void upLoadPhoto(){
        showTipDialog("请稍等...", QMUITipDialog.Builder.ICON_TYPE_LOADING);
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
                .add("companyId",companyId.toString())
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
                tipDialogDismiss();
                ResultModel result = JSONObject.parseObject(response.body().string(),ResultModel.class);
                if (result.getCode() == 0){
                    QMUIFragment fragment = new IndustryFragment();
                    startFragmentAndDestroyCurrent(fragment);
                }
            }
        });
    }

    private void initTopBar() {

        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopbar.setTitle("照片上传");

    }

    private void showMenuDialog(){
        final String[] items = new String[]{"拍照","从相册选择"};
        new QMUIDialog.MenuDialogBuilder(getActivity())
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
                requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        if (hasSdcard()){
                            imageUri = Uri.fromFile(fileUri);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                                imageUri = FileProvider.getUriForFile(getActivity(),"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(getActivity(),imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(getActivity(),"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(getActivity(), "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
        }
    }


    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case CODE_CAMERA_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,getContext());
                    if (bitmap != null){
                        switch (target){
                            case "business":
                                businessLicenseImg.setImageBitmap(bitmap);
                                break;
                            case "industry":
                                industryLicenseImg.setImageBitmap(bitmap);
                                break;
                            case "system":
                                systemLeveImg.setImageBitmap(bitmap);
                                break;
                        }
                    }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




}
