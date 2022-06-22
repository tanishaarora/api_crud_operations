package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Crud_submit extends AppCompatActivity {

    public static final String DATABASE_NAME = "mydatabase";

    EditText edit_title, edit_description;
    Button submit;
    Toolbar tool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_submit);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_description = (EditText) findViewById(R.id.edit_description);
        submit = (Button) findViewById(R.id.edit_button);

        tool = (Toolbar) findViewById(R.id.tool);
        tool.setTitle("Add Notes");
        setSupportActionBar(tool);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringTitle = edit_title.getText().toString();
                String stringDesc = edit_description.getText().toString();

                if (stringTitle.length() <= 0 || stringDesc.length() <= 0) {
                    Toast.makeText(Crud_submit.this, "Please enter the data"  , Toast.LENGTH_SHORT).show();
                }
                else {
                    SqliteDatabaseHelper sqliteDatabaseHelper = new SqliteDatabaseHelper(Crud_submit.this);
                    Crud_model crud_model = new Crud_model(stringTitle, stringDesc);
                    sqliteDatabaseHelper.addItem(crud_model);
                    Toast.makeText(Crud_submit.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

