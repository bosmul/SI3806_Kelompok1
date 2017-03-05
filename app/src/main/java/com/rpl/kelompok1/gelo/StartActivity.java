package com.rpl.kelompok1.gelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements OnClickListener {

    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnReg){
            startActivity(new Intent(StartActivity.this,StatusActivity.class));
        }
    }
}
