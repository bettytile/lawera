package com.example.dell.lawera;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.Myholder> {
    List<Item> dataModelArrayList;
    Context mContext;
    MediaPlayer mp;
    public WordsRecyclerAdapter(Context mContext, List<Item> dataModelArrayList) {
        this.mContext = mContext;
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView word;
        ImageView wordImage;

        public Myholder(View itemView) {
            super(itemView);
            word = (TextView) itemView.findViewById(R.id.word_text);
            wordImage = (ImageView)itemView.findViewById(R.id.word_image);
        }
    }

    @Override
    public WordsRecyclerAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.words_view, null);
        return new WordsRecyclerAdapter.Myholder(view);
    }

    @Override
    public void onBindViewHolder(WordsRecyclerAdapter.Myholder holder, int position) {
        final Item item = dataModelArrayList.get(position);
        holder.word.setText(item.getWord());
        Glide.with(mContext).load(item.getImage()).into(holder.wordImage);
        holder.wordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(mContext, item.getVoice());
                mp.start();

//                Intent intent = new Intent(mContext,Talk.class);
//                intent.putExtra("keyName",item.getName());
//                intent.putExtra("CategoryKey",item.getCategory());
//                int newTask = Intent.FLAG_ACTIVITY_NEW_TASK;
//                mContext.startActivity(intent.addFlags(newTask));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}
