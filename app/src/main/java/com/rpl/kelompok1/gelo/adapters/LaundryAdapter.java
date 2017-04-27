package com.rpl.kelompok1.gelo.adapters;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.Laundry;

import java.util.List;

/**
 * Created by Lenovo on 08/04/2017.
 */

public class LaundryAdapter extends ArrayAdapter<Laundry> {
    private List<Laundry> listLaundry;
    private Activity context;
    public AppCompatTextView textViewName;
    public AppCompatTextView textViewEmail;
    public AppCompatTextView textViewAlamat;
    public AppCompatTextView textViewTelepon;


    public LaundryAdapter(Activity context, List<Laundry> listLaundry) {
        super(context, R.layout.item_list, listLaundry);
        this.context = context;
        this.listLaundry = listLaundry;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list, null, true);

        textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
        textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
        textViewAlamat = (AppCompatTextView) view.findViewById(R.id.textViewAlamat);
        textViewTelepon = (AppCompatTextView) view.findViewById(R.id.textViewTelepon);

        //Laundry laundry = listLaundry.get(position);
        textViewName.setText(listLaundry.get(position).getName());
        textViewEmail.setText(listLaundry.get(position).getEmail());
        textViewAlamat.setText(listLaundry.get(position).getAlamat());
        textViewTelepon.setText(listLaundry.get(position).getTelepon());

        return view;
    }
}
