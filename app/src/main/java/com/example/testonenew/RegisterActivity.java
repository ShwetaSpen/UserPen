package com.example.testonenew;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;

import com.example.testonenew.DBHelper.DatabaseHelper;
import com.example.testonenew.helper.InputValidation;
import com.example.testonenew.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;
    User user;
    AppCompatButton appCompatButtonRegister;
    NestedScrollView nestedScrollView;
    LinearLayoutCompat linear_main;
    DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    DatePickerDialog.OnDateSetListener mdateListner;
    TextInputEditText textInputEditTextName, textInputEditTextPassword, textInputEditTextLastName,
            textInputEditTextDOB, textInputEditTextContact, textInputEditTextAddress, textInputEditTextEmail;
    TextInputLayout textInputLayoutName,
            textInputLayoutPassword, textInputLayoutLastName, textInputLayoutEmail,
            textInputLayoutDOB, textInputLayoutContact, textInputLayoutAddress;
    String name,email;
    Spinner staticSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        initViews();
        initListeners();
        initObjects();

        staticSpinner = findViewById(R.id.static_spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        textInputEditTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this, R.style.DialogTheme,
                        mdateListner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
        mdateListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d("PRINT", "ON dATESET:  DATE: " + year + "/" + +month + "/" + dayOfMonth);
                String date = month + 1 + "/" + dayOfMonth + "/" + year;
                textInputEditTextDOB.setText(date);

            }
        };
        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                }
                imm.hideSoftInputFromWindow(linear_main.getWindowToken(), 0);
                name = textInputEditTextName.getText().toString().trim();
                email = textInputEditTextEmail.getText().toString().trim();
                String password = textInputEditTextPassword.getText().toString().trim();
              String lastname = textInputEditTextLastName.getText().toString().trim();
              String DOB = textInputEditTextDOB.getText().toString();
               String contact = textInputEditTextContact.getText().toString();
                String address = textInputEditTextAddress.getText().toString();
                    String s = staticSpinner.getSelectedItem().toString();
                postDataToSQLite();




                Boolean val = databaseHelper.validate(email);

                if(val.equals(false)) {
/*
                    user.setName(textInputEditTextName.getText().toString().trim());
                    user.setEmail(textInputEditTextEmail.getText().toString().trim());
                    user.setPassword(textInputEditTextPassword.getText().toString().trim());
*/

                    user.setEmail(email);
                    user.setName(name);
                    user.setLastname(lastname);
                    user.setPassword(password);
                    user.setAddress(address);
                    user.setContact(contact);
                    user.setDOB(DOB);
                    user.setType(s);
                    // after adding the data we are displaying a toast message.
                    databaseHelper.addUser(user);
                    Toast.makeText(RegisterActivity.this, " Details Added", Toast.LENGTH_SHORT).show();
                    emptyInputEditText();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Email Already Exists", Toast.LENGTH_SHORT).show();
                }


//
//
//                // validating if the text fields are empty or not.
//                if (name.isEmpty() || password.isEmpty() || lastname.isEmpty() || email.isEmpty() || DOB.isEmpty() ||
//                        contact.isEmpty() || address.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    return;
//                }

              /*  Boolean val = databaseHelper.validate(email);

                if(val.equals(false)) {
                    // after adding the data we are displaying a toast message.
                    databaseHelper.addUser(email,name,lastname,password,DOB,contact,address,s);
                    Toast.makeText(MainActivity.this, "Student Details Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Roll Number Already Exists", Toast.LENGTH_SHORT).show();
                }
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");*/


            }
        });


    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextLastName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextContact.setText(null);
        textInputEditTextDOB.setText(null);
        textInputEditTextAddress.setText(null);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
    }


    private void initViews() {
        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        linear_main = findViewById(R.id.linear_main);
        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        textInputEditTextLastName = findViewById(R.id.textInputEditTextLastName);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextDOB = findViewById(R.id.textInputEditTextDOB);
        textInputEditTextContact = findViewById(R.id.textInputEditTextContact);
        textInputEditTextAddress = findViewById(R.id.textInputEditTextAddress);

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutLastName = findViewById(R.id.textInputLayoutLastName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutDOB = findViewById(R.id.textInputLayoutDOB);
        textInputLayoutContact = findViewById(R.id.textInputLayoutContact);
        textInputLayoutAddress = findViewById(R.id.textInputLayoutAddress);

        databaseHelper = new DatabaseHelper(RegisterActivity.this);

    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        //  appCompatTextViewLoginLink.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void postDataToSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        } else if ((!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, getString(R.string.error_message_lname))) ||
                (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email)))


        ) {

            return;

        } else if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (!inputValidation.isInputEditTextContact(textInputEditTextContact, textInputLayoutContact, getString(R.string.error_message_Contact))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextDOB, textInputLayoutDOB, getString(R.string.error_message_dob))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAddress, textInputLayoutAddress, getString(R.string.error_message_address))) {
            return;
        }
//        if (!inputValidation.isInputSpinnerFilled(staticSpinner, getString(R.string.error_message_address))) {
//            return;
//        }


//        if(textInputEditTextContact.length() == 10){
//            Toast.makeText(RegisterActivity.this, "Enter 10 digits", Toast.LENGTH_SHORT).show();
//            return;
//
//        }


    /*    if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
    */
    }

    @Override
    public void onClick(View v) {

    }
      /*  if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }
        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }*/


}

