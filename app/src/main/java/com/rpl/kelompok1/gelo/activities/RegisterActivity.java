package com.rpl.kelompok1.gelo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.User;
import com.rpl.kelompok1.gelo.helpers.InputValidation;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutAlamat;
    private TextInputLayout textInputLayoutTelepon;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextAlamat;
    private TextInputEditText textInputEditTextTelepon;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private ProgressDialog progressDialog;

    List<User> mUserList;
    private FirebaseAuth firebaseAuth;

    private InputValidation inputValidation;
    private User user;
    private DatabaseReference mDatabase;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String alamat=data.getStringExtra("alamat");
                textInputEditTextAlamat.setText(alamat);
            }
        }
    }

    private void writeNewUser(String userId, String name, String email, String alamat, String telepon) {
        User user = new User(userId, name, email, alamat, telepon);
        mDatabase.child(userId).setValue(user);
    }

    private void onAuthSuccess(FirebaseUser user) {
        String nama =textInputEditTextName.getText().toString();
        String telepon = textInputEditTextTelepon.getText().toString();
        String alamat = textInputEditTextAlamat.getText().toString();
        // Write new user
        writeNewUser(user.getUid(), nama, user.getEmail(), alamat, telepon);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutTelepon = (TextInputLayout) findViewById(R.id.textInputLayoutTelepon);
        textInputLayoutAlamat = (TextInputLayout) findViewById(R.id.textInputLayoutAlamat);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextAlamat = (TextInputEditText) findViewById(R.id.textInputEditTextAlamat);
        textInputEditTextTelepon = (TextInputEditText) findViewById(R.id.textInputEditTextTelepon);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        textInputEditTextAlamat.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(RegisterActivity.this);
        user = new User();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        mUserList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("user");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister:
                registerUser();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
            case R.id.textInputEditTextAlamat:
                startActivityForResult(new Intent(RegisterActivity.this, MapsActivity.class), 1);
                break;
        }
    }

    private void registerUser(){
        String email = textInputEditTextEmail.getText().toString().trim();
        String password  = textInputEditTextPassword.getText().toString().trim();

        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAlamat, textInputLayoutAlamat, getString(R.string.error_message_address))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTelepon, textInputLayoutTelepon, getString(R.string.error_message_telephone))) {
            return;
        }
        if (!inputValidation.isInputEditTextTelepon(textInputEditTextTelepon, textInputLayoutTelepon, getString(R.string.error_message_telephoneinvalid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            onAuthSuccess(task.getResult().getUser());
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                        emptyInputEditText();
                    }
                });
    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextAlamat.setText(null);
        textInputEditTextTelepon.setText(null);
    }
}
