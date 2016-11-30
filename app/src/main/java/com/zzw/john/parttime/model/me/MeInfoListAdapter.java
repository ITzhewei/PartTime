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
 * Created by czr_8 on 2016/11/25.
 */

public class MeInfoListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;

    public MeInfoListAdapter(Context context){
        this.context=context;
        this.layoutInflater=LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() { return 6;}

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
        TextView itemNameTV,itemInfoTV;
        ImageView itemArrowIV;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.list_meinfo_item,null);
        }
        itemNameTV=(TextView)convertView.findViewById(R.id.itemNameTV);
        itemInfoTV=(TextView)convertView.findViewById(R.id.itemInfoTV);
        itemArrowIV=(ImageView)convertView.findViewById(R.id.itemArrowIV);
        switch (position){
            case 0:
                itemNameTV.setText("昵称");
                break;
            case 1:
                itemNameTV.setText("真实姓名");
                break;
            case 2:
                itemNameTV.setText("年龄");
                break;
            case 3:
                itemNameTV.setText("性别");
                break;
            case 4:
                itemNameTV.setText("身高");
                break;
            case 5:
                itemNameTV.setText("所在学校");
                break;
            default:
                break;
        }
        return convertView;
    }
}
