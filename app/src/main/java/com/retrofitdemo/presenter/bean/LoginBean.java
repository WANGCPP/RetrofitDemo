package com.retrofitdemo.presenter.bean;

import java.util.List;

/**
 * Created by WANGCPP on 2018/1/15.
 * 接收类bean：LoginBean
 */
public class LoginBean extends BaseReceptionBean {

    private String token;
    private String sum;
    // 是否需要更新
    private String updateflag;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getUpdateflag() {
        return updateflag;
    }

    public void setUpdateflag(String updateflag) {
        this.updateflag = updateflag;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVerId() {
        return verId;
    }

    public void setVerId(String verId) {
        this.verId = verId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppIntro() {
        return appIntro;
    }

    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCarPackage() {
        return carPackage;
    }

    public void setCarPackage(String carPackage) {
        this.carPackage = carPackage;
    }

    public String getCarMain() {
        return carMain;
    }

    public void setCarMain(String carMain) {
        this.carMain = carMain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ResultBean> getResults() {
        return results;
    }

    public void setResults(List<ResultBean> results) {
        this.results = results;
    }

    // 应用id
    private String appId;
    // 版本id
    private String verId;
    // 版本号
    private String appVersion;
    // 名称
    private String name;
    // 应用介绍
    private String appIntro;
    // 图标连接
    private String icon;
    // 机车应用包名
    private String carPackage;
    // 机车应用主activity名
    private String carMain;
    // 应用下载地址
    private String url;
    // 返回类别列表
    private List<ResultBean> results;

    public class ResultBean {
        private String typeId;
        private String type;

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
