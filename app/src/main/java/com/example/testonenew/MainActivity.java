package com.example.testonenew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.testonenew.DBHelper.DatabaseHelper;
import com.example.testonenew.helper.InputValidation;
import com.example.testonenew.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;


    private AppCompatTextView textViewLinkRegister;
    private AppCompatButton appCompatButtonLogin;
    private TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private LinearLayout linear_main_login;


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

        linear_main_login = findViewById(R.id.linear_main_login);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }


    private void initListeners() {
        textViewLinkRegister.setOnClickListener(this);
        textViewLinkRegister.setSelected(true);
        appCompatButtonLogin.setOnClickListener(this);


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


            /*    textViewLinkRegister.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                         //   textViewLinkRegister.setBackground(Color.parseColor("#FFFFFF"));
                        }
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            //finger was lifted
                        }
                        return false;
                    }
                });*/
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;


            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;





        }
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {




        // List<String> role = databaseHelper.checkRole(textInputEditTextEmail.getText().toString().trim());

               /* if(role.equals("Admin") )
                {
                    Toast.makeText(MainActivity.this, "  Admin", Toast.LENGTH_SHORT).show();

                }
            if(role.equals("Owner") )
            {
                Toast.makeText(MainActivity.this, "  Owner", Toast.LENGTH_SHORT).show();

            }
            if(role.equals("Student") )
            {
                Toast.makeText(MainActivity.this, "  Student", Toast.LENGTH_SHORT).show();

            }*/

            Intent accountsIntent = new Intent(getApplicationContext(), UsersListActivity.class);
           // Toast.makeText(MainActivity.this, " Welcome", Toast.LENGTH_SHORT).show();
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Toast.makeText(MainActivity.this, " Wrong Details", Toast.LENGTH_SHORT).show();

            // Snack Bar to show success message that record is wrong
            Snackbar.make(linear_main_login, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}