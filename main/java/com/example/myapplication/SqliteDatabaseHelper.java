package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabaseHelper extends SQLiteOpenHelper {

    Context context;
    private static final int db_version = 1;
    private static final String database_name = "sqlite_database";

    private static final String table_name = " Notes ";

    public static final String id = "id";
    public static final String title = "title";
    public static final String description = "description";

    private SQLiteDatabase sqLiteDatabase;

    private static final String create_table = " Create Table " + table_name + "("
            + id + " Integer Primary Key AUTOINCREMENT, "
            + title + " Text not null,"
            + description + " Text not null);";


    public SqliteDatabaseHelper(Context context) {
        super(context, database_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void addItem(Crud_model crud_model) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteDatabaseHelper.title, crud_model.getTitle());
        contentValues.put(SqliteDatabaseHelper.description, crud_model.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(SqliteDatabaseHelper.table_name, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<Crud_model> getCrudModel() {
        String sql = " select * from " + table_name;
        sqLiteDatabase = this.getReadableDatabase();
        List<Crud_model> storedata = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                storedata.add(new Crud_model(id,title, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storedata;
    }

    @SuppressLint("Range")
    public Crud_model getNotes(String identity) {
        sqLiteDatabase = this.getWritableDatabase();
        String sql = " SELECT * FROM " + table_name + " WHERE " + SqliteDatabaseHelper.id + " = " + identity;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor != null)
            cursor.moveToFirst();

            Crud_model crud = new Crud_model();
            crud.setId(cursor.getInt(cursor.getColumnIndex(SqliteDatabaseHelper.id)));
            crud.setTitle(cursor.getString(cursor.getColumnIndex(SqliteDatabaseHelper.title)));
            crud.setDescription(cursor.getString(cursor.getColumnIndex(SqliteDatabaseHelper.description)));

        return crud;
    }

    public int updateNotes(String identity, String ttl, String descr){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(title, ttl);
        contentValues.put(description, descr);

        int result = sqLiteDatabase.update(table_name, contentValues,  " id = ? ", new String[]{String.valueOf(identity)});
     /*   if(result == 0){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }*/
        return result;
   //     sqLiteDatabase.close();
    }

 /*   public void updateNotes(Crud_model crud_model){

        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        Cursor cursor = sqliteDatabase.rawQuery(" select *  from notes where id = ? ", new String[]{id});
        if ((cursor.getCount() > 0)){
            long update = sqliteDatabase.update("title", contentValues, "description", new String[]{id});
            if(update == -1){
                return true;
            }
            else{
                return; false;
            }
        }*/
     //   sqLiteDatabase.close();
    public int deleteNotes(Integer ide){

        sqLiteDatabase = this.getWritableDatabase();
        int res = sqLiteDatabase.delete(table_name, id + " = ? ", new String[]
                {String.valueOf(ide)});

      //  sqLiteDatabase.close();
        return res;
    }
}
