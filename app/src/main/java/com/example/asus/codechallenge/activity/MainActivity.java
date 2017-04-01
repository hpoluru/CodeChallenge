package com.example.asus.codechallenge.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.codechallenge.R;
import com.example.asus.codechallenge.adapter.RecyclerAdapter;
import com.example.asus.codechallenge.model.Guide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private RecyclerView listView;
    private LinearLayoutManager mLayoutManager;
    private String mJSonResponse;
    private ArrayList<Integer> mPos;

    // URL to get contacts JSON
    private static String url = "http://guidebook.com/service/v2/upcomingGuides/";
    ArrayList<Guide> itemList, cartItemList;

    private LoginActivity loginActivity;
    private ImageView imageView;


    public void cart(View view) {
        Intent cartActivity = new Intent(this, CartActivity.class);
        cartActivity.putExtra("cart", cartItemList);
        cartActivity.putExtra("pos", mPos);
        startActivity(cartActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent extras = getIntent();
        String acc_name = extras.getStringExtra("acc_name");
        Uri imgUri = extras.getParcelableExtra("profile_picture");
        setContentView(R.layout.activity_main);
        mPos = new ArrayList<>();

        ImageView imageView = (ImageView) findViewById(R.id.profile_image);
        TextView textView = (TextView) findViewById((R.id.profile_name));
        Glide.with(this).load(imgUri).into(imageView);
        textView.setText(acc_name);

        itemList = new ArrayList<>();
        cartItemList = new ArrayList<>();
        listView = (RecyclerView) findViewById(R.id.list);
        new GetData().execute();
    }

    private class GetData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {

            /*HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);*/

            /*JSONParser jParser = new JSONParser();
            mJSonResponse = jParser.getJSONFromUrl(args[0]);*/

            mJSonResponse = getString(R.string.resp);
            Log.e("response", mJSonResponse);

            if (mJSonResponse != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(mJSonResponse);

                    // Getting JSON Array node
                    JSONArray data = new JSONArray(mJSonResponse);
                    for (int i = 0; i < data.length(); i++) {
                        Guide guide = new Guide();
                        guide.setEndDate(data.getJSONObject(i).getString("endDate"));
                        guide.setName(data.getJSONObject(i).getString("name"));
                        guide.setImgurl(data.getJSONObject(i).getString("icon"));
                        itemList.add(guide);
                    }

                } catch (JSONException je) {
                    je.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            listView.setHasFixedSize(true);
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, itemList, callBackForAdapter);
            recyclerAdapter.notifyDataSetChanged();
            listView.setAdapter(recyclerAdapter);
            setUpView();
        }

    }

    private void setUpView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(mLayoutManager);
    }

    public interface CallBackForAdapter{
        void onClick(ArrayList<Guide> arrayList, int pos);
    }

    CallBackForAdapter callBackForAdapter = new CallBackForAdapter() {
        @Override
        public void onClick(ArrayList<Guide> arrayList, int pos) {
            cartItemList = arrayList;
            mPos.add(pos);
        }
    };

}
