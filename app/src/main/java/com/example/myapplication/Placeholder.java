package com.example.myapplication;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Placeholder {
    String base_url = "https://puvvnl.srmtechsol.com/puvvnlapp/";

    @GET("ws.php?type=get_srmlight")
    Call<RV_model> getRV_model();

}
