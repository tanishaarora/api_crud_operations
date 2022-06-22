package com.example.myapplication;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.APIadapter;
import com.example.myapplication.Placeholder;
import com.example.myapplication.R;
import com.example.myapplication.RV_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroMain extends AppCompatActivity {

    RecyclerView superListView;
    Placeholder placeholder;
    ProgressBar progressBarHolder;
    Toolbar tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        superListView = findViewById(R.id.superRecyclerView);
        superListView.setHasFixedSize(true);
        superListView.setLayoutManager(new LinearLayoutManager(this));


        progressBarHolder = (ProgressBar) findViewById(R.id.progressBarHolder);

        tools = (Toolbar) findViewById(R.id.toolbar_get);
        setSupportActionBar(tools);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        if (isConnected()) {

            progressBarHolder.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://puvvnl.srmtechsol.com/puvvnlapp/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            placeholder = retrofit.create(Placeholder.class);
            Call<RV_model> call = placeholder.getRV_model();
            call.enqueue(new Callback<RV_model>() {
                @Override
                public void onResponse(Call<RV_model> call, Response<RV_model> response) {
                    progressBarHolder.setVisibility(View.GONE);
                    if (response.body() != null) {
                        RV_model res = response.body();
                        if (res.isCustomResponse()) {
                            APIadapter apIadapter = new APIadapter(RetroMain.this, res.getEnergy_light());
                            superListView.setAdapter(apIadapter);
                        }
                    }
                }
                @Override
                public void onFailure(Call<RV_model> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    progressBarHolder.setVisibility(View.GONE);
                }
            });
        }

        else
        {
            Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean isConnected(){
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

