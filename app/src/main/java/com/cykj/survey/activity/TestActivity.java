package com.cykj.survey.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cykj.survey.MainActivity;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.util.PhotoUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TestActivity extends BaseFragmentActivity {

    @BindView(R.id.test_btn)
    Button testBtn;
    @BindView(R.id.test_img)
    ImageView testImg;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_test);
        ButterKnife.bind(this);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(TestActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        if (hasSdcard()) {
                            imageUri = Uri.fromFile(fileUri);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                //通过FileProvider创建一个content类型的Uri
                                imageUri = FileProvider.getUriForFile(TestActivity.this, "com.cykj.survey.fileprovider", fileUri);
                            PhotoUtils.takePicture(TestActivity.this, imageUri, CODE_CAMERA_REQUEST);
                        } else {
                            Toast.makeText(TestActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                            Log.e("asd", "设备没有SD卡");
                        }
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(TestActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case CODE_CAMERA_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(imageUri,this);
                    if (bitmap != null){
                        testImg.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
