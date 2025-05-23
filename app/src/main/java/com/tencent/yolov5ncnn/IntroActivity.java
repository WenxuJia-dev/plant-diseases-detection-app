package com.tencent.yolov5ncnn;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

public class IntroActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        ConstraintLayout introBtn=findViewById(R.id.introBtn);
        introBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,FirstActivity.class));
            }
        });
    }
}
