package com.example.childapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.childapp.Adapters.Alphabet_adapter;
import com.example.childapp.Adapters.Number_adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Numbers extends AppCompatActivity {
    RecyclerView recyclerView_numbers;
    ImageView home_btn;
    TextToSpeech mTTS;
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList numbers = new ArrayList<>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    result = mTTS.setLanguage(Locale.ENGLISH);
                }else {
                    Toast.makeText(getApplicationContext(),"Feature Not Support in your Device",Toast.LENGTH_LONG).show();
                }
            }
        });
        recyclerView_numbers=findViewById(R.id.recycler_numbers);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView_numbers.setLayoutManager(gridLayoutManager);

        Number_adapter number_adapter = new Number_adapter(numbers, Numbers.this,mTTS,result);
        recyclerView_numbers.setAdapter(number_adapter);


        home_btn=findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Numbers.this,Dashboard.class);
                startActivity(i);
            }
        });
    }
}
