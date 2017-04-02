package com.rpl.kelompok1.gelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdminActivity extends AppCompatActivity {
    private Button btnPromo;
    private Button btnTambahPemilikJasa;
    private Button btnLaporanTransaksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        btnPromo = (Button) findViewById(R.id.btnPromo);
        btnTambahPemilikJasa = (Button) findViewById(R.id.btnTambahPemilikJasa);
        btnLaporanTransaksi=(Button) findViewById(R.id.btnLaporanTransaksi);

    }



    public void onClick(View view) {
        if (view == btnPromo) {
            startActivity(new Intent(MenuAdminActivity.this, PromoActivity.class));

        }
        if (view == btnTambahPemilikJasa) {
            startActivity(new Intent(MenuAdminActivity.this, InformasiJasaLaundryActivity.class));

        }
        if (view == btnLaporanTransaksi) {
            startActivity(new Intent(MenuAdminActivity.this, LaporanActivity.class));


        }




    }}
