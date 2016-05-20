package com.ychen.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ychen.tourism.R;
import com.ychen.tourism.helper.ContantsUtil;
import com.ychen.tourism.widget.MyProgressBar;

/**
 * Created by evast on 16-3-21.
 */
public class PoiDetailActivity extends BaseActivity{
    private WebView mWebView;
    private MyProgressBar mProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    /**
     * 初始化
     */
    protected void init(){
        Intent intent = getIntent();
        String url = intent.getStringExtra(ContantsUtil.URL_KEY);
        mProgressBar = (MyProgressBar) findViewById(R.id.progressbar);
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        Log.e("--main--","url:"+url);
        //加载需要显示的网页
        mWebView.loadUrl("http://www.baidu.com");
        //设置Web视图    不让其跳转到浏览器
        mWebView.setWebViewClient(new webViewClient());
        /** 显示进度条*/
        setLoaderProgressBar();
    }

    /**
     * 设置加载进度条
     */
    private void setLoaderProgressBar(){
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                mProgressBar.setProgress(progress);
                if (progress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }


    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
