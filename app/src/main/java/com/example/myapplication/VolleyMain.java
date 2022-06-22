package com.example.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyMain extends AppCompatActivity {

    //  TextView text;
    RecyclerView superListView;
    List<SRMLight> apiList;
    RequestQueue queue;
    APIadapter adapter;
    Toolbar toolbars;
    SRMLight srmLight;
    ProgressBar progressBarHolder;

    String Url = "https://puvvnl.srmtechsol.com/puvvnlapp/ws.php?type=get_srmlight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        superListView = findViewById(R.id.superRecyclerView);

        progressBarHolder = (ProgressBar) findViewById(R.id.progressBarHolder);

        toolbars = (Toolbar) findViewById(R.id.toolbar_get);
        setTitle("Get Activity");
        setSupportActionBar(toolbars);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        apiList = new ArrayList<>();
        extractApi();

            //      text = (TextView) findViewById(R.id.list);
        }

        private void extractApi () {
        if(isConnected()){

            progressBarHolder.setVisibility(View.VISIBLE);

            queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        progressBarHolder.setVisibility(View.GONE);

                        JSONArray jsonArray = response.getJSONArray("energy_light");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            srmLight = new SRMLight();
                            srmLight.setId(jsonObject.optString("id"));
                            srmLight.setValue(jsonObject.optString("value"));

                            apiList.add(srmLight);

                        /*String id = jsonObject.getString("id");
                        String value = jsonObject.getString("value");

                        text.append(id + ". " + value + "\n\n"); */
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    superListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new APIadapter(getApplicationContext(), apiList);
                    superListView.setAdapter(adapter);
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("tag", "onErrorResponse: " + error.getMessage());
                    progressBarHolder.setVisibility(View.GONE);
                }
            });
            queue.add(jsonObjectRequest);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

        }


        private boolean isConnected () {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();

        }
        return super.onOptionsItemSelected(item);

    }
    }


 /*       StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                text.setText(response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }

        });
        queue.add(request);
    }*/
