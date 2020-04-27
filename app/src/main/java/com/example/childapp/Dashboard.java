package com.example.childapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.Locale;

public class Dashboard extends AppCompatActivity {

    RelativeLayout Alphabets,Numbers,Shapes;
    RelativeLayout r1,r2;
    ImageView astro;
    TextView intro_text;
    Button start;
    Animation slide_in_right,slide_out_left,slide_up,slide_down,slide_in_left,slide_out_right;
    TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.GERMAN);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                      speak();
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        Alphabets=findViewById(R.id.card_alphabets);
        Numbers=findViewById(R.id.card_numbers);
        Shapes=findViewById(R.id.card_shapes);

        r1=findViewById(R.id.layout_1);
        r2=findViewById(R.id.layout_2);

        astro=findViewById(R.id.img_intro);
        intro_text=findViewById(R.id.text_intro);
        start=findViewById(R.id.btn_start);


        slide_in_right= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        slide_out_left= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_left);
        slide_up= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        slide_down= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        slide_in_left= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_left);
        slide_out_right= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        astro.startAnimation(slide_up);
        intro_text.startAnimation(slide_in_left);
        start.startAnimation(slide_up);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intro_text.startAnimation(slide_out_right);
                start.startAnimation(slide_down);
                astro.startAnimation(slide_down);
                r1.setVisibility(View.GONE);
                r2.setVisibility(View.VISIBLE);
            }
        });

        Alphabets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,Content.class);
                startActivity(i);
            }
        });
        Numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,Numbers.class);
                startActivity(i);
            }
        });
        Shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,Shapes.class);
                startActivity(i);
            }
        });

    }

    private void speak() {
        String text = intro_text.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
