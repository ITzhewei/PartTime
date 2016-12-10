package com.zzw.john.parttime.model.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.zzw.john.parttime.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by john on 2016/11/1.
 */

public class IndexFragment extends Fragment {

    //图片
    Integer[] images = new Integer[]{
            R.mipmap.h28,
            R.mipmap.h29,
            R.mipmap.h30
    };
    //设置图片标题
    String[] titles = new String[]{
            "h28",
            "h29",
            "h30",
    };
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.img_t1)
    ImageView mImgT1;
    @BindView(R.id.img_t2)
    ImageView mImgT2;
    @BindView(R.id.img_t3)
    ImageView mImgT3;
    @BindView(R.id.img_t4)
    ImageView mImgT4;
    @BindView(R.id.img_t_left)
    ImageView mImgTLeft;
    @BindView(R.id.img_t_right_1)
    ImageView mImgTRight1;
    @BindView(R.id.img_t_right_2)
    ImageView mImgTRight2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        ButterKnife.bind(this, view);

        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        mBanner.setIndicatorGravity(Banner.CENTER);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(2000);
        mBanner.setImages(images);

        return view;
    }

    @OnClick({R.id.img_t1, R.id.img_t2, R.id.img_t3, R.id.img_t4, R.id.img_t_left, R.id.img_t_right_1, R.id.img_t_right_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_t1:
                Intent alljob = new Intent(getActivity(), AllJobActivity.class);
                startActivity(alljob);
                break;
            case R.id.img_t2:
                Intent goodjob = new Intent(getActivity(), GoodJobActivity.class);
                startActivity(goodjob);
                break;
            case R.id.img_t3:
                Intent intent = new Intent(getActivity(), NextActivity.class);
                startActivity(intent);
                break;
            case R.id.img_t4:
                Intent intent1 = new Intent(getActivity(), WeekActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_t_left:
                Intent intent2 = new Intent(getActivity(), WeiActivity.class);
                startActivity(intent2);
                break;
            case R.id.img_t_right_1:
                Intent intent3 = new Intent(getActivity(), FindActivity.class);
                startActivity(intent3);
                break;
            case R.id.img_t_right_2:
                Intent intent4 = new Intent(getActivity(), YiActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
