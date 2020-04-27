package com.example.childapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button Get_started;
    TextView app_name,tag;
    ImageView img;
    Animation slide_in_right,slide_in_left,slide_out_right,slide_out_left,slide_up,slide_down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app_name=findViewById(R.id.app_name);
        tag=findViewById(R.id.tag_line);


        img=findViewById(R.id.logo_img);


        slide_in_right= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        slide_out_left= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_left);
        slide_up= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        slide_down= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        slide_in_left= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_left);
        slide_out_right= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        Get_started=findViewById(R.id.btn_start);

        tag.startAnimation(slide_in_left);
        img.startAnimation(slide_up);
        Get_started.startAnimation(slide_up);


        Get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Dashboard.class);
                startActivity(i);
            }
        });
    }
}
