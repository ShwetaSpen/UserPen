package com.example.testonenew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.testonenew.DBHelper.DatabaseHelper;
import com.example.testonenew.helper.InputValidation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;


    private AppCompatTextView textViewLinkRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initViews();
        initListeners();
        initObjects();


    }


    private void initViews() {
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }


    private void initListeners() {
        textViewLinkRegister.setOnClickListener(this);

    }
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;*/
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}