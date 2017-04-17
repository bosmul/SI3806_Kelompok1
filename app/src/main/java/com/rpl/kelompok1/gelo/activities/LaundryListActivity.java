package com.rpl.kelompok1.gelo.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
//    private DatabaseHelper databaseHelper;



    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewLaundry = (RecyclerView) findViewById(R.id.recyclerViewLaundry);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listLaundry = new ArrayList<>();
        mLaundryRecyclerAdapter = new LaundryRecyclerAdapter(listLaundry);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewLaundry.setLayoutManager(mLayoutManager);
        recyclerViewLaundry.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLaundry.setHasFixedSize(true);
        recyclerViewLaundry.setAdapter(mLaundryRecyclerAdapter);
//        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_list);

        initViews();
        initObjects();
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listLaundry.clear();
//                listLaundry.addAll(databaseHelper.getAllLaundry());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mLaundryRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
