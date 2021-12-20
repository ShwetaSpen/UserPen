package com.example.testonenew;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonenew.DBHelper.DatabaseHelper;
import com.example.testonenew.model.itemOwnerModel;

import java.util.ArrayList;


public class UsersListActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(UsersListActivity.this);
    String userName;
    String UserType;
    Cursor cursor;
    ArrayList<itemOwnerModel> arrayList;
    RecyclerView recyclerView;

    int[] icons = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};
    String[] iconsName = {"Chrome", "Google Drive", "Facebook", "Twitter", "Google Maps", "WhatsApp", "LinkedIn", "Google+", "Instagram"};
    String[] name = {
            "Add Pen", "My Pen", "Add Student", "Get Report", "Show Features", "Add Features",
            "Show Dictionary", "Add Dictionary","Show Languages", "Add Language", "Show Teachers", "Add Teachers"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_main);
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/

       // getSupportActionBar().setLogo(R.drawable.logo_small);
        recyclerView = findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();


        userName = extras.getString("EMAIL");
        String[] parts = userName.split("@");
        String first = parts [0];
        String second = parts [1];
        getSupportActionBar().setTitle("Hi ! " + first);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff00DDED));


        cursor = db.checkUserRole(userName);
        //  String a = user.getType();
        if (cursor.moveToFirst()) {
            UserType = cursor.getString(0);
        }
        switch (UserType) {
            case "Admin":

                Toast.makeText(UsersListActivity.this, " Toast " + UserType, Toast.LENGTH_SHORT).show();

                break;

            case "Owner":
                addServices();
                Toast.makeText(UsersListActivity.this, " Toast Owner", Toast.LENGTH_SHORT).show();

                break;


        }
           /* if(UserType.equals("Admin"))
            {}*/
        //   Toast.makeText(UsersListActivity.this, " Toast " + UserType, Toast.LENGTH_SHORT).show();

        // and get whatever type user account id is


    }

    private void addServices() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < name.length; i++) {
            itemOwnerModel itemModel = new itemOwnerModel();
            itemModel.setName(name[i]);
            itemModel.setImage(icons[i]);
            arrayList.add(itemModel);

        }
        ServicesOwnerAdapter adapter = new ServicesOwnerAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }
}


