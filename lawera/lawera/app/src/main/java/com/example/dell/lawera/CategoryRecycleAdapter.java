package com.example.dell.lawera;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryRecycleAdapter extends RecyclerView.Adapter<CategoryRecycleAdapter.Myholder> {
    List<Item> dataModelArrayList;
    Context mContext;


    public CategoryRecycleAdapter(Context mContext, List<Item> dataModelArrayList) {
        this.mContext = mContext;
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView word;
        ImageView itemImage;

        public Myholder(View itemView) {
            super(itemView);
            word = (TextView) itemView.findViewById(R.id.word_tv);
            itemImage = (ImageView)itemView.findViewById(R.id.image_iv);
        }
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, null);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        final Item item = dataModelArrayList.get(position);
        holder.word.setText(item.getWord());
        Glide.with(mContext).load(item.getImage()).into(holder.itemImage);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Lesson.class);
                intent.putExtra("keyName",item.getName());
                intent.putExtra("CategoryKey",item.getCategory());
                int newTask = Intent.FLAG_ACTIVITY_NEW_TASK;
                mContext.startActivity(intent.addFlags(newTask));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}