package com.example.dell.lawera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomWordsAdapter extends RecyclerView.Adapter<CustomWordsAdapter.Myholder> {
    List<CustomItem> dataModelArrayList;
    Context mContext;
    MediaPlayer mp;
    public CustomWordsAdapter(Context mContext, List<CustomItem> dataModelArrayList) {
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
    public CustomWordsAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.words_view, null);
        return new CustomWordsAdapter.Myholder(view);
    }

    @Override
    public void onBindViewHolder(CustomWordsAdapter.Myholder holder, int position) {
        final CustomItem customItem = dataModelArrayList.get(position);
        holder.word.setText(customItem.getName());
//        byte[] image = c.getBlob(0);
            Bitmap bmp= BitmapFactory.decodeByteArray(customItem.getImage(), 0 , customItem.getImage().length);
        Glide.with(mContext).load(bmp).into(holder.wordImage);
        holder.wordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getFilesDir()+"/"+nametext.getText().toString()+".m4a"
//                mp = MediaPlayer.create(mContext, customItem.getVoice_path());
//                mp.start();

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
