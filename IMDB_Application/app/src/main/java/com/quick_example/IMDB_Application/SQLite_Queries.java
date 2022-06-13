package com.quick_example.IMDB_Application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLite_Queries extends SQLiteOpenHelper{
    private static final
    int DATABASE_VERSION = 2;
    public static final String MOVIES_TABLE = "movies_table";
    public static final String MOVIE_RELEASE = "movie_release";
    public static final String MOVIE_NAME = "movie_name";
    public static final String MOVIE_DESCRIPTION = "movie_description";
    public static final String MOVIE_RATING = "movie_rating";
    public static final String MOVIE_TRAILER = "movie_trailer";
    public static final String MOVIE_IMAGE = "movie_image";
    public static final String MOVIE_KEYWORDS = "movie_keywords";
    public static final String MOVIE_ID = "movie_ID";

    public SQLite_Queries(Context context) {
        super(context, MOVIES_TABLE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE " + MOVIES_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOVIE_ID     + " TEXT, " +
                MOVIE_RELEASE     + " TEXT, " +
                MOVIE_NAME        + " TEXT, " +
                MOVIE_DESCRIPTION + " TEXT, " +
                MOVIE_RATING      + " TEXT, " +
                MOVIE_TRAILER     + " TEXT, " +
                MOVIE_IMAGE       + " TEXT, " +
                MOVIE_KEYWORDS    + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIES_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(MOVIES_TABLE, null, null);
    }

    public void removeFromDB(String fieldValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MOVIES_TABLE, MOVIE_NAME + "=?", new String[]{fieldValue});
    }


    public String checkFromDB(String fieldValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT movie_name FROM " + MOVIES_TABLE + " WHERE " + MOVIE_NAME + " =?" ;
        Cursor cursor = db.rawQuery(Query, new String[] {fieldValue});
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return fieldValue;
        }else{
            cursor.close();
            db.close();
            return null;
        }
    }

    // with the help of this method we are adding new movies to the database following the items in the parameters
    public void addData(String item0, String item1, String item2, String item3, String item4, String item5, String item6, String item7){
        SQLiteDatabase db =   this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_ID, item0);
        contentValues.put(MOVIE_RELEASE, item1);
        contentValues.put(MOVIE_NAME , item2);
        contentValues.put(MOVIE_DESCRIPTION, item3);
        contentValues.put(MOVIE_RATING , item4);
        contentValues.put(MOVIE_TRAILER , item5);
        contentValues.put(MOVIE_IMAGE , item6);
        contentValues.put(MOVIE_KEYWORDS , item7);


        long res = db.insert(MOVIES_TABLE, null , contentValues);

    }

    public ArrayList<menu_skeleton> readFromDB() {
    ArrayList<menu_skeleton> resultList = new ArrayList<>();
    SQLiteDatabase database =   this.getReadableDatabase();

    //Here we use the database cursor class that lets us create a query automatically and easily
    Cursor cursor = database.rawQuery("SELECT * FROM " + SQLite_Queries.MOVIES_TABLE , null);
    if(cursor.moveToFirst()){
        // we are looping until the cursor ends
        do {
            menu_skeleton Mms = new menu_skeleton();
            Mms.setMovie_id(cursor.getString(1));
            Mms.setMovie_release(cursor.getString(2));
            Mms.setMovie_title(cursor.getString(3));
            Mms.setMovie_description(cursor.getString(4));
            Mms.setMovie_rating(cursor.getString(5));
            Mms.setMovie_trailer(cursor.getString(6));
            Mms.setMovie_image(cursor.getString(7));
            Mms.setMovie_keywords(cursor.getString(8));
            resultList.add(Mms);
        }while (cursor.moveToNext());
    }
    else {
        Log.d("TAG", "ERROR WHILE GETTING ITEMS FROM DATABASE !!!");
    }
    cursor.close();
    database.close();
    Log.d("TAG", "Nbr of results outputted by DATABASE {" + SQLite_Queries.MOVIES_TABLE + "} is" + cursor.getCount());
        return resultList;
    }
}
