package com.and.framework.demo;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.and.framework.R;
import com.and.framework.common.activity.BaseActivity;
import com.orhanobut.logger.Logger;


/**
 * Created by zhangyadong on 2018/5/14.
 */

public class TestWebView extends BaseActivity {

    private WebView webview;

    @Override
    protected int getLayoutRes() {
        return R.layout.demo_webview;
    }

    @Override
    protected void onViewCreated() {
        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Logger.d("onPageFinished="+url);
            }
        });
    }

    @Override
    protected void initEventAndData(Bundle bundle) {
        webview.loadUrl("https://www.baidu.com");
    }




}
