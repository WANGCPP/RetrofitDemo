package com.retrofitdemo.view;

/**
 * Created by WANGCPP on 2018/1/18.
 * MainActivity view接口
 */
public interface IMainView {

    /**
     * 更新UI登录状态
     * @param status 登录状态
     */
    void updateLoginStatus(boolean status);

    /**
     * 更新UI下载进度
     * @param progress 当前下载进度
     */
    void updateDownloadProgress(int progress);
}
