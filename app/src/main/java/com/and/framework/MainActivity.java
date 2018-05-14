package com.and.framework;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.and.framework.common.activity.ToolbarConfigurator;
import com.and.framework.common.listener.ProgressListener;
import com.and.framework.common.component.ImageLoaderClient;
import com.and.framework.common.activity.BaseActivity;
import com.and.framework.common.utils.DownloadUtils;
import com.and.framework.common.utils.FileUtils;
import com.and.framework.common.utils.ToastUtils;
import com.and.framework.demo.TestBaseActivity;
import com.and.framework.demo.TestTabActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;



public class MainActivity extends BaseActivity<MainPresenter> implements MainView{
    private ImageView imageView;
    private String url = "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fa324a79d792510fb30f240821.jpg";

    @BindView(R.id.baseActivity)
    Button btnBaseActivity;
    @BindView(R.id.baseFragment)
    Button btnBaseFragment;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated() {
        setToolbarVisible(true);
        imageView = (ImageView) findViewById(R.id.image_view);
        mPresenter.attachView(this);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }

    @OnClick({R.id.baseActivity, R.id.baseFragment})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.baseActivity:
                launchScreen(TestBaseActivity.class);
                break;
            case R.id.baseFragment:
                launchScreen(TestTabActivity.class);
                break;
        }
    }

    @OnClick(R.id.upload_file)
    void UploadFile(){



        File uploadFile = new File("/storage/emulated/0/QQReader/PlugInImg/11_3_c.png");


        DownloadUtils.getInstance().uploadFile("http://1192.168.200.234:8080/web/UploadServlet",uploadFile);
    }

    @OnClick(R.id.btn_download)
    public void downFile() {
        DownloadUtils.getInstance().downloadFile(url, FileUtils.getApkPath(this, "zyy.jpg"));
    }

    ProgressListener progressListener = new ProgressListener() {
        @Override
        public void onProgress(long progress, long total, boolean done) {

        }
    };

    @Override
    protected void onInterceptMenuCreation(ToolbarConfigurator configurator) {
        super.onInterceptMenuCreation(configurator);
        configurator.setTitle("首页");
    }


    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(this,message);
    }

    @OnClick(R.id.show_image)
    void showImage(){
        mPresenter.getData();
        ImageLoaderClient.get().load(this, url, imageView);
    }
}
