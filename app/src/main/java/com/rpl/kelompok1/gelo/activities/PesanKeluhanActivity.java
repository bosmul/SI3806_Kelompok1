package com.rpl.kelompok1.gelo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.Keluhan;
import com.rpl.kelompok1.gelo.models.User;

import java.util.ArrayList;
import java.util.List;

public class PesanKeluhanActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText keluhan;
    private TextView laundry;
    private Button kirim;
    private List<User> listUser;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private String idKeluhan, idLaundry,idUser, namaUser, namaLaundry,
            nomorUser, nomorLaundry, isi, feedback;
    private Query query;

    @Override
    protected void onStart() {
        super.onStart();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listUser.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    listUser.add(user);

                    namaUser = user.getName();
                    nomorUser = user.getTelepon();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                idLaundry=data.getStringExtra("id");
                namaLaundry=data.getStringExtra("nama");
                nomorLaundry=data.getStringExtra("nomor");
                laundry.setText(namaLaundry);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pesan_keluhan);

        keluhan = (EditText) findViewById(R.id.editeTextKeluhan);

        laundry = (TextView) findViewById(R.id.textViewLaundry);
        laundry.setOnClickListener(this);

        kirim = (Button) findViewById(R.id.btnKirim);
        kirim.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference("keluhan");
        query =  FirebaseDatabase.getInstance().getReference("user").orderByChild("id").equalTo(user.getUid());

        listUser = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewLaundry:
                startActivityForResult(new Intent(PesanKeluhanActivity.this, LaundryListActivity.class), 1);
                break;
            case R.id.btnKirim:
                writeNewKeluhan();
                finish();
                break;
        }
    }

    private void writeNewKeluhan() {
        idKeluhan = mDatabase.push().getKey();
        idUser = user.getUid();
        isi = keluhan.getText().toString().trim();
        feedback = "";
        Keluhan keluhan = new Keluhan(idKeluhan,idUser, idLaundry, namaUser, namaLaundry,
                nomorUser, nomorLaundry, isi, feedback);
        mDatabase.child(idKeluhan).setValue(keluhan);
    }
}
