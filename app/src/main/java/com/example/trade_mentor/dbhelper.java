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
    public static final int VERSION=2;
    public static final String TABLE_NAME="USER_INFO";
    public static final String USER_INFO_ID="ID";
    public static final String USER_INFO_NAME="NAME";
    public static final String USER_INFO_EMAIL="EMAIL";
    public static final String USER_INFO_PASSWORD="PASSWORD";
    public static final String USER_INFO_MOBILE_NUMBER="MOBILE_NUMBER";



    private static final String TABLE_STOCK = "stock";
    private static final String COLUMN_BANK_NAME = "bank_name";
    private static final String COLUMN_STOCK_PRICE = "stock_price";
    private static final String COLUMN_CHP = "chp";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_NUMBER_OF_STOCK = "number_of_stock";
    private static final String COLUMN_TOTAL_AMOUNT = "total_amount";
    // Create table query
    private static final String SQL_CREATE_STOCK_TABLE =
            "CREATE TABLE " + TABLE_STOCK + " (" +
                    COLUMN_BANK_NAME + " TEXT," +
                    COLUMN_STOCK_PRICE + " TEXT," +
                    COLUMN_CHP + " TEXT," +
                    COLUMN_NUMBER_OF_STOCK + " TEXT," +
                    COLUMN_TOTAL_AMOUNT + " TEXT," +
                    COLUMN_DATE + " TEXT)";
    public dbhelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + USER_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_INFO_NAME + " TEXT," + USER_INFO_EMAIL + " TEXT," + USER_INFO_PASSWORD + " TEXT," + USER_INFO_MOBILE_NUMBER + " TEXT" + ")");
        db.execSQL(SQL_CREATE_STOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
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
    public void insertStock(String bankName, String stockPrice, String chp,String number_of_stock,String total_amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BANK_NAME, bankName);
        values.put(COLUMN_STOCK_PRICE, stockPrice);
        values.put(COLUMN_CHP, chp);
        values.put(COLUMN_NUMBER_OF_STOCK, number_of_stock);
        values.put(COLUMN_TOTAL_AMOUNT, total_amount);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_STOCK, null, values);
        db.close();
    }
    public void updateStock(String bankName, String newStockPrice, String newChp,String newnumber_of_stock,String newtotal_amount, String newDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STOCK_PRICE, newStockPrice);
        values.put(COLUMN_CHP, newChp);
        values.put(COLUMN_NUMBER_OF_STOCK, newnumber_of_stock);
        values.put(COLUMN_TOTAL_AMOUNT, newtotal_amount);
        values.put(COLUMN_DATE, newDate);
        db.update(TABLE_STOCK, values, COLUMN_BANK_NAME + " = ?", new String[]{bankName});
        db.close();
    }
    public void deleteStock(String bankName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STOCK, COLUMN_BANK_NAME + " = ?", new String[]{bankName});
        db.close();
    }
    public ArrayList<stock_info_model> fatch_stock_info(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_STOCK,null);
        ArrayList<stock_info_model> arrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            stock_info_model model=new  stock_info_model();
            model.bank_name=cursor.getString(0);
            model.stock_price=cursor.getString(1);
            model.chp=cursor.getString(2);
            model.number_of_stock=cursor.getString(3);
            model.total_amount=cursor.getString(4);
            model.date=cursor.getString(5);
            arrayList.add(model);
        }
        return arrayList;
    }
}
