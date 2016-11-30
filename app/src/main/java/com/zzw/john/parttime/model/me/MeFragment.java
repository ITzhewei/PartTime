package com.zzw.john.parttime.model.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zzw.john.parttime.R;

/**
 * Created by john on 2016/11/1.
 */

public class MeFragment extends Fragment {

    private ListView choiceLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        choiceLV=(ListView)view.findViewById(R.id.choiceLV);
        choiceLV.setAdapter(new MeListAdapter(getContext()));
        choiceLV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=null;
                if (position==0){
                    intent=new Intent(getActivity(),ResumeActivity.class);
                }else if (position==1){
                    intent=new Intent(getActivity(),EnrollActivitiy.class);
                }
                startActivity(intent);
            }
        });

        return view;
    }
}
