package com.rpl.kelompok1.gelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPromo;
    private Button btnPeta;
    private Button btnInformasiTempatLaundry;
    private Button btnPesanKeluhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_customer);
        btnPromo = (Button) findViewById(R.id.btnPromo);
        btnPeta = (Button) findViewById(R.id.btnPeta);
        btnInformasiTempatLaundry=(Button) findViewById(R.id.btnInformasiTempatLaundry);
        btnPesanKeluhan = (Button) findViewById(R.id.btnPesanKeluhan);

    }



    public void onClick(View view) {
        if (view == btnPromo) {
            startActivity(new Intent(MenuCustomerActivity.this, PromoActivity.class));

        }
        if (view == btnPeta) {
            startActivity(new Intent(MenuCustomerActivity.this, MapsActivity.class));

        }
        if (view == btnInformasiTempatLaundry) {
            startActivity(new Intent(MenuCustomerActivity.this, InformasiJasaLaundryActivity.class));

        }
        if (view == btnPesanKeluhan) {
            startActivity(new Intent(MenuCustomerActivity.this, PesanKeluhanActivity.class));

        }




    }}
