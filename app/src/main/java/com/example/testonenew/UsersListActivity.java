package com.example.testonenew;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testonenew.DBHelper.DatabaseHelper;
import com.example.testonenew.model.User;

public class UsersListActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(UsersListActivity.this) ;
    String userName;
    String UserType;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Bundle extras = getIntent().getExtras();



            userName = extras.getString("EMAIL");
            cursor = db.checkUserRole(userName);
          //  String a = user.getType();
            if(cursor.moveToFirst()) {
                UserType = cursor.getString(0);
            }
            switch (UserType)
            {
                case "Admin":

                    Toast.makeText(UsersListActivity.this, " Toast " + UserType, Toast.LENGTH_SHORT).show();

                    break;

                case "Owner":

                    Toast.makeText(UsersListActivity.this, " Toast Owner" , Toast.LENGTH_SHORT).show();

                    break;



            }
           /* if(UserType.equals("Admin"))
            {}*/
         //   Toast.makeText(UsersListActivity.this, " Toast " + UserType, Toast.LENGTH_SHORT).show();

            // and get whatever type user account id is



    }
}
