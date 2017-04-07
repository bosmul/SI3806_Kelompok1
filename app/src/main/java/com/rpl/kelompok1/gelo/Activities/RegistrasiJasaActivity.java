package com.rpl.kelompok1.gelo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rpl.kelompok1.gelo.R;

public class RegistrasiJasaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registrasi_jasa);}

    public void login(View view){
        Intent toLogin = new Intent(RegistrasiJasaActivity.this, LoginActivity.class);
        startActivity(toLogin);
    }
}
