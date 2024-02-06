package com.example.trade_mentor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="TRADE_MENTOR";
    public static final int VERSION=1;
    public static final String TABLE_NAME="USER_INFO";
    public static final String USER_INFO_ID="ID";
    public static final String USER_INFO_NAME="NAME";
    public static final String USER_INFO_EMAIL="EMAIL";
    public static final String USER_INFO_PASSWORD="PASSWORD";
    public static final String USER_INFO_MOBILE_NUMBER="MOBILE_NUMBER";

        public dbhelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + USER_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_INFO_NAME + " TEXT," + USER_INFO_EMAIL + " TEXT," + USER_INFO_PASSWORD + " TEXT," + USER_INFO_MOBILE_NUMBER + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public void add_user_info(String name,String email,String password,String mobile_number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(USER_INFO_NAME,name);
        value.put(USER_INFO_EMAIL,email);
        value.put(USER_INFO_PASSWORD,password);
        value.put(USER_INFO_MOBILE_NUMBER,mobile_number);
        db.insert(TABLE_NAME,null,value);
    }
    public void add_user_info(String email,String password,String mobile_number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(USER_INFO_EMAIL,email);
        value.put(USER_INFO_PASSWORD,password);
        value.put(USER_INFO_MOBILE_NUMBER,mobile_number);
        db.insert(TABLE_NAME,null,value);
    }
    public ArrayList<user_info_model> fatch_user_info(){
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
            ArrayList<user_info_model> arrayList=new ArrayList<>();
            while (cursor.moveToNext()){
                user_info_model model=new  user_info_model();
                model.id=cursor.getInt(0);
                model.name=cursor.getString(1);
                model.email=cursor.getString(2);
                model.password=cursor.getString(3);
                model.mobile_number=cursor.getString(4);
                arrayList.add(model);
            }
        return arrayList;
    }
    public void update_user_info(user_info_model user_info_model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(USER_INFO_NAME,user_info_model.name);
        value.put(USER_INFO_EMAIL,user_info_model.email);
        value.put(USER_INFO_PASSWORD,user_info_model.password);
        value.put(USER_INFO_MOBILE_NUMBER,user_info_model.mobile_number);
        db.update(TABLE_NAME,value,USER_INFO_ID+" = "+user_info_model.id,null);
    }
}
