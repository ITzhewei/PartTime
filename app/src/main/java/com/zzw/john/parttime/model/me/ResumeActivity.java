package com.zzw.john.parttime.model.me;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.zzw.john.parttime.R;
import com.zzw.john.parttime.utils.UIUtils;

import java.util.Calendar;

public class ResumeActivity extends AppCompatActivity {

    private EditText intentET,commentET;
    private LinearLayout intentLLO,intentEditLLO,commentLLO,commentEditLLO;
    private ListView meInfoLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_me_resume);

        intentET=(EditText)findViewById(R.id.intentET);
        commentET=(EditText)findViewById(R.id.commentET);
        intentLLO=(LinearLayout)findViewById(R.id.intentLLO);
        intentEditLLO=(LinearLayout)findViewById(R.id.intentEditLLO);
        commentLLO=(LinearLayout)findViewById(R.id.commentLLO);
        commentEditLLO=(LinearLayout)findViewById(R.id.commentEditLLO);
        meInfoLV=(ListView)findViewById(R.id.meInfoLV);
        meInfoLV.setAdapter(new MeInfoListAdapter(UIUtils.getContext()));

        meInfoLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                final EditText editText=new EditText(UIUtils.getContext());
                final ViewGroup viewGroup=(ViewGroup) meInfoLV.getChildAt(position);
                final TextView itemInfoTV=(TextView)viewGroup.getChildAt(1);
                editText.setTextColor(Color.rgb(0,0,0));
                switch (position){
                    case 0:
                        new AlertDialog.Builder(ResumeActivity.this).setTitle("请输入昵称").setView(editText).
                                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        itemInfoTV.setText(editText.getText());
                                    }
                                }).setNegativeButton("取消",null).show();
                        break;
                    case 1:
                        new AlertDialog.Builder(ResumeActivity.this).setTitle("请输入真实姓名").setView(editText).
                                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        itemInfoTV.setText(editText.getText());
                                    }
                                }).setNegativeButton("取消",null).show();
                        break;
                    case 2:
                        Calendar calendar= Calendar.getInstance();
                        final int yearCurrent, monthCurrent, dayCurrent;

                        yearCurrent = calendar.get(Calendar.YEAR);
                        monthCurrent = calendar.get(Calendar.MONTH);
                        dayCurrent = calendar.get(Calendar.DAY_OF_MONTH);

                        new DatePickerDialog(ResumeActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                itemInfoTV.setText(Integer.toString(yearCurrent-year));
                            }
                        }, yearCurrent, monthCurrent, dayCurrent).show();
                        break;
                    case 3:
                        new AlertDialog.Builder(ResumeActivity.this).setTitle("选择性别").
                                setSingleChoiceItems(new String[]{"男","女"},-1,new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which==0)
                                            itemInfoTV.setText("男");
                                        else
                                            itemInfoTV.setText("女");
                                        dialog.dismiss();
                                    }

                                }).show();
                        break;
                    case 4:
                        final Dialog statureDialog = new Dialog(ResumeActivity.this);
                        statureDialog.setContentView(R.layout.meinfopicker_dialog);

                        String initValue=itemInfoTV.getText().toString();
                        final NumberPicker statureNP = (NumberPicker) statureDialog.findViewById(R.id.statureNP);
                        final Button statureCfmBtn=(Button)statureDialog.findViewById(R.id.confirmBtn);

                        statureNP.setMaxValue(25);
                        statureNP.setMinValue(7);
                        if (initValue.equals(""))
                            statureNP.setValue(16);
                        else
                            statureNP.setValue(Integer.parseInt(initValue.split("0cm")[0]));
                        statureNP.setFormatter(new NumberPicker.Formatter() {
                            @Override
                            public String format(int value) {
                                return Integer.toString(value)+"0cm";
                            }
                        });

                        statureNP.setWrapSelectorWheel(false);
                        statureNP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                        statureCfmBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                itemInfoTV.setText(Integer.toString(statureNP.getValue())+"0cm");
                                statureDialog.dismiss();
                            }
                        });

                        statureDialog.show();

                        break;
                    case 5:
                        final Dialog schoolDialog=new Dialog(ResumeActivity.this);
                        schoolDialog.setContentView(R.layout.meinfopicker_dialog);

                        final NumberPicker schoolNP = (NumberPicker) schoolDialog.findViewById(R.id.statureNP);
                        final Button schoolCfmBtn=(Button)schoolDialog.findViewById(R.id.confirmBtn);
                        final String[] schools={"东北大学","鲁迅美术学院","辽宁大学","沈阳航空航天大学","中国医科大学"};

                        schoolNP.setDisplayedValues(schools);
                        schoolNP.setMaxValue(schools.length-1);
                        schoolNP.setMinValue(0);
                        schoolNP.setValue(0);

                        schoolNP.setWrapSelectorWheel(false);
                        schoolNP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                        schoolCfmBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                itemInfoTV.setText(schools[schoolNP.getValue()]);
                                schoolDialog.dismiss();
                            }
                        });

                        schoolDialog.show();

                        break;
                    default:
                        break;
                }
            }
        });

        intentEditLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentEditLLO.setVisibility(View.INVISIBLE);
                intentET.setInputType(InputType.TYPE_CLASS_TEXT);
                addOptionBtn(intentLLO,1);
            }
        });

        commentEditLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentEditLLO.setVisibility(View.INVISIBLE);
                commentET.setInputType(InputType.TYPE_CLASS_TEXT);
                addOptionBtn(commentLLO,2);
            }
        });

    }

    private void addOptionBtn(final LinearLayout superLayout, final int source){
        //source 1:兼职意向 2:个人评价
        final LinearLayout newLayout=new LinearLayout(this);
        Button confirmBtn=new Button(this);
        Button cancelBtn=new Button(this);
        LinearLayout.LayoutParams layoutParams;
        int layoutWidth=superLayout.getWidth();

        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        confirmBtn.setText("确认");
        cancelBtn.setText("取消");
        layoutParams=new LinearLayout.LayoutParams(layoutWidth/4,ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(layoutWidth/6,0,0,0);
        confirmBtn.setLayoutParams(layoutParams);
        cancelBtn.setLayoutParams(layoutParams);
        newLayout.addView(confirmBtn);
        newLayout.addView(cancelBtn);
        superLayout.addView(newLayout);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (source==1){
                    intentEditLLO.setVisibility(View.VISIBLE);
                    intentET.setInputType(InputType.TYPE_NULL);
                }else {
                    commentEditLLO.setVisibility(View.VISIBLE);
                    commentET.setInputType(InputType.TYPE_NULL);
                }
                superLayout.removeView(newLayout);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (source==1){
                    intentEditLLO.setVisibility(View.VISIBLE);
                    intentET.setInputType(InputType.TYPE_NULL);
                }else {
                    commentEditLLO.setVisibility(View.VISIBLE);
                    commentET.setInputType(InputType.TYPE_NULL);
                }
                superLayout.removeView(newLayout);
            }
        });
    }

    private int PxToDp(int pxValue){
        final float scale= UIUtils.getContext().getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

    private int DpToPx(int dpValue){
        final float scale= UIUtils.getContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }


}