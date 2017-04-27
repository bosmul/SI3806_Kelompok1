package com.rpl.kelompok1.gelo.adapters;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.User;
import com.rpl.kelompok1.gelo.models.User;

import java.util.List;

/**
 * Created by Lenovo on 07/04/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private List<User> listUser;
    private Activity context;
    public AppCompatTextView textViewName;
    public AppCompatTextView textViewEmail;
    public AppCompatTextView textViewTelepon;
    public UserAdapter(Activity context, List<User> listUser) {
        super(context, R.layout.item_list, listUser);
        this.context = context;
        this.listUser = listUser;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list, null, true);

        textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
        textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
        textViewTelepon = (AppCompatTextView) view.findViewById(R.id.textViewTelepon);

        //User user = listUser.get(position);
        textViewName.setText(listUser.get(position).getName());
        textViewEmail.setText(listUser.get(position).getEmail());
        textViewTelepon.setText(listUser.get(position).getTelepon());

        return view;
    }
}
