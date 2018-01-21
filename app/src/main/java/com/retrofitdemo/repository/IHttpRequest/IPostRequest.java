package com.retrofitdemo.repository.IHttpRequest;

import com.retrofitdemo.presenter.bean.LoginBean;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.retrofitdemo.presenter.entity.ServerURL.URL_LOGIN;

/**
 * Created by WANGCPP on 2018/1/15.
 * post请求接口
 */
public interface IPostRequest {

    @Headers({"Content-Type: application/json", "Accept: application/json"}) //需要添加头
    @POST(URL_LOGIN)
    Observable<LoginBean> postLoginRequest(@Body RequestBody requestBody); //传入的参数为RequestBody
}
