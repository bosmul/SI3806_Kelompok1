package com.rpl.kelompok1.gelo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.Keluhan;
import com.rpl.kelompok1.gelo.models.User;

import java.util.ArrayList;
import java.util.List;

public class LihatKeluhanActivity extends AppCompatActivity{
    TextView laundry, isiKeluhan, feedbackTV;
    private List<Keluhan> listKeluhan;
    private List<User> listUser;

    FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    String idKeluhan, idLaundry,idUser, namaUser, namaLaundry,
            nomorUser, nomorLaundry, isi, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_keluhan);

        feedbackTV = (TextView) findViewById(R.id.textViewIsiFeedback);

        laundry = (TextView) findViewById(R.id.textViewLaundry);
        isiKeluhan = (TextView) findViewById(R.id.textViewIsiKeluhan);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("keluhan");
        user = firebaseAuth.getCurrentUser();

        listKeluhan = new ArrayList<>();
        listUser = new ArrayList<>();

        idKeluhan = getIntent().getStringExtra("idKeluhan");
        idLaundry = getIntent().getStringExtra("idLaundry");
        idUser = getIntent().getStringExtra("idUser");
        namaLaundry = getIntent().getStringExtra("namaLaundry");
        namaUser = getIntent().getStringExtra("namaUser");
        nomorLaundry = getIntent().getStringExtra("nomorLaundry");
        nomorUser = getIntent().getStringExtra("nomorUser");
        isi = getIntent().getStringExtra("isi");
        feedback = getIntent().getStringExtra("feedback");

        laundry.setText(namaLaundry);
        isiKeluhan.setText(isi);
        feedbackTV.setText(feedback);
    }
}
