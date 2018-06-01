package com.cykj.survey.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragment;
import com.cykj.survey.fragment.adapter.AccidentGridAdapter;
import com.cykj.survey.model.AccidentGridModel;
import com.cykj.survey.util.PermissionUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AccidentFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.accident_edit)
    EditText accidentEdit;
    @BindView(R.id.accident_site_img)
    ImageView accidentSiteImg;
    @BindView(R.id.accident_surroundings_img)
    ImageView accidentSurroundingsImg;
    @BindView(R.id.accident_type_grid)
    GridView accidentTypeGrid;
    @BindView(R.id.accident_result_grid)
    GridView accidentResultGrid;

    private AccidentGridAdapter typeGridAdapter;
    private AccidentGridAdapter resultAdapter;
    private List<AccidentGridModel> typeData;
    private List<AccidentGridModel> resultData;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private String terget;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_accident_description, null);

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

        initTopbar();
        initData();
        initResultData();

        accidentSiteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terget = "site";
                showMenuDialog();
            }
        });

        accidentSurroundingsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terget = "surroundings";
                showMenuDialog();
            }
        });

        typeGridAdapter = new AccidentGridAdapter(getActivity(),typeData);
        accidentTypeGrid.setAdapter(typeGridAdapter);
        accidentTypeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (typeData.get(position).isSelect()){
                    typeData.get(position).setSelect(false);
                }else {
                    typeData.get(position).setSelect(true);
                }
                typeGridAdapter.notifyDataSetChanged();
            }
        });

        resultAdapter = new AccidentGridAdapter(getActivity(),resultData);
        accidentResultGrid.setAdapter(resultAdapter);
        accidentResultGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (resultData.get(position).isSelect()){
                    resultData.get(position).setSelect(false);
                }else {
                    resultData.get(position).setSelect(true);
                    showSingleChoiceDialog(resultData.get(position).getName(),position);
                }
                resultAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }

    private void initTopbar() {

        topbar.setTitle("事故说明");
        topbar.addRightTextButton("完成",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

    }

    private void initData(){
        typeData = new ArrayList<>();
        typeData.add(new AccidentGridModel("触电事故",false));
        typeData.add(new AccidentGridModel("火灾、爆炸",false));
        typeData.add(new AccidentGridModel("雷击事故",false));
        typeData.add(new AccidentGridModel("暴乱事件",false));
        typeData.add(new AccidentGridModel("踩踏事故",false));
        typeData.add(new AccidentGridModel("交通事故",false));
        typeData.add(new AccidentGridModel("淹溺、灼烫",false));
        typeData.add(new AccidentGridModel("物体打击",false));
        typeData.add(new AccidentGridModel("机械伤害",false));
        typeData.add(new AccidentGridModel("化学伤害",false));
        typeData.add(new AccidentGridModel("其他",false));
    }

    private void initResultData(){
        resultData = new ArrayList<>();
        resultData.add(new AccidentGridModel("财产损失",false));
        resultData.add(new AccidentGridModel("受伤",false));
        resultData.add(new AccidentGridModel("死亡",false));
        resultData.add(new AccidentGridModel("群死群伤",false));
    }

    String[] items;
    private void showSingleChoiceDialog(String str, final int positon){
        QMUIDialog.CheckableDialogBuilder dialogBuilder = new QMUIDialog.CheckableDialogBuilder(getActivity());
        if (str.equals("财产损失") || str.equals("0-5万") || str.equals("5-10万") || str.equals("10-50万") || str.equals("50-100万") ||
                str.equals("100-500万") || str.equals("500万以上")){
            items = new String[]{"0-5万","5-10万","10-50万","50-100万","100-500万","500万以上"};
        }else if (str.equals("受伤") || str.equals("轻伤") || str.equals("重伤") || str.equals("致残")){
            items = new String[]{"轻伤","重伤","致残"};
        }else if (str.equals("死亡") || str.equals("1人死亡") || str.equals("1-3人死亡") || str.equals("3人以上死亡")){
            items = new String[]{"1人死亡","1-3人死亡","3人以上死亡"};
        }else {
            return;
        }
        dialogBuilder.addItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resultData.get(positon).setName(items[which]);
                resultAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        })
        .create(mCurrentDialogStyle).show();
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
                if (terget.equals("site")){
                    accidentSiteImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath,200,200));
                }else if (terget.equals("surroundings")){
                    accidentSurroundingsImg.setImageBitmap(PermissionUtils.getBitmapByPath(imagePath,200,200));
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
                Log.e("uri",">>>>>>>>>>...."+ PermissionUtils.getBitmapByPath(path, 480, 800));
                //绑定图片到Imageview
                if (terget.equals("site")){
                    accidentSiteImg.setImageBitmap(PermissionUtils.getBitmapByPath(path,200,200));
                }else if (terget.equals("surroundings")){
                    accidentSurroundingsImg.setImageBitmap(PermissionUtils.getBitmapByPath(path,200,200));
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

}
