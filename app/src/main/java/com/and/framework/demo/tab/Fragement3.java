package com.and.framework.demo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.and.framework.R;
import com.and.framework.common.fragment.BaseFragment;

/**
 * Created by lwp940118 on 2016/11/25.
 */
public class Fragement3 extends BaseFragment {

    private View rootView;

    @Override
    protected int inflateContentView() {
        return R.layout.fragment3;
    }

    @Override
    protected void onInitializeView(View view) {

    }
}