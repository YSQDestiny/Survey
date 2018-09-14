package com.cykj.survey.activity.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cykj.survey.Constants;
import com.cykj.survey.MainActivity;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.model.ResultModel;
import com.cykj.survey.model.WeatherInfo;
import com.cykj.survey.model.WeatherModel;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WeatherChartActivity extends BaseFragmentActivity {

    @BindView(R.id.weather_web)
    WebView weatherWeb;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private String[] mMonths = new String[]{
            "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
    };

    private List<WeatherInfo> weatherInfoList = new ArrayList<>();

    @BindView(R.id.topbar)
    QMUITopBar topbar;


    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_chart);
        ButterKnife.bind(this);
        handler = new Handler();
        initTopbar();
        initView();


    }

    private void initView() {
        weatherWeb.loadUrl(Constants.TEST_SERVICE + "/project/showCharts?projectId=" + Constants.PROJECT_ID);
        weatherWeb.setWebChromeClient(webChromeClient);
        weatherWeb.setWebViewClient(webViewClient);


        WebSettings webSettings = weatherWeb.getSettings();

        webSettings.setJavaScriptEnabled(true);//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }


    private void initTopbar() {
        topbar.setTitle("历史气温");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherChartActivity.this, ProjectAccidentActivity.class);
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


    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            progressbar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen", "拦截url:" + url);
            if (url.equals("http://www.google.com/")) {
                Toast.makeText(WeatherChartActivity.this, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen", "网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressbar.setProgress(newProgress);
        }
    };

    /**
     * JS调用android的方法
     *
     * @param str
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void getClient(String str) {
        Log.i("ansen", "html调用客户端:" + str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        weatherWeb.destroy();
        weatherWeb = null;
    }

    private String dataStr;
    private String[] dataSub;

    private void getData(String pinyin, final String hanzi) {

        String url = "http://www.nmc.cn/publish/forecast/ASC/" + pinyin + ".html";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String str = response.body().string();
            Document document = Jsoup.parse(str);
            Elements elements = document.getElementsByTag("script");
            Element element = elements.get(7);
            String temp = element.data().toString();
            String substring = temp.substring(temp.indexOf("climate("), temp.indexOf("});"));
            dataStr = substring.substring(8, 223);
            dataSub = dataStr.split("],");

            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setName(hanzi);

            String highWeather = dataSub[0] + "]";
            weatherInfo.setHighWeather(highWeather);

            String lowWeather = dataSub[1] + "]";
            weatherInfo.setLowWeather(lowWeather);

            if (dataSub[2].indexOf("]") == -1) {
                weatherInfo.setRainfall(dataSub[2] + "]");
            } else {
                weatherInfo.setRainfall(dataSub[2]);
            }

            weatherInfoList.add(weatherInfo);
            dataStr = null;
            dataSub = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//
//        });
    }
}
