package com.example.asus.codechallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.codechallenge.R;
import com.example.asus.codechallenge.model.Guide;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ArrayList<Guide> guideArrayList;
    private ArrayList<Integer> pos;
    LinearLayout linearLayout;
    NestedScrollView scrollView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent nullCheckIntent = this.getIntent();
        if (nullCheckIntent != null) {
            guideArrayList = (ArrayList<Guide>) nullCheckIntent.getSerializableExtra("cart");
            pos = (ArrayList<Integer>) nullCheckIntent.getSerializableExtra("pos");
        }

        setContentView(R.layout.activity_cart);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cart List");
        linearLayout = (LinearLayout) findViewById(R.id.main_linear);
        scrollView=(NestedScrollView) findViewById(R.id.scroll);

        try {
            for (int i = 0; i < pos.size(); i++) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = vi.inflate(R.layout.card_row_list, null);
                Log.d("pos", ""+pos.get(i));
                TextView name = (TextView) v.findViewById(R.id.username);
                name.setText(guideArrayList.get(pos.get(i)).getName());
                TextView endDate = (TextView) v.findViewById(R.id.endDate);
                endDate.setText(guideArrayList.get(pos.get(i)).getEndDate());
                ImageView imgurl = (ImageView) v.findViewById(R.id.url);
                Glide.with(this).load(guideArrayList.get(pos.get(i)).getImgurl()).into(imgurl);
                final ImageView remove = (ImageView) v.findViewById(R.id.cart);
                linearLayout.addView(v);
                final int position = linearLayout.indexOfChild(v);
               // linearLayout.setTag(position);

                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View r) {
                        linearLayout.removeViewAt(position);

                        //linearLayout.removeViewAt(index);
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
