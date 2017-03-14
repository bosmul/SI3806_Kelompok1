package com.rpl.kelompok1.gelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginJasaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_jasa);
    }

    public void register(View view){
        Intent toReg = new Intent(LoginJasaActivity.this, RegistrasiJasaActivity.class);
        startActivity(toReg);
    }
    public void login(View view){
        Intent toReg = new Intent(LoginJasaActivity.this, MenuJasaActivity.class);
        startActivity(toReg);
    }

}
