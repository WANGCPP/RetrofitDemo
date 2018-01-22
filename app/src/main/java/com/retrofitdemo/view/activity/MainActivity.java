package com.retrofitdemo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.retrofitdemo.R;
import com.retrofitdemo.presenter.IMainPresenter;
import com.retrofitdemo.presenter.impl.MainPresenter;
import com.retrofitdemo.repository.impl.MainRepository;
import com.retrofitdemo.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private IMainPresenter<IMainView> mPresenter = null;

    private Button btnDownload_nz = null;

    private Button btnDownload_wy = null;

    private TextView tvCenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(new MainRepository());
        initView();
        initEvent();

        mPresenter.onLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onShow(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onHide();
    }

    private void initView() {
        tvCenter = findViewById(R.id.tv_center);
        btnDownload_nz = findViewById(R.id.btn_download_nz);
        btnDownload_wy = findViewById(R.id.btn_download_wy);
    }

    private void initEvent() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_download_nz:
                        mPresenter.onDownload();
                        break;
                    case R.id.btn_download_wy:
                        mPresenter.onDownload();
                        break;
                    default:
                        break;
                }
            }
        };

        btnDownload_nz.setOnClickListener(clickListener);
        btnDownload_wy.setOnClickListener(clickListener);
    }

    @Override
    public void updateLoginStatus(boolean status) {
        if (status) {
            tvCenter.setText("登录成功");
        } else {
            tvCenter.setText("登录失败");
        }
    }

    @Override
    public void updateDownloadProgress(int progress) {
        tvCenter.setText("progress == " + progress + " %");
    }
}
