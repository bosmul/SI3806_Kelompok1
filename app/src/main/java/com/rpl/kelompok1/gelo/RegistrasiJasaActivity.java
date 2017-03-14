package com.rpl.kelompok1.gelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistrasiJasaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_jasa);}

    public void login(View view){
        Intent toLogin = new Intent(RegistrasiJasaActivity.this, LoginJasaActivity.class);
        startActivity(toLogin);
    }
}
