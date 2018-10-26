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

public class TakePhotoActivity extends BaseFragmentActivity implements View.OnClickListener {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.hydro_takephoto_prospect)
    ImageView hydroTakephotoProspect;
    @BindView(R.id.hydro_takephoto_dam_prospect)
    ImageView hydroTakephotoDamProspect;
    @BindView(R.id.hydro_takephoto_dam_close)
    ImageView hydroTakephotoDamClose;
    @BindView(R.id.hydro_takephoto_gate)
    ImageView hydroTakephotoGate;
    @BindView(R.id.hydro_takephoto_hoist)
    ImageView hydroTakephotoHoist;

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
        setContentView(R.layout.activity_hydro_takephoto);
        ButterKnife.bind(this);
        initTopbar();
        initView();
    }

    /**
     * 初始化标题栏
     */
    private void initTopbar(){
        topbar.setTitle("照片采集");
        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePhotoActivity.this,HydroGeologyActivity.class);
                startActivity(intent);
            }
        });
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 初始化View
     */
    private void initView(){
        hydroTakephotoProspect.setOnClickListener(this);
        hydroTakephotoDamProspect.setOnClickListener(this);
        hydroTakephotoDamClose.setOnClickListener(this);
        hydroTakephotoGate.setOnClickListener(this);
        hydroTakephotoHoist.setOnClickListener(this);
    }

    /**
     * 拍照dialog
     */
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

    /**
     * 拍照或从相册选择
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
                                imageUri = FileProvider.getUriForFile(TakePhotoActivity.this,"com.cykj.survey.fileprovider",fileUri);
                                PhotoUtils.takePicture(TakePhotoActivity.this,imageUri,CODE_CAMERA_REQUEST);
                            }
                        }else {
                            Toast.makeText(TakePhotoActivity.this,"设备没有SD卡！",Toast.LENGTH_SHORT).show();
                            Log.e("erro","设备没有SD卡！");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(TakePhotoActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "从相册选择":
                requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        PhotoUtils.openPic(TakePhotoActivity.this, CODE_GALLERY_REQUEST);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(TakePhotoActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
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
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,TakePhotoActivity.this);
                    if (bitmap != null){
                        switch (target){
                            case "prospect":
                                hydroTakephotoProspect.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "damProspect":
                                hydroTakephotoDamProspect.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "damClose":
                                hydroTakephotoDamClose.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "gate":
                                hydroTakephotoGate.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
                                break;
                            case "hoist":
                                hydroTakephotoHoist.setImageBitmap(ImgUtil.zoomImg(bitmap,200,300));
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
                        Bitmap bitmap1 = PhotoUtils.getBitmapFromUri(newUri,TakePhotoActivity.this);
                        if (bitmap1 != null){
                            switch (target){
                                case "prospect":
                                    hydroTakephotoProspect.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "damProspect":
                                    hydroTakephotoDamProspect.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "damClose":
                                    hydroTakephotoDamClose.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "gate":
                                    hydroTakephotoGate.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
                                    break;
                                case "hoist":
                                    hydroTakephotoHoist.setImageBitmap(ImgUtil.zoomImg(bitmap1,200,300));
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hydro_takephoto_prospect:
                target = "prospect";
                showMenuDialog();
                break;
            case R.id.hydro_takephoto_dam_prospect:
                target = "damProspect";
                showMenuDialog();
                break;
            case R.id.hydro_takephoto_dam_close:
                target = "damClose";
                showMenuDialog();
                break;
            case R.id.hydro_takephoto_gate:
                target = "gate";
                showMenuDialog();
                break;
            case R.id.hydro_takephoto_hoist:
                target = "hoist";
                showMenuDialog();
                break;
            default:
                break;
        }
    }
}
