package com.mindmines.smartmomma.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.mindmines.smartmomma.Dataclass.Data;

import java.util.ArrayList;

/**
 * Created by pc on 9/15/2016.
 */

public class DataRepo {
    private DBHelper dbHelper;

    public DataRepo(Context context){
        dbHelper= new DBHelper(context);
    }


    public void insertData(Data data){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Data.NAME,data.getName());
        values.put(Data.RATING_ID,data.getRatingid());
        values.put(Data.CATEGORY_ID,data.getCategoryid());
        values.put(Data.SHORTDES,data.getShortdes());
        values.put(Data.ADDITIONAL,data.getAdditional());
        values.put(Data.REASONS,data.getReasons());
        values.put(Data.CATEGORY_NAME,data.getCategoryname());
        values.put(Data.CATEGORY_DES,data.getCategorydes());
        values.put(Data.RATING_NAME,data.getRatingname());
        values.put(Data.RATING_LEVEL,data.getRatinglevel());

        db.insert(Data.TABLE,null,values);
        db.close();

    }
    public ArrayList<String>  getNameListByKeyword() {
        //Open connection to read only
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Data.NAME + " FROM " + Data.TABLE +
               " ;";


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        while(cursor.moveToNext()){
            String a=cursor.getString(0);
            arrayList.add(a);
        }


        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return arrayList;


    }


}
