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
import com.example.childapp.Adapters.Shapes_adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Shapes extends AppCompatActivity {

    RecyclerView recyclerView_shapes;
    ImageView home_btn;
    TextToSpeech mTTS;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        ArrayList shapes = new ArrayList<>(Arrays.asList("Square","Rectangle","Triangle","Circle","Cube","Cuboid","Pyramid","Sphere"));
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
        recyclerView_shapes=findViewById(R.id.recycler_shapes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView_shapes.setLayoutManager(gridLayoutManager);

        Shapes_adapter shapes_adapter = new Shapes_adapter(shapes, Shapes.this,mTTS,result);
        recyclerView_shapes.setAdapter(shapes_adapter);

        home_btn=findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Shapes.this,Dashboard.class);
                startActivity(i);
            }
        });

    }
}
