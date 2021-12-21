package com.example.testonenew;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonenew.DBHelper.DatabaseHelper;
import com.example.testonenew.Owner.AddPen;
import com.example.testonenew.model.itemOwnerModel;

import java.util.ArrayList;


public class UsersListActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(UsersListActivity.this);
    String userName;
    String UserType;
    Cursor cursor;
    ServicesOwnerAdapter adapter;

    ArrayList<itemOwnerModel> arrayList;
    RecyclerView recyclerView;
    Dialog myPenDialog;
    ImageView imgClose;

    int[] icons = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};
    String[] iconsName = {"Chrome", "Google Drive", "Facebook", "Twitter", "Google Maps", "WhatsApp", "LinkedIn", "Google+", "Instagram"};
    String[] name = {
            "Add Pen", "My Pen", "Add Student", "Get Report", "Show Features", "Add Features",
            "Show Dictionary", "Add Dictionary", "Show Languages", "Add Language", "Show Teachers", "Add Teachers"};
    String[] nameAdmin = {
            "Admin", "Admin Pen", "Add Student", "Get Report", "Show Features", "Add Features",
            "Show Dictionary", "Add Dictionary", "Show Languages", "Add Language", "Show Teachers", "Add Teachers"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_main);
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/

        // getSupportActionBar().setLogo(R.drawable.logo_small);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        arrayList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        userName = extras.getString("EMAIL");
        String[] parts = userName.split("@");
        String first = parts[0];
        String second = parts[1];
        getSupportActionBar().setTitle("Hi ! " + first);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff00DDED));


        cursor = db.checkUserRole(userName);
        //  String a = user.getType();
        if (cursor.moveToFirst()) {
            UserType = cursor.getString(0);
        }
        switch (UserType) {
            case "Admin":
                addServicesAdmin();
                Toast.makeText(UsersListActivity.this, " Toast " + UserType, Toast.LENGTH_SHORT).show();
                break;

            case "Owner":
                addServices();
                Toast.makeText(UsersListActivity.this, " Toast Owner", Toast.LENGTH_SHORT).show();
                break;

            case "Student":
                Toast.makeText(UsersListActivity.this, " Toast " + UserType, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //for dialog and click events on the OWNER card
    private void changeItem(int position, String clicked) {
        // arrayList.get(position).changeText("Clicked");

        myPenDialog = new Dialog(this);
        myPenDialog.setContentView(R.layout.mypen_dialog);
        myPenDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgClose = myPenDialog.findViewById(R.id.imageViewClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPenDialog.dismiss();
            }
        });
        if (position == 0) {
            Intent intent = new Intent(getApplicationContext(), AddPen.class);
            startActivity(intent);
        }

        if (position == 1) {
            myPenDialog.show();
            //Toast.makeText(this,"you pressed "+position,Toast.LENGTH_SHORT).show();
        }
        if (position == 4 || position == 5 || position == 6 || position == 7 || position == 8 || position == 9 || position == 10 || position == 11) {
            //  imgClose.setVisibility(View.GONE);
            myPenDialog.show();
        }

        adapter.notifyItemChanged(position);
    }

    private void addServices() {
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < name.length; i++) {
            itemOwnerModel itemModel = new itemOwnerModel(icons[i], name[i]);
            itemModel.setName(name[i]);
            itemModel.setImage(icons[i]);
            arrayList.add(itemModel);

        }
        adapter = new ServicesOwnerAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClicklistener(
                new ServicesOwnerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        changeItem(position, "Clicked");
                    }
                });
    }

    private void addServicesAdmin() {
        for (int i = 0; i < nameAdmin.length; i++) {
            itemOwnerModel itemModel = new itemOwnerModel(icons[i], nameAdmin[i]);
            itemModel.setName(nameAdmin[i]);
            itemModel.setImage(icons[i]);
            arrayList.add(itemModel);

        }
        adapter = new ServicesOwnerAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClicklistener(
                new ServicesOwnerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        changeItem(position, "Clicked");
                    }
                });
    }
}

