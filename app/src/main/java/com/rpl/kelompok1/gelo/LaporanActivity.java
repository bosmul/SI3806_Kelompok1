package com.rpl.kelompok1.gelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaporanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_laporan);
    }
}
