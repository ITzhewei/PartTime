package com.zzw.john.parttime.model.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzw.john.parttime.R;
import com.zzw.john.parttime.bean.EmployerBeanAll;

/**
 * Created by czr_8 on 2016/11/25.
 */

public class MeInfoListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private EmployerBeanAll.EmployerBean employer;

    public MeInfoListAdapter(Context context, EmployerBeanAll.EmployerBean employer){
        this.context=context;
        this.layoutInflater=LayoutInflater.from(this.context);
        this.employer=employer;
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
                itemNameTV.setText("真实姓名");
                if (employer.getName()!=null)
                    itemInfoTV.setText(employer.getName());
                break;
            case 1:
                itemNameTV.setText("年龄");
                if (employer.getAge()!=0)
                    itemInfoTV.setText(Integer.toString(employer.getAge()));
                break;
            case 2:
                itemNameTV.setText("性别");
                if (employer.getSex()!=null)
                    itemInfoTV.setText(employer.getSex());
                break;
            case 3:
                itemNameTV.setText("身高");
                if (employer.getHeight()!=0)
                    itemInfoTV.setText(Integer.toString(employer.getHeight())+"cm");
                break;
            case 4:
                itemNameTV.setText("所在学校");
                if (employer.getSchoolName()!=null)
                    itemInfoTV.setText(employer.getSchoolName());
                break;
            case 5:
                itemNameTV.setText("电话号码");
                if (employer.getPhone()!=null)
                    itemInfoTV.setText(employer.getPhone());
                break;
            default:
                break;
        }
        return convertView;
    }
}
