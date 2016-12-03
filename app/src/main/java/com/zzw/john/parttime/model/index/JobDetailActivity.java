package com.zzw.john.parttime.model.index;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.zzw.john.parttime.R;
import com.zzw.john.parttime.base.MyApplication;
import com.zzw.john.parttime.bean.JobBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobDetailActivity extends AppCompatActivity {

    JobBean.JobListBean mJobListBean;
    @BindView(R.id.detail_title)
    TextView mDetailTitle;
    @BindView(R.id.btn_signup)
    BootstrapButton mBtnSignup;
    @BindView(R.id.btn_back)
    BootstrapButton mBtnBack;
    @BindView(R.id.activity_job_detail)
    LinearLayout mActivityJobDetail;
    @BindView(R.id.detail_money)
    TextView mDetailMoney;
    @BindView(R.id.detail_sex)
    TextView mDetailSex;
    @BindView(R.id.detail_type)
    TextView mDetailType;
    @BindView(R.id.detail_address)
    TextView mDetailAddress;
    @BindView(R.id.detail_request)
    TextView mDetailRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        ButterKnife.bind(this);

        initData();
        initView();

    }

    private void initView() {
        mDetailTitle.setText(mJobListBean.getName());
        mDetailMoney.setText(mJobListBean.getSalary());
        mDetailSex.setText(mJobListBean.getSex());
        mDetailType.setText(mJobListBean.getType());
        mDetailAddress.setText(mJobListBean.getAddress());
        mDetailRequest.setText(mJobListBean.getRemark());
    }


    private void initData() {
        mJobListBean = (JobBean.JobListBean) this.getIntent().getSerializableExtra("bean");
    }


    @OnClick({R.id.btn_signup, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                View submit_view = getLayoutInflater().inflate(R.layout.submit_sign, null);
                EditText phone = (EditText) submit_view.findViewById(R.id.et_phone_number);
                EditText school = (EditText) submit_view.findViewById(R.id.et_school);
                //                phone.setText(MyApplication.employerBean.);
                school.setText(MyApplication.employerBean.getSchoolName());
                new AlertDialog.Builder(this)
                        .setTitle("报名")
                        .setView(submit_view)
                        .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();

                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
