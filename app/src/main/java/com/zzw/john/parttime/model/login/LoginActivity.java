package com.zzw.john.parttime.model.login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.zzw.john.parttime.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john on 2016/11/1.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_username)
    BootstrapEditText mEtUsername;
    @BindView(R.id.et_password)
    BootstrapEditText mEtPassword;
    @BindView(R.id.btn_login)
    BootstrapButton mBtnLogin;
    @BindView(R.id.btn_register)
    BootstrapButton mBtnRegister;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
