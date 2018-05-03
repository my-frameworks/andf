package com.and.framework.demo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.and.framework.R;
import com.and.framework.common.activity.BaseActivity;
import com.and.framework.common.widget.swipetoloadlayout.OnLoadMoreListener;
import com.and.framework.common.widget.swipetoloadlayout.OnRefreshListener;
import com.and.framework.common.widget.swipetoloadlayout.SwipeToLoadLayout;

import butterknife.BindView;

/**
 * Created by zhangyadong on 2018/5/3.
 */

public class TestSwipeActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutRes() {
        return R.layout.demo_tab_layout;
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
