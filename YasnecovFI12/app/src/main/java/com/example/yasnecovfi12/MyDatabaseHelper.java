package com.example.yasnecovfi12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "movies.db"; //название бд
    private static final int DATABASE_VERSION = 1; //версия бд
    private static final String TABLE_NAME = "my_movies"; // название таблицы в бд
    // названия столбцов
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_TITLE ="movie_title";
    private static final String COLUMN_YEAR ="movie_year";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, "+
                        COLUMN_YEAR + " TEXT);";
        db.execSQL(query);
        db.execSQL("INSERT OR IGNORE INTO  "+ TABLE_NAME + " VALUES (1,'Drive', '2011'), (2,'Cruella', '2021');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //удаляет таблицу
        onCreate(db);
    }

    void addMovie(String title, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_YEAR,year);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"Ошибка", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Успешно добавлено", Toast.LENGTH_SHORT).show();
        }
    }

    //Cursor нужен чтобы получить данные из базы данных
    Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_NAME;//Выбираем все данные из бд
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id, String title, String year){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_YEAR,year);
        long result = db.update(TABLE_NAME, cv, "_id=?",new String[]{row_id});
        if (result == - 1){
            Toast.makeText(context,"Ошибка при добавлении",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Успешно добавлено",Toast.LENGTH_SHORT).show();
        }
    }
    void DelereOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if (result == - 1){
            Toast.makeText(context,"Ошибка при удалении",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Успешно удалено",Toast.LENGTH_SHORT).show();
        }
    }
}
