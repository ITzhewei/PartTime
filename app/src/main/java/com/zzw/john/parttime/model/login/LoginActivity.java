package com.zzw.john.parttime.model.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzw.john.parttime.MainActivity;
import com.zzw.john.parttime.R;
import com.zzw.john.parttime.base.MyApplication;
import com.zzw.john.parttime.bean.EmployeeBeanAll;
import com.zzw.john.parttime.componments.ApiClient;
import com.zzw.john.parttime.model.register.RegisterActivity;
import com.zzw.john.parttime.service.Api;
import com.zzw.john.parttime.utils.ShareP;
import com.zzw.john.parttime.utils.UIUtils;
import com.zzw.john.parttime.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by john on 2016/11/1.
 */

public class LoginActivity extends AppCompatActivity {


    Api api;
    @BindView(R.id.et_username)
    ClearEditText mEtUsername;
    @BindView(R.id.et_password)
    ClearEditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    TextView mBtnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        api = ApiClient.getApi();
        initView();
    }

    private void initView() {
        mEtUsername.setText(ShareP.getString("nickname"));
        mEtPassword.setText(ShareP.getString("password"));
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String nickname = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(nickname) && TextUtils.isEmpty(password)) {
                    UIUtils.showToast("不能为空,请重新输入");
                    break;
                } else {
                    ShareP.setString("nickname", nickname);
                    ShareP.setString("password", password);
                    Observable<EmployeeBeanAll> register = api.login(nickname, password);
                    register.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<EmployeeBeanAll>() {
                                @Override
                                public void onCompleted() {


                                }

                                @Override
                                public void onError(Throwable e) {
                                    Logger.d(e);
                                    UIUtils.showToast("超时,请重试!");
                                }

                                @Override
                                public void onNext(EmployeeBeanAll employeeBeanAll) {
                                    String flag = employeeBeanAll.getFlag();
                                    if (flag.equals("true")) {
                                        //保存个人信息
                                        MyApplication.employeeBean = employeeBeanAll.getEmployee();
                                        //跳转主页
                                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(main);
                                    } else {
                                        UIUtils.showToast("登录失败,请重试");
                                    }
                                }
                            });
                }

                break;
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
