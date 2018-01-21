package com.retrofitdemo.repository;

import com.retrofitdemo.repository.impl.MainRepository;

import io.reactivex.Observable;

/**
 * Created by WANGCPP on 2018/1/18.
 * IMainRepository
 */
public interface IMainRepository {

    /**
     * 登录请求
     */
    void login(String loginJsonString);

    /**
     * 登录结果观察接口
     */
    void setOnLoginListener(LoginListener listener);

    /**
     * 下载
     */
    void download();

    /**
     * 下载进度监听回调接口
     *
     * @return 已下载进度
     */
    Observable<Integer> setOnProgressChangeListener();
}
