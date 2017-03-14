package com.rpl.kelompok1.gelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJasaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPromo;
    private Button btnPeta;
    private Button btnInformasiTempatLaundry;
    private Button btnPesananCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jasa);
        btnPromo = (Button) findViewById(R.id.btnPromo);
        btnPeta = (Button) findViewById(R.id.btnPeta);
        btnInformasiTempatLaundry=(Button) findViewById(R.id.btnInformasiTempatLaundry);
        btnPesananCustomer = (Button) findViewById(R.id.btnPesananCouumer);
    }
    public void onClick(View view) {
        if (view == btnPromo) {
            startActivity(new Intent(MenuJasaActivity.this, PromoActivity.class));

        }
        if (view == btnPeta) {
            startActivity(new Intent(MenuJasaActivity.this, MapsActivity.class));

        }
        if (view == btnPesananCustomer) {
            startActivity(new Intent(MenuJasaActivity.this, PesanKeluhanActivity.class));

        }

    }
}
