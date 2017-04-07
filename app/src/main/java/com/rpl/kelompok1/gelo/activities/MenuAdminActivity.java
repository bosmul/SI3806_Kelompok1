package com.rpl.kelompok1.gelo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rpl.kelompok1.gelo.R;

public class MenuAdminActivity extends AppCompatActivity {
    private Button btnUser;
    private Button btnLaundry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_admin);
        btnUser = (Button) findViewById(R.id.btnListUser);
        btnLaundry = (Button) findViewById(R.id.btnListLaundry);
    }

    public void onClick(View view) {
        if (view == btnUser) {
            startActivity(new Intent(MenuAdminActivity.this, UserListActivity.class));
        }
        if (view == btnLaundry) {
            startActivity(new Intent(MenuAdminActivity.this, LaundryListActivity.class));
        }
    }
}
