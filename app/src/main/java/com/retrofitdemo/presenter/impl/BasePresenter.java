package com.retrofitdemo.presenter.impl;

import com.retrofitdemo.presenter.IBasePresenter;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by WANGCPP on 2018/1/19.
 * BasePresenter
 */
public abstract class BasePresenter<V> implements IBasePresenter<V> {

    private WeakReference<V> mViewRef = null;

    /**
     * 用于退出时主动解除所有Observe订阅关系
     */
    CompositeDisposable mCompositeDisposable = null;

    BasePresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onShow(V view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void onHide() {

        if (null != mCompositeDisposable && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose(); //主动解除所有订阅关系
            mCompositeDisposable = null;
        }

        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @Override
    public V getView() {
        return mViewRef.get();
    }
}
