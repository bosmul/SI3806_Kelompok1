package com.rpl.kelompok1.gelo.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.adapters.LaundryAdapter;
import com.rpl.kelompok1.gelo.models.Laundry;
import com.rpl.kelompok1.gelo.models.Order;
import com.rpl.kelompok1.gelo.models.User;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{
    private List<User> listUser;
    TextInputEditText alamat, laundry, parfurmET;
    TextInputLayout alamatLayout, laundryLayout, parfurmLayout;
    FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    String idOrder, idLaundry,idUser, namaUser, namaLaundry, alamatLaundry, alamatUser,
            nomorUser, nomorLaundry, tipe, berat, harga, status, parfurm;
    Query query;
    Spinner tipeLaundry;
    AppCompatButton order;

    @Override
    protected void onStart() {
        super.onStart();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                listUser.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    User user = postSnapshot.getValue(User.class);
                    //adding artist to the list
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tipeLaundry = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipe_laundry, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tipeLaundry.setAdapter(adapter);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("order");
        user = firebaseAuth.getCurrentUser();

        order = (AppCompatButton) findViewById(R.id.appCompatButtonOrder);
        order.setOnClickListener(this);

        alamat = (TextInputEditText) findViewById(R.id.textInputEditTextUser);
        alamatLayout = (TextInputLayout) findViewById(R.id.textInputLayoutUser);

        laundry = (TextInputEditText) findViewById(R.id.textInputEditTextLaundry);
        laundryLayout = (TextInputLayout) findViewById(R.id.textInputLayoutLaundry);

        parfurmET = (TextInputEditText) findViewById(R.id.textInputEditTextParfurm);
        parfurmLayout = (TextInputLayout) findViewById(R.id.textInputLayoutParfurm);

        alamat.setOnClickListener(this);
        laundry.setOnClickListener(this);

        listUser = new ArrayList<>();

        query =  FirebaseDatabase.getInstance().getReference("user").orderByChild("id").equalTo(user.getUid());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                alamatUser=data.getStringExtra("alamat");
                alamat.setText(alamatUser);
            }
        } if (requestCode == 2) {
            if(resultCode == RESULT_OK){
                alamatLaundry=data.getStringExtra("alamat");
                idLaundry=data.getStringExtra("id");
                namaLaundry=data.getStringExtra("nama");
                nomorLaundry=data.getStringExtra("nomor");
                laundry.setText(alamatLaundry);
            }
        }
    }

    private void writeNewOrder() {
        idOrder = mDatabase.push().getKey();
        idUser = user.getUid();
        tipe = tipeLaundry.getSelectedItem().toString();
        berat = "0";
        harga = "0";
        status = "dipesan";
        parfurm = parfurmET.getText().toString().trim();
        Order order = new Order(idOrder, idLaundry, idUser, namaUser, namaLaundry, alamatLaundry, alamatUser,
                nomorUser, nomorLaundry, tipe, berat, harga, status, parfurm);
        mDatabase.child(idOrder).setValue(order);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textInputEditTextUser:
                startActivityForResult(new Intent(OrderActivity.this, MapsActivity.class), 1);
                break;
            case R.id.textInputEditTextLaundry:
                startActivityForResult(new Intent(OrderActivity.this, LaundryListActivity.class), 2);
                break;
            case R.id.appCompatButtonOrder:
                writeNewOrder();
                finish();
                break;
        }
    }
}
