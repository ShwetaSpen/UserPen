package com.example.testonenew.DBHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;




import com.example.testonenew.RegisterActivity;
import com.example.testonenew.model.User;

import java.util.ArrayList;
import java.util.List;

//to manage database creation and versions
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "UserManager";
    // User table name
    private static final String TABLE_NAME = "userNew";

    // User Table Columns names
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_LASTNAME = "lastname";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_DOB = "dob";
    private static final String COLUMN_USER_CONTACT = "contact";
    private static final String COLUMN_USER_ADDRESS = "address";
    private static final String COLUMN_USER_TYPE = "type";

    // create table sql query
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_USER_TABLE);

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_USER_EMAIL + " TEXT PRIMARY KEY, "
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_LASTNAME + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT,"
                + COLUMN_USER_DOB + " DATE,"
                + COLUMN_USER_CONTACT + " TEXT,"
                + COLUMN_USER_ADDRESS + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT)";


        db.execSQL(query);
    }

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }

    // for inserting values into the table
    public void addUser(String email, String name, String lastname, String password, String DOB, String contact, String address, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_LASTNAME, lastname);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_DOB, DOB);
        values.put(COLUMN_USER_CONTACT, contact);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_TYPE, s);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
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

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        // updating row
        db.update(TABLE_NAME, values, COLUMN_USER_EMAIL + " = ?",
                new String[]{String.valueOf(user.getEmail())});
        db.close();
    }

    public Boolean validate(String email) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String Query = "Select * from " + TABLE_NAME + " where " + COLUMN_USER_EMAIL + " = " + email;
            Cursor cursor = db.rawQuery(Query, null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                db.close();
                return false;
            } else
                return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with condition
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUserRole(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria

        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with condition
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }



   /* public List<String> checkRole(String email) {
        String response;
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> rollNo1 = new ArrayList<String>();

        String[] columns = {
                COLUMN_USER_TYPE
        };
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor1 = db.rawQuery("Select * from "+
                TABLE_NAME + " where " + COLUMN_USER_EMAIL +" = " + email, null);
        cursor1.moveToFirst();
        User user = new User();
        user.setType(cursor1.getString(cursor1.getColumnIndexOrThrow(COLUMN_USER_TYPE)));

        int cursorCount = cursor1.getCount();

        if (cursorCount > 0) {
            cursor1.moveToFirst();

           response = cursor1.getString(1);
            rollNo1.add(response);
           // return contact
            return rollNo1;
        }

        return null;

    }*/
}