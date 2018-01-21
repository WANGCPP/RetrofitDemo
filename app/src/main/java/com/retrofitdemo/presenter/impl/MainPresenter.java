package com.retrofitdemo.presenter.impl;

import com.google.gson.Gson;
import com.retrofitdemo.presenter.IMainPresenter;
import com.retrofitdemo.presenter.bean.LoginRequestBean;
import com.retrofitdemo.repository.IMainRepository;
import com.retrofitdemo.repository.LoginListener;
import com.retrofitdemo.view.IMainView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WANGCPP on 2018/1/18.
 * MainPresenter
 */
public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter<IMainView> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private String mRequestString = null;

    private IMainRepository mRepository = null;

    public MainPresenter(IMainRepository repository) {
        super();
        this.mRepository = repository;
    }

    @Override
    public void onLogin() {
        initLoginBean();
        mRepository.setOnLoginListener(new LoginListener() {
            @Override
            public void onSucess() {
                getView().updateLoginStatus(true);
            }

            @Override
            public void onFailure() {
                getView().updateLoginStatus(false);
            }
        });
        mRepository.login(mRequestString);
    }

    @Override
    public void onDownload() {
        mRepository.setOnProgressChangeListener()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        mCompositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(Integer progress) {
                        if (disposable.isDisposed()) { //如果解除则不需处理
                            return;
                        }
                        getView().updateDownloadProgress(progress);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
        mRepository.download();
    }


    /**
     * 初始化登录的LoginRequestBean
     */
    private void initLoginBean() {
        LoginRequestBean loginRequestBean;
        loginRequestBean = new LoginRequestBean();
        loginRequestBean.setAlfuscode("");
        loginRequestBean.setAppVersion("0.8");
        loginRequestBean.setReqTime("20180114215322");
        Gson gson = new Gson();
        mRequestString = gson.toJson(loginRequestBean);
    }
}
