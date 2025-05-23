package com.tencent.yolov5ncnn;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import domain.AppleDomain;

public class detail_apple extends AppCompatActivity {
    private TextView titleTxt,introtxt,reasontxt,pretxt,guardtxt;
    private AppleDomain item;
    private ImageView picImg,reimg;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);
        initView();
        setVariable();
    }
    private void setVariable() {
        item = (AppleDomain) getIntent().getSerializableExtra("object");
        titleTxt.setText(item.getDiseasetype());
        introtxt.setText("        "+item.getDescription());
        reasontxt.setText("        "+item.getReason());
        pretxt.setText("        "+item.getPresence());
        guardtxt.setText(item.getGuide());
        reimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int drawableResId = getResources().getIdentifier(item.getPic(),"drawable",getPackageName());
        Glide.with(this).load(drawableResId).into(picImg);

    }
    private void initView(){
        titleTxt=findViewById(R.id.title_text);
        introtxt=findViewById(R.id.introtext);
        reasontxt=findViewById(R.id.reasnotext);
        pretxt=findViewById(R.id.presenttext);
        guardtxt=findViewById(R.id.preventtext);
        picImg=findViewById(R.id.picpath);
        reimg=findViewById(R.id.imagereturn);
    }
}
