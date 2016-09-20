package com.mindmines.smartmomma.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mindmines.smartmomma.Dataclass.Category;
import com.mindmines.smartmomma.Dataclass.Data;

import java.util.ArrayList;

/**
 * Created by pc on 9/15/2016.
 */

public class CategoryRepo {

    DBHelper dbHelper;

    public CategoryRepo(Context context){
        dbHelper= new DBHelper(context);
    }

    public void insertcatgory(Category category){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Category.CODE,category.getCode());
        values.put(Category.NAME,category.getName());
        values.put(Category.DES,category.getDescription());

        db.insert(Category.TABLE,null,values);
        db.close();

    }

    public ArrayList<String> getCategoryList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(Category.TABLE,null,null,null,null,null,null);
        ArrayList<String> arrayList = new ArrayList<>();
    if(cursor!=null){
    while(cursor.moveToNext()){

        arrayList.add(cursor.getString(2));
      }
    }

    return arrayList;
    }


    public String getCategoryDes(String cname){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(Category.TABLE,null,Category.NAME+"=?",new String[]{cname},null,null,null);
        String des=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
               des= cursor.getString(3);
            }
        }
       return des;
    }
}
