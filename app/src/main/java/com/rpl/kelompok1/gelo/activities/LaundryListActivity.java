package com.rpl.kelompok1.gelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.adapters.LaundryRecyclerAdapter;
import com.rpl.kelompok1.gelo.models.Laundry;

import java.util.ArrayList;
import java.util.List;

public class LaundryListActivity extends AppCompatActivity {
    private AppCompatActivity activity = LaundryListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewLaundry;
    private List<Laundry> listLaundry;
    private LaundryRecyclerAdapter mLaundryRecyclerAdapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                listLaundry.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Laundry laundry = postSnapshot.getValue(Laundry.class);
                    //adding artist to the list
                    listLaundry.add(laundry);
                }
                //creating adapter
                mLaundryRecyclerAdapter = new LaundryRecyclerAdapter(listLaundry);
                //attaching adapter to the listview
                recyclerViewLaundry.setAdapter(mLaundryRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewLaundry = (RecyclerView) findViewById(R.id.recyclerViewLaundry);
    }

    private void initObjects() {
        listLaundry = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewLaundry.setLayoutManager(mLayoutManager);
        recyclerViewLaundry.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLaundry.setHasFixedSize(true);
        recyclerViewLaundry.setAdapter(mLaundryRecyclerAdapter);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_list);

        initViews();
        initObjects();
        mDatabase = FirebaseDatabase.getInstance().getReference("laundry");


    }
}
