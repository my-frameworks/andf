package com.and.framework.demo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.and.framework.R;
import com.and.framework.common.fragment.BaseFragment;
import com.and.framework.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangyadong on 2018/4/27.
 */

public class TestBaseFragment extends BaseFragment {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int inflateContentView() {
        return R.layout.demo_base_fragment_layout;
    }

    @Override
    protected void onInitializeView(View view) {
        text.setText("Hello@BaseFragment");
    }

    @OnClick(R.id.button)
    public void click() {
        ToastUtils.showToast(getContext(), "xxxxx");
    }


}
