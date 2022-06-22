package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrudUpdate extends AppCompatActivity {

    EditText update_title, update_description;
    EditText update_id;
    Button update_button;
    Toolbar tool;
    Context context;

    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crudupdate);

        update_id = (EditText) findViewById(R.id.update_id);
        update_title = (EditText) findViewById(R.id.update_title);
        update_description = (EditText) findViewById(R.id.update_description);
        update_button = (Button) findViewById(R.id.update_button);

        tool = (Toolbar) findViewById(R.id.tool);
        tool.setTitle("Update Notes");
        setSupportActionBar(tool);

        getSupportActionBar();
        if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        id = getIntent().getStringExtra("id1");
        update_id.setText(id);
        update_id.setEnabled(false);
        update_id.setClickable(false);

        SqliteDatabaseHelper sqliteDatabaseHelper = new SqliteDatabaseHelper(CrudUpdate.this);
        Crud_model crud_model = sqliteDatabaseHelper.getNotes(id);
        if(crud_model != null){
           // update_id.setText(crud_model.getId());
            update_title.setText(crud_model.getTitle());
            update_description.setText(crud_model.getDescription());
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringTitle = update_title.getText().toString();
                String stringDesc = update_description.getText().toString();

                if (stringTitle.length() <= 0 || stringDesc.length() <= 0) {
                    Toast.makeText(CrudUpdate.this, "Please enter the data", Toast.LENGTH_SHORT).show();
                }
                else {
                    SqliteDatabaseHelper sqliteDatabaseHelper = new SqliteDatabaseHelper(CrudUpdate.this);
                    int result = sqliteDatabaseHelper.updateNotes(id, stringTitle, stringDesc);
                    if(result == 0) {
                        Toast.makeText(CrudUpdate.this, "Failed to update", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CrudUpdate.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
  //      getIntentData();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


   /* void getIntentData(){
        if(getIntent().hasExtra("id")  && getIntent().hasExtra("title") &&
        getIntent().hasExtra("description")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            update_id.setText(id);
            update_title.setText(title);
            update_description.setText(description);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }*/
}
