package com.and.framework.demo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.and.framework.R;
import com.and.framework.common.fragment.BaseFragment;
import com.and.framework.demo.TestWebView;

import org.w3c.dom.Text;

import butterknife.BindView;

/**
 * Created by lwp940118 on 2016/11/25.
 */
public class Fragment1 extends BaseFragment {

    @BindView(R.id.test)
    TextView testTex;

    @Override
    protected int inflateContentView() {
        return R.layout.fragement1;
    }

    @Override
    protected void onInitializeView(View view) {
        testTex.setText("xxxxxx");
        testTex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchScreen(TestWebView.class);
            }
        });
    }
}