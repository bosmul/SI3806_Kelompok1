package com.rpl.kelompok1.gelo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.adapters.LaundryAdapter;
import com.rpl.kelompok1.gelo.models.Laundry;

import java.util.ArrayList;
import java.util.List;

public class LaundryListActivity extends AppCompatActivity {
    private ListView listViewLaundry;
    private List<Laundry> listLaundry;
    private LaundryAdapter mLaundryAdapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listLaundry.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Laundry laundry = postSnapshot.getValue(Laundry.class);
                    listLaundry.add(laundry);
                }

                mLaundryAdapter = new LaundryAdapter(LaundryListActivity.this, listLaundry);
                listViewLaundry.setAdapter(mLaundryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_list);
        getSupportActionBar().hide();

        listViewLaundry = (ListView) findViewById(R.id.listViewLaundry);

        listLaundry = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference("laundry");

        listViewLaundry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Laundry laundry = listLaundry.get(i);

                String alamat = laundry.getAlamat();
                String id = laundry.getId();
                String nama = laundry.getName();
                String nomor = laundry.getTelepon();
                Intent setalamat = new Intent(LaundryListActivity.this, RegisterActivity.class);
                setalamat.putExtra("alamat", alamat);
                setalamat.putExtra("id", id);
                setalamat.putExtra("nama", nama);
                setalamat.putExtra("nomor", nomor);
                setResult(RESULT_OK, setalamat);
                finish();
            }
        });
    }
}
