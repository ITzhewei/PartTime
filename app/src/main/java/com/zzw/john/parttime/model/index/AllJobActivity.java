package com.zzw.john.parttime.model.index;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lzyzsd.randomcolor.RandomColor;
import com.orhanobut.logger.Logger;
import com.zzw.john.parttime.R;
import com.zzw.john.parttime.bean.JobBean;
import com.zzw.john.parttime.componments.ApiClient;
import com.zzw.john.parttime.service.Api;
import com.zzw.john.parttime.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AllJobActivity extends AppCompatActivity {

    @BindView(R.id.rl_alljob)
    RecyclerView mRlAlljob;

    Api mApi;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job);
        ButterKnife.bind(this);
        mApi = ApiClient.getApi();
        initView();
        initData();
    }

    private void initData() {
        Observable<JobBean> jobBeanObservable = mApi.queryAllTypeJob();
        jobBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e);
                        UIUtils.showToast("超时,请重试!");
                    }

                    @Override
                    public void onNext(JobBean jobBean) {
                        List<JobBean.JobListBean> jobList = jobBean.getJobList();
                        //设置
                        mRlAlljob.setAdapter(new MyAdapter(jobList));
                        mProgressDialog.dismiss();
                    }
                });
    }


    private void initView() {
        mRlAlljob.setLayoutManager(new LinearLayoutManager(this));
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
    }


    //adapter
    private class MyAdapter extends RecyclerView.Adapter {

        List<JobBean.JobListBean> jobList;

        public MyAdapter(List<JobBean.JobListBean> jobList) {
            this.jobList = jobList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.list_job_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindView(jobList.get(position));
        }

        @Override
        public int getItemCount() {
            return jobList.size();
        }

        private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView mTvName;
            TextView mTvSalay;
            TextView mTvAddress;

            JobBean.JobListBean mJobListBean;

            public MyHolder(View itemView) {
                super(itemView);
                mTvName = (TextView) itemView.findViewById(R.id.tv_name);
                mTvSalay = (TextView) itemView.findViewById(R.id.tv_salay);
                mTvAddress = (TextView) itemView.findViewById(R.id.tv_address);
                ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_bg);
                imageView.setBackgroundColor(new RandomColor().randomColor());
            }

            public void bindView(JobBean.JobListBean jobListBean) {
                mJobListBean = jobListBean;
                mTvName.setText(jobListBean.getName());
                mTvSalay.setText(jobListBean.getSalary());
                mTvAddress.setText(jobListBean.getAddress());
            }

            @Override
            public void onClick(View v) {
                Intent jobDetail = new Intent(AllJobActivity.this, JobDetailActivity.class);
                jobDetail.putExtra("bean", mJobListBean);
                jobDetail.putExtra("from", "AllJobActivity");
                startActivity(jobDetail);
            }
        }
    }
}
