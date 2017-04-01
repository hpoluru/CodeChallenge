package com.example.asus.codechallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.codechallenge.R;
import com.example.asus.codechallenge.activity.CartActivity;
import com.example.asus.codechallenge.activity.MainActivity;
import com.example.asus.codechallenge.model.Guide;

import java.util.ArrayList;

/**
 * Created by ASUS on 31-03-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private ArrayList<Guide> guideArrayList;
    private MainActivity.CallBackForAdapter mCallBackForAdapter;

    public RecyclerAdapter(Context context, ArrayList<Guide> arrayList, MainActivity.CallBackForAdapter callBackForAdapter) {
        this.mContext = context;
        this.guideArrayList = arrayList;
        this.mCallBackForAdapter = callBackForAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data_list, parent, false);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);
        return new RecyclerAdapter.RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindWall((RecyclerAdapter.RecycleHolder) holder, position);
    }

    private void bindWall(final RecyclerAdapter.RecycleHolder holder, final int position) {

        holder.name.setText(guideArrayList.get(position).getName());
        holder.endDate.setText(guideArrayList.get(position).getEndDate());
        Glide.with(mContext).load(guideArrayList.get(position).getImgurl()).into(holder.imgurl);
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCallBackForAdapter.onClick(guideArrayList,position);
            }
        });

        //holder.imgurl.setImageURI();

    }

    @Override
    public int getItemCount() {
        return guideArrayList.size();
    }

    static class RecycleHolder extends RecyclerView.ViewHolder {

        protected TextView name, endDate;
        protected ImageView imgurl, cart;

        public RecycleHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.username);
            this.endDate = (TextView) view.findViewById(R.id.endDate);
            this.imgurl = (ImageView) view.findViewById(R.id.url);
            this.cart = (ImageView) view.findViewById(R.id.cart);
        }
    }
}

