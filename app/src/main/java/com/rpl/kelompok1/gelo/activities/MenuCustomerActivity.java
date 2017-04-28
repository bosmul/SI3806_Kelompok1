package com.rpl.kelompok1.gelo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.rpl.kelompok1.gelo.R;

import java.io.IOException;

public class MenuCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOrder;
    private Button btnPeta;
    private Button btnInformasiTempatLaundry;
    private Button btnPesanKeluhan;
    private Button btnLihatKeluhan;
    private Button btnSignOut;

    private TextView update;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_customer);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnPeta = (Button) findViewById(R.id.btnPeta);
        btnInformasiTempatLaundry=(Button) findViewById(R.id.btnInformasiTempatLaundry);
        btnPesanKeluhan = (Button) findViewById(R.id.btnPesanKeluhan);
        btnLihatKeluhan = (Button) findViewById(R.id.btnLihatkeluhan);
        btnSignOut = (Button) findViewById(R.id.btnSignOut);
        update = (TextView) findViewById(R.id.textViewUpdate);

    }

    public void onClick(View view) {
        if (view == btnOrder) {
            startActivity(new Intent(MenuCustomerActivity.this, OrderActivity.class));
        }
        if (view == btnPeta) {
            startActivity(new Intent(MenuCustomerActivity.this, OrderListActivity.class));
        }
        if (view == btnInformasiTempatLaundry) {
            startActivity(new Intent(MenuCustomerActivity.this, LaundryListActivity.class));
        }
        if (view == btnPesanKeluhan) {
            startActivity(new Intent(MenuCustomerActivity.this, PesanKeluhanActivity.class));
        }
        if (view == btnLihatKeluhan) {
            startActivity(new Intent(MenuCustomerActivity.this, KeluhanListActivity.class));
        }
        if (view == update) {
            startActivity(new Intent(MenuCustomerActivity.this, UserListActivity.class));
        }
        if (view == btnSignOut) {
           firebaseAuth.signOut();
            startActivity(new Intent(MenuCustomerActivity.this, LoginActivity.class));
        }
    }}
