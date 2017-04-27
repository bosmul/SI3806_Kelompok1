package com.rpl.kelompok1.gelo.activities;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.rpl.kelompok1.gelo.adapters.UserAdapter;
import com.rpl.kelompok1.gelo.adapters.UserAdapter;
import com.rpl.kelompok1.gelo.models.User;
import com.rpl.kelompok1.gelo.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private AppCompatTextView textViewName;
    private ListView listViewUser;
    private List<User> listUser;
    private UserAdapter mUserAdapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    FirebaseUser user;
    Query query;

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
                }
                //creating adapter
                mUserAdapter = new UserAdapter(UserListActivity.this, listUser);
                //attaching adapter to the listview
                listViewUser.setAdapter(mUserAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initViews();
        initObjects();
        mDatabase = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        query = mDatabase.orderByChild("id").equalTo(user.getUid());

        listViewUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user=listUser.get(i);
                showUpdateDialog(user.getId(), user.getEmail());
                return true;
            }
        });
    }

    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        listViewUser = (ListView) findViewById(R.id.listViewUser);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUser = new ArrayList<>();

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
    }

    private boolean updateUser(String id, String name, String email, String telepon) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("user").child(id);

        //updating artist
        User user= new User(id, name, email, telepon);
        dR.setValue(user);
        Toast.makeText(getApplicationContext(), "Order Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private void showUpdateDialog(final String id, final String email) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_profile_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextNama = (EditText) dialogView.findViewById(R.id.editTextNama);
        final EditText editTextTelepon = (EditText) dialogView.findViewById(R.id.editTextTelepon);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);

        dialogBuilder.setTitle(id);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editTextNama.getText().toString().trim();
                String telepon = editTextTelepon.getText().toString().trim();
                if (!TextUtils.isEmpty(nama)) {
                    updateUser(id, nama, email, telepon);
                    b.dismiss();
                }
            }
        });
    }

}
