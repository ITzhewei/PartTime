package com.zzw.john.parttime.model.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.zzw.john.parttime.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        ButterKnife.bind(this,view);

        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        mBanner.setIndicatorGravity(Banner.CENTER);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(2000);
        mBanner.setImages(images);

        return view;
    }
}
