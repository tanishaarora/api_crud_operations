package com.example.myapplication;

import static utils.Utility.hideKeyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.Utility;

public class RetroPostMethod  extends AppCompatActivity {

    EditText id, pass;
    Button result;
    TextView text;
    ProgressBar progressBar;
    Toolbar tool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retropost);


        id = (EditText) findViewById(R.id.id);
        pass = (EditText) findViewById(R.id.pass);
        text = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progBar);
        result = (Button) findViewById(R.id.result_button);


        tool = (Toolbar) findViewById(R.id.toolbar_post);
        tool.setTitle("Retrofit Post Activity");
        setSupportActionBar(tool);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
                    Toast.makeText(RetroPostMethod.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                    if(pass.getText().toString().isEmpty()){
                        Toast.makeText(RetroPostMethod.this, "Please enter password", Toast.LENGTH_SHORT).show();
                        return;

                }
                postData(id.getText().toString(), pass.getText().toString());

            }
        });
    }

    private void postData(String id, String password) {

        if (isConnected()) {

            Utility ob = new Utility();
            ob.hideKeyboard(this);
            progressBar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            RetroPost retroPost = retrofit.create(RetroPost.class);
            RetroPostModel retroPostModel = new RetroPostModel(id, password);
            Call<RetrofitPostResponse> call = retroPost.createPost(retroPostModel);
            call.enqueue(new Callback<RetrofitPostResponse>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<RetrofitPostResponse> call, Response<RetrofitPostResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if(response.body() != null) {
                        text.setText(response.body().getToken());
                    }
                    else {
                        try{
                            if(response.errorBody() != null){
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                String error = jsonObject.getString("error");
                                text.setText(error);


      //                          String error = response.errorBody().string();
     //                           Log.d("avvvvvv", "error == "+error);

                            }

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RetrofitPostResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        }

        else
        {
            Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }
        private boolean isConnected() {
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

