package com.retrofitdemo.presenter.bean;

/**
 * Created by WANGCPP on 2018/1/15.
 * 网络请求接收基类bean
 */
public class BaseReceptionBean {
    private String status;
    private String resTime;
    private String msg;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResTime() {
        return resTime;
    }

    public void setResTime(String resTime) {
        this.resTime = resTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
