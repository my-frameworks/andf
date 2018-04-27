package com.and.framework.common.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.and.framework.common.R;


/**
 * Created by user on 2017/7/21.
 */

public class LoadingProgressDialog extends ProgressDialog {

    private TextView contentTv;

    public LoadingProgressDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    public LoadingProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultProgressSetting();
        initView();
    }

    private void defaultProgressSetting() {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    private void initView() {
        setContentView(R.layout.view_progress_dialog_layout);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        contentTv = (TextView)findViewById(R.id.tv_load_dialog);
    }

    protected void setMessage(String message){
        contentTv.setText(message);
    }

}
