package com.cykj.survey.activity.power;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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

import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.activity.property.ProertyDetailActivirt;
import com.cykj.survey.base.BaseFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HydroDetailActivirty extends BaseFragmentActivity {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.report_web)
    WebView reportWeb;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    private String hydroId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        hydroId = intent.getStringExtra("id");
        initTop();
        initView();
    }

    private void initTop() {
        topbar.setTitle("报告详情");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topbar.addRightTextButton("下载",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = Constants.TEST_SERVICE + "property/download?id=" +propertyId;
//                Uri uri = Uri.parse(url);
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
            }
        });
    }

    /**
     *WebViewClient主要帮助WebView处理各种通知、请求事件
     */
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
                Toast.makeText(HydroDetailActivirty.this, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
                //表示我已经处理过了
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    private void initView(){
        reportWeb.loadUrl(Constants.TEST_SERVICE + "/hydro/showHydro?id=" + hydroId);
        reportWeb.setWebChromeClient(webChromeClient);
        reportWeb.setWebViewClient(webViewClient);

        WebSettings webSettings = reportWeb.getSettings();

        //允许使用js
        webSettings.setJavaScriptEnabled(true);

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        //不使用缓存，只从网络获取数据.
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    /**
     * WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
     */
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
     * 仍然必不可少
     * @param str
     * @return
     */
    @JavascriptInterface
    public void getClient(String str) {

    }
}