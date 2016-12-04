package com.zzw.john.parttime.model.me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzw.john.parttime.R;
import com.zzw.john.parttime.base.MyApplication;
import com.zzw.john.parttime.bean.JobBean;
import com.zzw.john.parttime.componments.ApiClient;
import com.zzw.john.parttime.model.index.AllJobActivity;
import com.zzw.john.parttime.model.index.JobDetailActivity;
import com.zzw.john.parttime.service.Api;
import com.zzw.john.parttime.utils.UIUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class EnrollActivitiy extends AppCompatActivity {

    private ListView enrollLV;
    private ProgressDialog progressDialog;

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_enroll);

        api= ApiClient.getApi();

        initView();
        initData();

    }

    private void initView() {
        enrollLV=(ListView)findViewById(R.id.enrollLV);

        progressDialog=new ProgressDialog(EnrollActivitiy.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("请稍等");
        progressDialog.show();
    }

    private void initData() {
        System.out.println(MyApplication.employeeBean.getId());
        Observable<JobBean> jobBeanObservable = api.queryStatusRecordByEmployeeID(0,MyApplication.employeeBean.getId());
        jobBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e);
                        progressDialog.dismiss();
                        UIUtils.showToast("超时,请重试!");
                    }

                    @Override
                    public void onNext(JobBean jobBean) {
                        enrollLV.setAdapter(new EnrollListAdapter(jobBean.getJobList(),jobBean.getNameList(),jobBean.getStateList()));
                        progressDialog.dismiss();
                    }
                });
    }

    private class EnrollListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<JobBean.JobListBean> jobList;
        private List<String> nameList;
        private List<Integer> stateList;

        public EnrollListAdapter(List<JobBean.JobListBean> jobList,List<String> nameList,List<Integer> stateList){
            this.layoutInflater=LayoutInflater.from(UIUtils.getContext());
            this.jobList=jobList;
            this.nameList=nameList;
            this.stateList=stateList;
        }

        @Override
        public int getCount() {
            return jobList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView jobNameTV,jobTypeTV,employerTV,stateTV;
            Button detailBtn;

            final JobBean.JobListBean jobListBean;

            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_meenroll_item,null);
            }

            jobNameTV=(TextView)convertView.findViewById(R.id.jobNameTV);
            jobTypeTV=(TextView)convertView.findViewById(R.id.jobTypeTV);
            employerTV=(TextView)convertView.findViewById(R.id.employerTV);
            stateTV=(TextView)convertView.findViewById(R.id.stateTV);
            detailBtn=(Button)convertView.findViewById(R.id.detailBtn);

            jobListBean=jobList.get(position);

            jobNameTV.setText(jobListBean.getName());
            jobTypeTV.setText(jobListBean.getType());
            employerTV.setText(nameList.get(position));
            if (stateList.get(position)==0){
                stateTV.setText("待审核");
                stateTV.setBackgroundColor(Color.GRAY);
            }
            else if (stateList.get(position)==1){
                stateTV.setText("已同意");
                stateTV.setBackgroundColor(Color.GREEN);
            }
            else {
                stateTV.setText("已拒绝");
                stateTV.setBackgroundColor(Color.RED);
            }



            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent jobDetail = new Intent(EnrollActivitiy.this, JobDetailActivity.class);
                    jobDetail.putExtra("bean",jobListBean);
                    jobDetail.putExtra("from","EnrollActivity");
                    startActivity(jobDetail);
                }
            });

            return convertView;
        }
    }
}
