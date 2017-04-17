package com.rpl.kelompok1.gelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rpl.kelompok1.gelo.R;

public class PesanKeluhanActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pesan_keluhan);
    }
}
