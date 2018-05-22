package com.cykj.survey.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.cykj.survey.MainActivity;
import com.cykj.survey.R;
import com.cykj.survey.fragment.utils.PermissionUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class TestActivity extends AppCompatActivity {

//    private ImageView imageView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        imageView = (ImageView)findViewById(R.id.image);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (PermissionUtils.isCameraPermission(MainActivity.this, 0x007))
//                    byCamera();
//            }
//        });
//        findViewById(R.id.album).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (PermissionUtils.isCameraPermission(MainActivity.this, 0x009))
//                    //打开相册
//                    openAlbum();
//            }
//        });
//    }
//    //打开相册
//    private  void  openAlbum(){
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        startActivityForResult(intent, 0x006); // 打开相册
//    }
//
//    //请求权限回调
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 0x007:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    byCamera();
//                } else {
//                    Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case 0x009:
//                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                    byCamera();
//                } else {
//                    Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//
//    private String imagePath;
//    //打开相机
//    public void byCamera() {
//        String savePath = "";
//        String storageState = Environment.getExternalStorageState();
//        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
//            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/camera/";
//            File savedir = new File(savePath);
//            if (!savedir.exists()) {
//                savedir.mkdirs();
//            }
//        }
//
//        // 没有挂载SD卡，无法保存文件
//        if (savePath == null || "".equals(savePath)) {
//            System.out.println("无法保存照片，请检查SD卡是否挂载");
//            return;
//        }
//
//        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        //照片命名
//        String fileName = timeStamp + ".jpg";
//        File out = new File(savePath, fileName);
//        Uri uri = Uri.fromFile(out);
//        //该照片的绝对路径
//        imagePath = savePath + fileName;
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(intent, 0x008);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 8) {
//            if(imagePath!=null && resultCode == RESULT_OK){
//                Log.e(">>>>>>>>>>....",""+imagePath);
//                imageView.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath,480,800));
//            }
//        }
//        if(requestCode==6){
//            //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
//            ContentResolver resolver = getContentResolver();
//            Uri originalUri = data.getData();//获得图片的uri
//            Log.e("uri",">>>>>>>>>>...."+originalUri);
//            Bitmap bm = null;
//            try {
//                //显得到bitmap图片
//                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
//                //获取图片的路径
//                String[] proj = {MediaStore.Images.Media.DATA};
//                Cursor cursor = managedQuery(originalUri, proj, null, null, null);
//                //获得用户选择的图片的索引值
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                //将光标移至开头 ，这个很重要，不小心很容易引起越界
//                cursor.moveToFirst();
//                //最后根据索引值获取图片路径
//                String path = cursor.getString(column_index);
//                Log.e("uri",">>>>>>>>>>...."+ PermissionUtils.getBitmapByPath(path, 480, 800));
//                //绑定图片到Imageview
//                imageView.setImageBitmap(PermissionUtils.getBitmapByPath(path, 480, 800));
//                //转换成file文件,上传服务器uploadImg(file)
//               /* File file=new File(path);
//                Log.e("uri",">>>>>>>>>>...."+file);
//                uploadImg(file);*/
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}
