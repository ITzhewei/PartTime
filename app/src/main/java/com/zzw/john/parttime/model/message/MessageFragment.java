package com.zzw.john.parttime.model.message;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.lzyzsd.randomcolor.RandomColor;
import com.orhanobut.logger.Logger;
import com.zzw.john.parttime.R;
import com.zzw.john.parttime.base.MyApplication;
import com.zzw.john.parttime.bean.JobBean;
import com.zzw.john.parttime.componments.ApiClient;
import com.zzw.john.parttime.model.index.JobDetailActivity;
import com.zzw.john.parttime.service.Api;
import com.zzw.john.parttime.utils.UIUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by john on 2016/11/1.
 */

public class MessageFragment extends Fragment {

    private ListView messageLV;
    private ScrollView messageSV;
    private SwipeRefreshLayout messageSRLO;
    private ProgressDialog progressDialog;

    private Api api;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);

        api= ApiClient.getApi();

        initView(view);
        initData(0);

        return view;
    }

    private void initView(View view) {
        messageLV=(ListView)view.findViewById(R.id.messageLV);
        messageSV=(ScrollView)view.findViewById(R.id.messageSV);
        messageSRLO=(SwipeRefreshLayout)view.findViewById(R.id.messageSRLO);

        messageLV.setOnScrollListener(new SwipeListViewOnScrollListener(messageSRLO, new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        }));

        messageSRLO.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(1);
            }
        });

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("请稍等");
        progressDialog.show();
    }

    private void initData(final int type) {
        Observable<JobBean> jobBeanObservable = api.queryStatusRecordByEmployeeID(1,MyApplication.employeeBean.getId());
        jobBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (type==0)
                            progressDialog.dismiss();
                        else
                            refreshHandler.sendEmptyMessage(0);
                        Logger.d(e);
                        UIUtils.showToast("超时,请重试!");
                    }

                    @Override
                    public void onNext(JobBean jobBean) {
                        if (type==0)
                            progressDialog.dismiss();
                        else
                            refreshHandler.sendEmptyMessage(0);
                        messageLV.setAdapter(new MessageListAdapter(jobBean.getJobList(),jobBean.getNameList(),jobBean.getStateList()));
                    }
                });
    }

    public Handler refreshHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                messageSRLO.setRefreshing(false);
            }
        }
    };

    //适配器
    private class MessageListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<JobBean.JobListBean> jobList;
        private List<String> nameList;
        private List<Integer> stateList;

        public MessageListAdapter(List<JobBean.JobListBean> jobList,List<String> nameList,List<Integer> stateList){
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
            ImageView employerIV;

            final JobBean.JobListBean jobListBean;

            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_meenroll_item,null);
            }

            jobNameTV=(TextView)convertView.findViewById(R.id.jobNameTV);
            jobTypeTV=(TextView)convertView.findViewById(R.id.jobTypeTV);
            employerTV=(TextView)convertView.findViewById(R.id.employerTV);
            stateTV=(TextView)convertView.findViewById(R.id.stateTV);
            detailBtn=(Button)convertView.findViewById(R.id.detailBtn);
            employerIV=(ImageView)convertView.findViewById(R.id.employerIV);

            employerIV.setBackgroundColor(new RandomColor().randomColor());

            jobListBean=jobList.get(position);

            jobNameTV.setText(jobListBean.getName());
            jobTypeTV.setText(jobListBean.getType());
            employerTV.setText(nameList.get(position));
            if (stateList.get(position)==1){
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
                    Intent jobDetail = new Intent(getActivity(), JobDetailActivity.class);
                    jobDetail.putExtra("bean",jobListBean);
                    jobDetail.putExtra("from","MessageFragment");
                    startActivity(jobDetail);
                }
            });

            return convertView;
        }
    }

    private static class SwipeListViewOnScrollListener implements AbsListView.OnScrollListener{

        private SwipeRefreshLayout mSwipeView;
        private AbsListView.OnScrollListener mOnScrollListener;

        public SwipeListViewOnScrollListener(SwipeRefreshLayout swipeView){
            mSwipeView=swipeView;
        }

        public SwipeListViewOnScrollListener(SwipeRefreshLayout swipeView,AbsListView.OnScrollListener onScrollListener){
            mSwipeView=swipeView;
            mOnScrollListener=onScrollListener;
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View firstView = view.getChildAt(firstVisibleItem);

            if(firstVisibleItem==0&&(firstView==null||firstView.getTop()==0))
                mSwipeView.setEnabled(true);
            else
                mSwipeView.setEnabled(false);

            if(null!=mOnScrollListener)
                mOnScrollListener.onScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
        }
    }
}
