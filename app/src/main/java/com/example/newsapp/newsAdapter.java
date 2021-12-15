package com.example.newsapp;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class newsAdapter  extends RecyclerView.Adapter<newsAdapter.ViewHolder>  {
    List<String> news;
    String inews;
    Context context;
    public newsAdapter(Context context, List<String> news) {
        this.news=news;
        this.context=context;
    }

    public String getItem() {
        return inews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1  ,parent,false);
        return new ViewHolder(newsView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        inews= news.get(position);
        holder.bind(inews);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( context, ArticleActivity.class);
                i.putExtra("inews",news.get(position));
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem= itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvItem.setText(item);



        }
    }

}
