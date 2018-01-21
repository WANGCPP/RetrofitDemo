package com.retrofitdemo.repository.impl;

import android.util.Log;

import com.retrofitdemo.presenter.bean.LoginBean;
import com.retrofitdemo.repository.IMainRepository;
import com.retrofitdemo.repository.IHttpRequest.IDownRequest;
import com.retrofitdemo.repository.IHttpRequest.IPostRequest;
import com.retrofitdemo.repository.LoginListener;
import com.retrofitdemo.repository.download.ProgressListener;
import com.retrofitdemo.repository.download.ProgressResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.retrofitdemo.presenter.entity.ServerURL.URL_BASE;
import static com.retrofitdemo.presenter.entity.ServerURL.URL_BASE_DOWNLOAD;

/**
 * Created by WANGCPP on 2018/1/18.
 * MainRepository
 */
public class MainRepository implements IMainRepository {

    private static final String TAG = MainRepository.class.getSimpleName();

    private LoginListener mLoginListener = null;

    private PListener mPListener = null;


    @Override
    public void login(String loginJsonString) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        IPostRequest iPostRequest = retrofit.create(IPostRequest.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), loginJsonString);

        iPostRequest.postLoginRequest(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe()");
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.d(TAG, "onNext()");
                        mLoginListener.onSucess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError()" + e.getMessage());
                        mLoginListener.onFailure();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }
                });
    }

    @Override
    public void setOnLoginListener(LoginListener listener) {
        mLoginListener = listener;
    }


    @Override
    public void download() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(URL_BASE_DOWNLOAD);

        OkHttpClient client = new OkHttpClient.Builder() //设置拦截器
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response orginalResponse = chain.proceed(chain.request());

                        return orginalResponse.newBuilder()
                                .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                                    int progress;

                                    @Override
                                    public void onProgress(long current, long total, boolean done) { //子线程中回调
                                        if (null != mPListener) {
                                            progress = (int) (current * 100 / total);
                                            mPListener.onLoading(progress);
                                            if (done) {
                                                Log.d(TAG, "Done");
                                                mPListener.onFinished();

                                            }
                                        }
                                    }
                                }))
                                .build();
                    }
                })
                .build();

        final IDownRequest iDownRequest = builder.client(client).build().create(IDownRequest.class);

        new Thread() {
            @Override
            public void run() {
                Call<ResponseBody> call = iDownRequest.downloadWithUrl("bytes=0-"); //从头下载

                try {
                    Response<ResponseBody> response = call.execute(); //同步请求
                    if (response.isSuccessful()) {
                        Log.d(TAG, "server contacted and has file");
                        boolean status = writeResponseBodyToDisk(response.body());
                    } else {
                        Log.d(TAG, "server contact failed");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public Observable<Integer> setOnProgressChangeListener() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                mPListener = new PListener() {
                    @Override
                    public void onLoading(int progress) {
                        emitter.onNext(progress);
                    }

                    @Override
                    public void onFinished() {
                        emitter.onComplete();
                    }
                };
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // 目标存储路径
            File targetFile = new File("/sdcard/Download/test.apk");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(targetFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 监听下载进度回调
     */
    public interface PListener {

        /**
         * onLoading
         *
         * @param progress 当前进度
         */
        void onLoading(int progress);

        /**
         * 下载完成
         */
        void onFinished();

    }
}
