package com.and.framework.common.widget.swipetoloadlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.and.framework.common.R;
import com.and.framework.common.utils.CollectionUtils;
import com.and.framework.common.widget.BaseFrameLayout;

import java.util.Collection;

/**
 * Created by zyd on 2017/7/25.
 */

public class SwipeRecyclerView extends BaseFrameLayout implements OnLoadMoreListener, OnRefreshListener{

    private static final int LIST_PAGE_SIZE = 20;

    private SwipeToLoadLayout mSwipeToLoadLayout;
    private RecyclerView mRecyclerView;

    private int mPageSize = LIST_PAGE_SIZE;
    private int mPageNumber = 1;

    private OnRefreshCallback onRefreshCallback;

    public interface OnRefreshCallback{
        void onShouldRefreshData(final SwipeRecyclerView view, final int pageNumber, final int pageSize);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecyclerView(Context context) {
        super(context);
    }

    @Override
    protected int getInflatedLayout() {
        return R.layout.swipe_to_load_layout_recyclerview;
    }

    @Override
    protected void onInitializeCompleted(View parentView) {
        mSwipeToLoadLayout=(SwipeToLoadLayout)parentView.findViewById(R.id.swipeToLoadLayout);
        mRecyclerView=(RecyclerView)parentView.findViewById(R.id.swipe_target);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        this.mPageNumber = 1;

        if (null != onRefreshCallback){
            onRefreshCallback.onShouldRefreshData(this, mPageNumber, mPageSize);
        }
    }

    @Override
    public void onLoadMore() {
        mPageNumber ++;

        if (null != onRefreshCallback){
            onRefreshCallback.onShouldRefreshData(this, mPageNumber, mPageSize);
        }
    }

    public void initRecyclerView(){

    }


    public void notifyDataFetched(final int pageNumber, final Collection<?> data) {
        if (pageNumber == 1) {                  // Indicates this is a pull down to refresh action.
            mSwipeToLoadLayout.setRefreshing(false);
        } else {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
        final boolean hasNextPage = CollectionUtils.isValidate(data) && data.size() >= mPageSize;
        mSwipeToLoadLayout.setIsLoadMoreEnd(!hasNextPage);
    }

    public void notifyErrorOccurred(final int pageNumber) {
        if (pageNumber == 1) {                  // Indicates this is a pull down to refresh action.
            mSwipeToLoadLayout.setRefreshing(false);
        } else {
            mPageNumber --;
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }

    public void startDataRequest(){

    }

    public void setOnRefreshCallback(OnRefreshCallback onRefreshCallback) {
        this.onRefreshCallback = onRefreshCallback;
    }

    public int getPageNumber() {
        return mPageNumber;
    }

    public void setPageSize(int pageSize) {
        this.mPageSize = pageSize;
    }

    public int getPageSize() {
        return mPageSize;
    }

}
