package com.rpl.kelompok1.gelo.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rpl.kelompok1.gelo.R;

public class StatusActivity extends AppCompatActivity implements OnClickListener{

    private Button btnPemilikJasa;
    private Button btnCustomer;
    private Button btnAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_status);
        btnPemilikJasa = (Button) findViewById(R.id.btnPemilikJasa);
        btnPemilikJasa.setOnClickListener(this);
        btnCustomer = (Button) findViewById(R.id.btnCustomer);
        btnCustomer.setOnClickListener(this);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view == btnCustomer) {
            startActivity(new Intent(StatusActivity.this, LoginActivity.class));

        }
        if (view == btnPemilikJasa) {
            startActivity(new Intent(StatusActivity.this, LoginJasaActivity.class));

        }
        if (view == btnAdmin) {
            startActivity(new Intent(StatusActivity.this, LoginKhususActivity.class));

        }
    }

}

