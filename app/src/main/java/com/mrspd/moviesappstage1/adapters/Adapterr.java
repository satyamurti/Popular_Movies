package com.mrspd.moviesappstage1.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrspd.moviesappstage1.OnItemClickListener;
import com.mrspd.moviesappstage1.R;
import com.mrspd.moviesappstage1.model.Result;

import java.util.List;
/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public  class Adapterr extends RecyclerView.Adapter<Adapterr.ViewHolder> {
    List<Result> resultData;
   OnItemClickListener listener;


    public Adapterr(List<Result> resultData, OnItemClickListener listener) {
        this.resultData = resultData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.onBind(resultData.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }

    public void setData(List<Result> it) {
        resultData.clear();
        this.resultData = it;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView myimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myimage = itemView.findViewById(R.id.image);

        }

        public void onBind(final Result result, final OnItemClickListener listener) {

            Glide.with(itemView.getContext())
                    .load(ip(result.getPosterPath()))
                    .into(myimage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(result);
                }
            });
        }
    }

    public static String ip(String path) {
        return "https://image.tmdb.org/t/p/" +
                "w500" +
                path;
    }
}