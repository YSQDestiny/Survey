package com.cykj.survey.fragment.index;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.util.ImgUtil;
import com.cykj.survey.util.PermissionUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TakePhotoOptions;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_license_upload, null);
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
                            byCamera();
                            dialog.dismiss();
                        }else if (items[which].equals("从相册选择")){
                            openAlbum();
                            dialog.dismiss();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    //打开相册
    private void openAlbum(){
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.setType("image/*");
        startActivityForResult(intent,0x006);
    }

    private String imagePath;
    //打开相机
    public void byCamera(){
        String savePath = "";
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/camera/";
            File savedir = new File(savePath);
            if (!savedir.exists()){
                savedir.mkdirs();
            }
        }

        //没有挂载SD卡，无法保存文件
        if (savePath == null || "".equals(savePath)){
            System.out.println("无法保存图片，请检查SD卡是否挂载");
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //照片命名
        String fileName = timeStamp + ".jpg";
        File out = new File(savePath,fileName);
        Uri uri = Uri.fromFile(out);
        //该照片的绝对路径
        imagePath = savePath + fileName;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,0x008);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 8){
            if (imagePath != null && resultCode == -1){
                Log.e(">>>>>>>>>>...",""+imagePath);
                if (target.equals("business")){
                    businessLicenseImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath,200,200));
                }else if (target.equals("industry")){
                    industryLicenseImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath,200,200));
                }else if (target.equals("system")){
                    systemLeveImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath,200,200));
                }
            }
        }
        if(requestCode==6){
            //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
            ContentResolver resolver = getActivity().getContentResolver();
            Uri originalUri = data.getData();//获得图片的uri
            Log.e("uri",">>>>>>>>>>...."+originalUri);
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
                Log.e("uri",">>>>>>>>>>...."+PermissionUtils.getBitmapByPath(path, 480, 800));
                //绑定图片到Imageview
                if (target.equals("business")){
                    businessLicenseImg.setImageBitmap(PermissionUtils.getBitmapByPath(path,300,300));
                }else if (target.equals("industry")){
                    industryLicenseImg.setImageBitmap(PermissionUtils.getBitmapByPath(path,300,300));
                }else if (target.equals("system")){
                    systemLeveImg.setImageBitmap(PermissionUtils.getBitmapByPath(path,300,300));
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

    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    private void photoClick(String fangfa,TakePhoto takePhoto){
        File file = new File(Environment.getExternalStorageDirectory(),"/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);

        configTakePhotoOption(takePhoto);
        switch (fangfa){
            case "拍照":
                takePhoto.onPickFromCapture(imageUri);
                break;
        }
    }
}
