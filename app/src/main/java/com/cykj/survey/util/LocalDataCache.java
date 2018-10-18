package com.cykj.survey.util;

import android.content.Context;
import android.util.Log;

import com.cykj.survey.SurveyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 本地数据缓存
 */
public class LocalDataCache {

    /**
     * 两个小时后为时间超时
     */
    public static final long CACHE_DURATION = 1000 * 60 * 60 * 2;
    private static MessageDigest messageDigest;


    /**
     * 获取缓存在本地的数据
     * @param fileName
     * @return
     */
    public static Object getLoaclData(Context context, String fileName) {

        Log.e("log", "开始从本地缓存获取数据");
        File path = context.getFilesDir();
        Log.e("log",path.getAbsolutePath());
        File file = new File(path + "/" + Md5(fileName)+".config");
        file.getAbsolutePath();
        Log.e("wxf", file.getAbsolutePath());
        if (file.exists()) {
            if (isValid(file)) {
                try {
                    Log.e("log", "从本地加载数据");
                    FileInputStream fin = new FileInputStream(file);
                    ObjectInputStream oin = new ObjectInputStream(fin);
                    Object object = oin.readObject();
                    oin.close();
                    return object;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("log", "获取数据异常" + e.toString());
                }
            }
        }
        return null;
    }

    /**
     * 保存数据
     * @param object   保存数据的对象
     * @param fileName 保存的文件名
     */
    public static void save(Context context,Object object,String fileName) {
        Log.e("log","开始缓存数据到本地");
        File path = context.getFilesDir();
        File file = new File(path + "/" + Md5(fileName) + ".config");
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("log","数据缓存异常" + e.toString());
        }
    }

    /**
     * MD5加密
     * @param message
     * @return
     */
    public static String Md5(String message){
        StringBuilder sb = new StringBuilder();
        try{
            //algorithm:加密的方式
            if (messageDigest == null ){
                messageDigest = MessageDigest.getInstance("MD5");
            }
            //1.将数据转化成byte数组，并对byte数组进行第一次加密，返回的就是加密的byte数组
            byte[] digest = messageDigest.digest(message.getBytes());

            for (int i = 0;i < digest.length; i++){
                //2.将加密过的byte数组的元素和255进行与运算
                //-128 -127
                int result = digest[i] & 0xff;
                //因为得到int类型的值，可能会比较打，将int类型的值转化成十六进制的字符串
                String hexString = Integer.toHexString(result);
                if (hexString.length() < 2){
                    sb.append("0");
                }
                sb.append(hexString);
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断该文件是否有效
     *
     * @param file
     * @return
     */
    private static boolean isValid(File file) {
        //文件存在了多长时间
        long existDuration = System.currentTimeMillis() - file.lastModified();
        return existDuration <= CACHE_DURATION;
    }

}
