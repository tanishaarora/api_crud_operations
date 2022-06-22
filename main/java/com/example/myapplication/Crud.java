package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Crud extends AppCompatActivity {

    public static final String DATABASE_NAME = "mydatabase";

    FloatingActionButton fab;
    TextView id, title, description;
    Button edit, delete;
    RecyclerView recyclerView;
    Toolbar tool;
    private SqliteDatabaseHelper sqliteDatabaseHelper;
    private List<Crud_model> crud_modelList;
    private Crud_Adapter crud_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        id = (TextView) findViewById(R.id.id_key);
        title = (TextView) findViewById(R.id.title_key);
        description = (TextView) findViewById(R.id.description_key);
        edit = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView = (RecyclerView) findViewById(R.id.crud_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        tool = (Toolbar) findViewById(R.id.tool);
        tool.setTitle("Notes");
        setSupportActionBar(tool);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        sqliteDatabaseHelper = new SqliteDatabaseHelper(this);
        crud_modelList = new ArrayList<>();
        crud_adapter = new Crud_Adapter(crud_modelList, Crud.this);
        recyclerView.setAdapter(crud_adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Crud_submit.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        crud_modelList.clear();
        crud_modelList.addAll(sqliteDatabaseHelper.getCrudModel());
        crud_adapter.notifyDataSetChanged();
        if (crud_modelList.size() == 0) {
            Toast.makeText(this, "There is no note in the database", Toast.LENGTH_SHORT).show();
        }
        Log.e("Crud", "onResume");
    }
}