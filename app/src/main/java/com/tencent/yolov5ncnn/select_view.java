package com.tencent.yolov5ncnn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class select_view extends Activity {
    ImageView image1,image2,image3;
    ImageView im1,im2,im3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_layout);
        init();
    }
    protected void init(){
        im1=findViewById(R.id.imagebot);
        im2=findViewById(R.id.imagebot2);
        im3=findViewById(R.id.imagebot3);
        image1=findViewById(R.id.imagewheat);
        image2=findViewById(R.id.imageapple);
        image3=findViewById(R.id.imageplant);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_view.this,IntroActivity.class));
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_view.this,FirstActivity.class));
            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_view.this,detect_act.class));
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_view.this,MainActivity.class));
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_view.this,detect_plant.class));
            }
        });
    }
}
