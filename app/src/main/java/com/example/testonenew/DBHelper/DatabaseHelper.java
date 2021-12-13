package com.example.testonenew.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.testonenew.model.User;

import java.util.ArrayList;
import java.util.List;

//to manage database creation and versions
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";
    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_LASTNAME = "user_lastname";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_DOB = "user_dob";
    private static final String COLUMN_USER_CONTACT = "user_contact";
    private static final String COLUMN_USER_ADDRESS = "user_address";
    private static final String COLUMN_USER_TYPE = "user_type";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_EMAIL + " TEXT PRIMARY KEY," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_LASTNAME + " TEXT," + COLUMN_USER_PASSWORD + " TEXT,"


            + COLUMN_USER_DOB + "DATE," + COLUMN_USER_CONTACT + "TEXT,"
            + COLUMN_USER_ADDRESS + "TEXT," +COLUMN_USER_TYPE + "TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper( Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }
    // for inserting values into the table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_LASTNAME,user.getLastname());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_DOB, user.getDOB());
        values.put(COLUMN_USER_CONTACT, user.getContact());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());
        values.put(COLUMN_USER_TYPE, user.getType());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //used for fetching all users.
    /*public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_LASTNAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_DOB,
                COLUMN_USER_CONTACT,
                COLUMN_USER_ADDRESS,
                COLUMN_USER_TYPE
        };
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        *//*
          SQL query equivalent to this query function is
          SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         *//*
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setEmail(Cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }*/

        public void updateUser (User user){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());
            // updating row
            db.update(TABLE_USER, values, COLUMN_USER_EMAIL + " = ?",
                    new String[]{String.valueOf(user.getEmail())});
            db.close();
        }

        public Boolean validate(String email)
        {
            try
            {
            SQLiteDatabase db = this.getWritableDatabase();
            String Query = "Select * from " + TABLE_USER + " where " + COLUMN_USER_EMAIL + " = " + email;
            Cursor cursor = db.rawQuery(Query, null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                db.close();
                return false;
            }
            else
                return true;
        } catch (Exception e) {
    }
        return false;
}


    }
