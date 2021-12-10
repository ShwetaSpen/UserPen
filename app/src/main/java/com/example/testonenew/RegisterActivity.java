package com.example.testonenew;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    User user;
    private final AppCompatActivity activity = RegisterActivity.this;

    AppCompatButton appCompatButtonRegister;
    NestedScrollView nestedScrollView;
    LinearLayoutCompat linear_main;
    DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    TextInputEditText textInputEditTextName, textInputEditTextPassword, textInputEditTextLastName,
            textInputEditTextDOB, textInputEditTextContact, textInputEditTextAddress, textInputEditTextEmail;
    TextInputLayout textInputLayoutName,
            textInputLayoutPassword,textInputLayoutLastName,textInputLayoutEmail,
            textInputLayoutDOB, textInputLayoutContact,textInputLayoutAddress;

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
        
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                }
                imm.hideSoftInputFromWindow(linear_main.getWindowToken(), 0);

//                String name = textInputEditTextName.getText().toString();
//                String password = textInputEditTextPassword.getText().toString();
//                String lastname = textInputEditTextLastName.getText().toString();
  //             String email = textInputEditTextEmail.getText().toString();
//                String DOB = textInputEditTextDOB.getText().toString();
//                String contact = textInputEditTextContact.getText().toString();
//                String address = textInputEditTextAddress.getText().toString();
//                String s = staticSpinner.getSelectedItem().toString();
//
//                // validating if the text fields are empty or not.
//                if (name.isEmpty() || password.isEmpty() || lastname.isEmpty() || email.isEmpty() || DOB.isEmpty() ||
//                        contact.isEmpty() || address.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                Boolean val = databaseHelper.validate(email);

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
                courseDescriptionEdt.setText("");



            }
        });


    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
      //  appCompatTextViewLoginLink.setOnClickListener(this);

    }

    private void initViews() {
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        linear_main = (LinearLayoutCompat) findViewById(R.id.linear_main);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextLastName = (TextInputEditText) findViewById(R.id.textInputEditTextLastName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextDOB = (TextInputEditText) findViewById(R.id.textInputEditTextDOB);
        textInputEditTextContact = (TextInputEditText) findViewById(R.id.textInputEditTextContact);
        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddress);

        textInputLayoutName =(TextInputLayout)findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword = (TextInputEditText) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutLastName = (TextInputEditText) findViewById(R.id.textInputLayoutLastName);
        textInputLayoutEmail = (TextInputEditText) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutDOB = (TextInputEditText) findViewById(R.id.textInputLayoutDOB);
        textInputLayoutContact = (TextInputEditText) findViewById(R.id.textInputLayoutContact);
        textInputLayoutAddress = (TextInputEditText) findViewById(R.id.textInputLayoutAddress);





        databaseHelper = new DatabaseHelper(RegisterActivity.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

//            case R.id.appCompatTextViewLoginLink:
//                finish();
//                break;
        }
    }

    private void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, , getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
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
        }


    }
}
