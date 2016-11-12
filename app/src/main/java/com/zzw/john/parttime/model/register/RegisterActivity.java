package com.zzw.john.parttime.model.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.zzw.john.parttime.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john on 2016/11/1.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_username)
    BootstrapEditText mEtUsername;
    @BindView(R.id.et_password)
    BootstrapEditText mEtPassword;
    @BindView(R.id.btn_register)
    BootstrapButton mBtnRegister;
    @BindView(R.id.btn_back)
    BootstrapButton mBtnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
}
