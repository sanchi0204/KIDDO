package com.example.childapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.childapp.Adapters.Alphabet_adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Content extends AppCompatActivity {

RecyclerView recyclerView_content;
ImageView home_btn;
    ArrayList alphabets;
    TextToSpeech mTTS;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        alphabets = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));

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
        recyclerView_content=findViewById(R.id.recycler_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView_content.setLayoutManager(gridLayoutManager);

                final Alphabet_adapter alphabet_adapter = new Alphabet_adapter(alphabets, Content.this,mTTS,result);
                recyclerView_content.setAdapter(alphabet_adapter);


                home_btn=findViewById(R.id.home_btn);
                home_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(Content.this,Dashboard.class);
                        startActivity(i);
                    }
                });



        }

}

