package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    Placeholder myPlaceholder;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Placeholder.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myPlaceholder = retrofit.create(Placeholder.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public com.example.myapplication.Placeholder getMyApi() {

        return myPlaceholder;
    }
}

