package com.rpl.kelompok1.gelo.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.helpers.DatabaseHelper;
import com.rpl.kelompok1.gelo.helpers.InsertAdmin;

public class StartActivity extends AppCompatActivity implements OnClickListener {

    private Button btnReg;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);
        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
        InsertAdmin.insertAdminData(db);
    }

    @Override
    public void onClick(View v) {
        if (v == btnReg){
            startActivity(new Intent(StartActivity.this,StatusActivity.class));
        }
    }
}
