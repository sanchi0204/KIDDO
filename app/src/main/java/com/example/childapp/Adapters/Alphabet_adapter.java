package com.example.childapp.Adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.childapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class Alphabet_adapter extends RecyclerView.Adapter<Alphabet_adapter.MyViewHolder> {

    ArrayList alphabets;
    Context context;
    TextToSpeech mTTS;
    int result;

    public Alphabet_adapter(TextToSpeech mTTS, int result) {
        this.mTTS = mTTS;
        this.result = result;
    }

    public Alphabet_adapter(ArrayList alphabets, Context context, TextToSpeech mTTS, int result) {
        this.alphabets = alphabets;
        this.context = context;
        this.mTTS = mTTS;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.content_recycler, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.text.setText((CharSequence) alphabets.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, alphabets.get(position)+" selected", Toast.LENGTH_SHORT).show();
            }
        });
        holder.speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(context, "Feature Not Support in your Device", Toast.LENGTH_SHORT).show();
                }else {
                    mTTS.speak(String.valueOf(alphabets.get(position)), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return alphabets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView speak;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            speak = itemView.findViewById(R.id.speak);



        }
    }
}

