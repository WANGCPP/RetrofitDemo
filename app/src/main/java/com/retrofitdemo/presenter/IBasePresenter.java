package com.retrofitdemo.presenter;

/**
 * Created by WANGCPP on 2018/1/19.
 * Presenter Base接口
 */
public interface IBasePresenter<V> {

    /**
     * view show
     */
    void onShow(V view);

    /**
     * view hide
     */
    void onHide();

    /**
     * 获取view
     *
     * @return 返回view的弱引用
     */
    V getView();

}
