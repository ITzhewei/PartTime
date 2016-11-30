package com.zzw.john.parttime.model.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzw.john.parttime.R;

/**
 * Created by czr_8 on 2016/11/20.
 */

public class MeListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;

    public MeListAdapter(Context context){
        this.context=context;
        this.layoutInflater=LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return 2;
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
        ImageView listPicIV,listArrowIV;
        TextView listNameTV;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.list_me_item,null);
        }

        listNameTV=(TextView)convertView.findViewById(R.id.listNameTV);
        if (position==0) {
            listNameTV.setText("我的简历");
        }else if (position==1){
            listNameTV.setText("我的报名");
        }
        return convertView;
    }
}
