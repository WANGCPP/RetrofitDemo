package com.retrofitdemo.presenter.bean;

/**
 * Created by WANGCPP on 2018/1/15.
 * 请求类bean:登录请求bean
 */
public class LoginRequestBean {

    private String reqTime = null;

    private String alfuscode = null;

    private String appVersion = null;

    public String getReqTime() {
        return this.reqTime;
    }

    public String getAlfuscode() {
        return this.alfuscode;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public void setAlfuscode(String alfuscode) {
        this.alfuscode = alfuscode;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
