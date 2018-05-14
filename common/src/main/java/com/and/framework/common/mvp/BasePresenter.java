package com.and.framework.common.mvp;

/**
 * Created by zyd on 2018/4/28.
 */

public class BasePresenter<V extends BaseView>{


    protected V mvpView;


    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        this.mvpView = null;
    }

    public boolean isViewAttached(){
        return this.mvpView != null;
    }

    public V getView(){
        return mvpView;
    }

}
