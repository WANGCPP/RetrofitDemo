package com.retrofitdemo.repository;

/**
 * Created by WANGCPP on 2018/1/19.
 * 监听登录回调结果
 */
public interface LoginListener {
    /**
     * 登录成功
     */
    void onSucess();

    /**
     * 登录失败
     */
    void onFailure();
}