package com.retrofitdemo.presenter;


/**
 * Created by WANGCPP on 2018/1/18.
 * MainPresenter 接口
 */
public interface IMainPresenter<V> extends IBasePresenter<V> {

    /**
     * 登录
     */
    void onLogin();

    /**
     * 下载
     */
    void onDownload();
}
