package com.and.framework.common.fragment;

/**
 * Created by user on 2017/11/15.
 */

public abstract class LazyFragment extends RxFragment {
    private boolean mIsVisible;//是否显示
    private boolean mIsPrepared;//是否加载组件完毕
    private boolean mIsFirstInit;//是否第一次加载

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        if (!mIsPrepared || !mIsVisible || mIsFirstInit) {
            return;
        }
        lazyLoad();
        mIsFirstInit = true;
    }

    protected void lazyLoad() {
    }

    protected void onInvisible() {
    }

    public boolean getVisibleStatus() {
        return mIsVisible;
    }

    public boolean getPreparedStatus() {
        return mIsPrepared;
    }

    //是否加载组件完毕
    public void setPreparedStatus(boolean mIsPrepared) {
        this.mIsPrepared = mIsPrepared;
    }

    public boolean getFirstInitStatus() {
        return mIsFirstInit;
    }

    //是否第一次加载
    public void setFirstInitStatus(boolean mIsFirstInit) {
        this.mIsFirstInit = mIsFirstInit;
    }

}
