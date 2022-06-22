package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import utils.Utility;

public class VolleyLogin extends AppCompatActivity {

    Toolbar toolbar;
    EditText email, password;
    Button login;
    RequestQueue queue;
    TextView msgResponse;
    ProgressBar progressBarHolder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        msgResponse = findViewById(R.id.idTVResponse);
        progressBarHolder = (ProgressBar) findViewById(R.id.progressBar);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Login Activity");
        setSupportActionBar(toolbar);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    Toast.makeText(VolleyLogin.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private TextView postDataUsingVolley(String mail, String pass) {

        if(isConnected()){


            Utility ob = new Utility();
            ob.hideKeyboard(this);
            progressBarHolder.setVisibility(View.VISIBLE);

        String url = "https://reqres.in/api/login";
        queue = Volley.newRequestQueue(VolleyLogin.this);

            Map<String, String> postParam= new HashMap<>();
            postParam.put("email", mail);
            postParam.put("password", pass);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), new Response.Listener<JSONObject>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(JSONObject response) {
                    progressBarHolder.setVisibility(View.GONE);

                    //JSONObject jObj = new JSONObject();
                   /* Log.d(TAG, response.toString());
                    msgResponse.setText(response.toString());*/
                    try {
                        if (response.has("token")) {
                            String token = response.getString("token");
                            Log.d("token: " , token); // Present Key
                            msgResponse.setText(token.toString());
                        }
                        else if(response.has("error")){
                            String error = response.getString("error");
                            Log.d("error: " , error); // Present Key
                            msgResponse.setText("error");
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    progressBarHolder.setVisibility(View.GONE);

                }
            }); /*{
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
 //                   headers.put("mail", mail);
  //YYY                  headers.put("pass", pass);

            return headers;

        }
        } ;*/
            request.setTag(TAG);

            queue.add(request);
    }
        else
        {
            Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();

     //       Util obj = new Util();
       //     int sum = obj.add(2,3);


        }
    return  msgResponse;
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