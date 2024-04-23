package com.example.yasnecovfi10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static String DATABASE_PATH; // полный путь к базе данных
    private static final String DATABASE_NAME = "moviebase.db"; //название бд
    private static final int DATABASE_VERSION = 1; //версия бд

    private static final String TABLE_NAME = "movie_libary"; // название таблицы в бд
    // названия столбцов
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_TITLE ="movie_title";
    private static final String COLUMN_DIRECTOR ="movie_director";
    private static final String COLUMN_YEAR ="movie_year";
    private static final String COLUMN_DESCRIPTION ="movie_description";
    private static final String COLUMN_POSTER ="movie_poster";
    private static  final String COLUMN_LENGTH = "movie_length";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, "+
                COLUMN_DIRECTOR + " TEXT, "+
                COLUMN_YEAR + " TEXT, "+
                COLUMN_DESCRIPTION + " TEXT, "+
                COLUMN_POSTER + " BLOB, "+
                COLUMN_LENGTH + " TEXT);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //удаляет таблицу
        onCreate(db);
    }

    public  boolean addMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,movie.getMovie_title());
        cv.put(COLUMN_DIRECTOR,movie.getMovie_director());
        cv.put(COLUMN_YEAR,movie.getMovie_year());
        cv.put(COLUMN_DESCRIPTION,movie.getMovie_description());
        cv.put(COLUMN_POSTER,ImageToBlob(movie.getMovie_poster()));
        cv.put(COLUMN_LENGTH,movie.getMovie_length());
        long result = db.insert(TABLE_NAME,null,cv);
        db.close();
        return  result !=-1;

    }

    public  boolean deleteMovie(String title, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_TITLE + " =? AND+ "+ COLUMN_YEAR + " =?", new String[] { title, year });
        db.close();
        return result >0;
    }

    public Movie findMovie(String title, String year){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,COLUMN_TITLE,COLUMN_DIRECTOR, COLUMN_YEAR,COLUMN_DESCRIPTION, COLUMN_POSTER,COLUMN_LENGTH },
                COLUMN_TITLE + " =? AND+ "+ COLUMN_YEAR + " =?",new String[] { title, year },null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            int id = cursor.getInt(0);
            String m_title = cursor.getString(1);
            String m_director = cursor.getString(2);
            String m_year = cursor.getString(3);
            String m_description = cursor.getString(4);
            Bitmap m_poster = BlobToImage(cursor.getBlob(5));
            String m_length = cursor.getString(6);
            Movie movie = new Movie(id, m_title, m_director, m_year,m_description, m_poster,m_length);
            cursor.close();
            db.close();
            return movie;
        }
        else if (cursor != null){
            cursor.close();
        }
        db.close();
        return null;
    }

    public List<Movie> getAllMovies(){
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String m_title = cursor.getString(1);
                String m_director = cursor.getString(2);
                String m_year = cursor.getString(3);
                String m_description = cursor.getString(4);
                Bitmap m_poster = BlobToImage(cursor.getBlob(5));
                String m_length = cursor.getString(6);
                Movie movie = new Movie(id, m_title, m_director, m_year,m_description, m_poster,m_length);
                movieList.add(movie);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieList;
    }
    public boolean updateMovie(String old_title, String old_year,String new_title, String new_director, String new_year,String new_description, Bitmap new_poster,String new_length){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,new_title);
        cv.put(COLUMN_DIRECTOR,new_director);
        cv.put(COLUMN_YEAR,new_year);
        cv.put(COLUMN_DESCRIPTION,new_description);
        cv.put(COLUMN_POSTER,ImageToBlob(new_poster));
        cv.put(COLUMN_LENGTH,new_length);
        //Обновляем запись, где название и год фильма равны old_title и old_year
        int result = db.update(TABLE_NAME,cv,COLUMN_TITLE + " =? AND+ "+ COLUMN_YEAR + " =?",new String[] { old_title, old_year });
        db.close();
        return  result >0;

    }

    private byte[] ImageToBlob(Bitmap bitmap){
        // Преобразование Bitmap в массив байтов
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        return bos.toByteArray();
    }
    private  Bitmap BlobToImage(byte[] image){
        // Преобразование массива байтов в Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return bitmap;
    }

}
