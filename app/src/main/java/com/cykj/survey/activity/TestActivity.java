package com.cykj.survey.activity;

import android.support.v7.app.AppCompatActivity;


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

/**
 * xiao ping zi
 * shou wo zhe xiao ping zi
 * zhuang ping fan de ri zi
 * sheng huo de da xiao shi
 * shu xi de guo ke
 * dao ying zhong kan bi ci
 * ge zhong
 * jin kou de shou zhi
 * yan li de bi ci
 * xie xia xin de gu shi
 * fen fen zhe die de cheng mo
 * ceng ceng ying cang ning yi ge wo
 * shuo zhe bu wan mei de yin se
 * chuan guo yi duo duo xuan lan de xuan wo
 * cong cong feng chui hua kai ri luo
 * shi jian a jie kai wo de mi huo
 * xu xia de yuan wang zai tian bian hai jiao pu cheng le yin he
 * deng zhe liu lang de ni lai qian zhu wo de shou
 * piao liu de xiao ping zi
 * zhuang ku xiao de yang zi
 * feng kuang huo shi you zhi
 * ge zhong xi nu ai le
 * fen fen zhe die de cheng mo
 * cheng cheng ying cang ning yi ge wo
 * shuo zhe bu wan mei de yin se
 * chuan guo yi duo duo xuan lan de xuan wo
 * chong chong feng chui hua kai ri luo o
 * shi jian a jie kai wo de mi huo
 * xu xia de yuan wang zai tian bian hai jiao pu cheng le yin he
 * deng zhe liu lang de ni lai qian zhu wo de shou
 * feng feng zhe die de cheng mo
 * cheng cheng ying cang ling yi ge wo
 * shuo zhe bu wan mei de yin se
 * chuan guo yi duo duo xuan lan de xuan wo
 * cong cong feng chui hua kai ri luo o
 * shi jian a jie kai wo de mi huo
 * xu xia de yuan wang zai tian bian hai jiao pu cheng le yin he
 * deng zhe liu lang de ni jin jin qian zhu wo de shou
 */


/**
 * 爆刘继芬
 * 不要问我，一生曾经爱过多少人
 * 你懂我伤有多深呐
 * 要剥开伤口总是很残忍
 * 劝你莫做那个神戳戳滴人呐
 * 多情暂且爆刘继芬
 * 爆刘继芬呐
 * 不喜欢孤独
 * 却又害怕两个人相处
 * 这分明是一种痛苦
 *
 */

}
