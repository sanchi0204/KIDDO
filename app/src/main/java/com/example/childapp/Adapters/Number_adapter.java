package com.example.childapp.Adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
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

public class Number_adapter extends RecyclerView.Adapter<Number_adapter.MyViewHolder> {

    ArrayList numbers;
    Context context;
    TextToSpeech mTTS;
    int result;

    public Number_adapter(TextToSpeech mTTS, int result) {
        this.mTTS = mTTS;
        this.result = result;
    }

    public Number_adapter(ArrayList numbers, Context context, TextToSpeech mTTS, int result) {
        this.numbers = numbers;
        this.context = context;
        this.mTTS = mTTS;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_numbers, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.text.setText((CharSequence) numbers.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, numbers.get(position)+" selected", Toast.LENGTH_SHORT).show();
            }
        });
        holder.speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(context, "Feature Not Support in your Device", Toast.LENGTH_SHORT).show();
                }else {
                    mTTS.speak(String.valueOf(numbers.get(position)), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView speak;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            speak=itemView.findViewById(R.id.speak);



        }
    }
}

