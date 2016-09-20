package com.mindmines.smartmomma.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mindmines.smartmomma.Dataclass.Category;
import com.mindmines.smartmomma.Dataclass.Data;

/**
 * Created by pc on 9/15/2016.
 */

public class DBHelper extends SQLiteOpenHelper{
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "smartmomma.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
     String CREATE_TABLE_DATA="create table "+ Data.TABLE+"("
                              +"_id integer priamry key ,"
                              + Data.NAME+" text,"
                              + Data.RATING_ID+" text,"
                              + Data.CATEGORY_ID+" text,"
                              + Data.SHORTDES+" text,"
                              + Data.ADDITIONAL+" text,"
                              + Data.REASONS+" text,"
                              + Data.CATEGORY_NAME+" text,"
                              + Data.CATEGORY_DES+" text,"
                              + Data.RATING_NAME+" text,"
                              + Data.RATING_LEVEL+" text );";

        String CREATE_TABLE_CATEGORY= "CREATE TABLE "+"category"+"("
                +"_id integer primary key,"
                +"ccode"+" text,"
                +"cname"+" text,"
                +"cdescription"+" text);";
        db.execSQL(CREATE_TABLE_DATA);

        db.execSQL(CREATE_TABLE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
