package com.example.myapplication;

import static android.media.CamcorderProfile.get;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Crud_Adapter extends RecyclerView.Adapter<Crud_Adapter.ViewHolder> {

    List<Crud_model> notes;
    Context context;
    SqliteDatabaseHelper sqliteDatabaseHelper;


    public Crud_Adapter(List<Crud_model> notes, Context context){
        this.notes = notes;
        this.context = context;
        sqliteDatabaseHelper = new SqliteDatabaseHelper(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.crud_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Crud_model crud_model = notes.get(position);

        holder.id.setText(Integer.toString(crud_model.getId()));
        holder.edit_Title.setText(crud_model.getTitle());
        holder.edit_Description.setText(crud_model.getDescription());

        holder.edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CrudUpdate.class);
                intent.putExtra("id1", String.valueOf(crud_model.getId()));
                context.startActivity(intent);

            }
        });
        /*
                String stringTitle = holder.edit_Title.getText().toString();
                String stringDescription = holder.edit_Description.getText().toString();

                sqliteDatabaseHelper.updateNotes(new Crud_model(crud_model.getId(), stringTitle, stringDescription));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
*/
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirm Exit")
                        .setMessage("Are you sure you want to exit")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SqliteDatabaseHelper sqliteDatabaseHelper = new SqliteDatabaseHelper(context);
                                int delete_result = sqliteDatabaseHelper.deleteNotes(crud_model.id);

                                if(delete_result == 0){
                                    Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
                                }else {
                                    notes.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView edit_Title;
        TextView edit_Description;
        Button edit_button;
        Button delete_button;

        public ViewHolder(View view){
            super(view);

            id = view.findViewById(R.id.id_key);
            edit_Title = view.findViewById(R.id.title_key);
            edit_Description = view.findViewById(R.id.description_key);
            edit_button = view.findViewById(R.id.update);
            delete_button = view.findViewById(R.id.delete);
        }
    }
}
