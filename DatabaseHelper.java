package com.loyality.testproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rohit Gupta on 22-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_FIRST_NAME = "fname";
    public static final String USER_COLUMN_LAST_NAME = "lname";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PHONE = "phone";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String ITEM_TABLE_NAME = "item";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_DESCRIPTION = "description";
    public static final String ITEM_COLUMN_PRICE = "price";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table user " +
                        "(id integer primary key, fname text,lname text,email text,phone text, password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(String fname, String lname, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_FIRST_NAME, fname);
        contentValues.put(USER_COLUMN_LAST_NAME, lname);
        contentValues.put(USER_COLUMN_EMAIL, email);
        contentValues.put(USER_COLUMN_PHONE, phone);
        contentValues.put(USER_COLUMN_PASSWORD, password);
        db.insert(USER_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }


    public boolean updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        db.update(USER_TABLE_NAME, contentValues, "email = ? ", new String[]{email});
        db.close();
        return true;
    }

    public UserModel getUserDetails(String emailId) {
        UserModel userModel = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from user where email='" + emailId + "'", null);
        try {
            if (res.getCount() > 0) {
                res.moveToFirst();
                userModel = new UserModel(res.getInt(res.getColumnIndex(USER_COLUMN_ID)),
                        res.getString(res.getColumnIndex(USER_COLUMN_FIRST_NAME)),
                        res.getString(res.getColumnIndex(USER_COLUMN_LAST_NAME)),
                        res.getString(res.getColumnIndex(USER_COLUMN_EMAIL)),
                        res.getString(res.getColumnIndex(USER_COLUMN_PHONE)),
                        res.getString(res.getColumnIndex(USER_COLUMN_PASSWORD)));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            res.close();
            db.close();
        }
        return userModel;
    }

//    public Integer deleteContact (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList<String> getAllCotacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
