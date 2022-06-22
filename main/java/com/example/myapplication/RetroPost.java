package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetroPost {

    @POST("login")
    Call<RetrofitPostResponse> createPost(@Body RetroPostModel retroPostModel);
}
