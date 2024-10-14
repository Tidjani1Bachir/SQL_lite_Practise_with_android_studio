package com.example.sqllitepractise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final String DATABASE_NAME="Book Libirary";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME ="my_kibirary";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_TITLE="book_title";
    private static final String COLUMN_AUTHOR="book_author";
    private static final String COLUMN_PAGES="book_pages";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     String query = "CREATE TABLE " + TABLE_NAME +
             " ("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             COLUMN_TITLE + " TEXT, " + COLUMN_AUTHOR + " TEXT, " +
             COLUMN_PAGES + " INTEGER);";
     db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
      onCreate(db);
    }

    void addBook(String title,String author,int pages) {
        // with this we are refering to SQLiteOpenHelper class and we are using it's methode getWritableDatabas

         SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
/*we don't need column id cause it will be incremented automaticly*/
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"add successfully",Toast.LENGTH_SHORT).show();
        }


    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =null;
        if(db !=null) {
            cursor = db.rawQuery(query,null);

        }
        return  cursor;
    }
}
