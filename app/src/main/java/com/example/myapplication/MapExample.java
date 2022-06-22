package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MapExample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_tracking);

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "105 Elon");
        map.put(3, "New Port");
        map.put(4, "12345678");
        map.put(6, "New name");

//                map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        for(Map.Entry m: map.entrySet()){
            Log.d("tag", " Key =  " + m.getKey() + " Value =  " + m.getValue());
        }

    }
}
