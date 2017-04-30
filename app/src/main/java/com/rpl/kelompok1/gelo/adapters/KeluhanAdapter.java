package com.rpl.kelompok1.gelo.adapters;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.Keluhan;

import java.util.List;

/**
 * Created by Alfian on 28/04/2017.
 */

public class KeluhanAdapter extends ArrayAdapter<Keluhan> {

    private List<Keluhan> listKeluhan;
    private Activity context;
    public AppCompatTextView textViewKeluhan;
    public AppCompatTextView textViewIsi;
    public AppCompatTextView textViewNama;
    public AppCompatTextView textViewNomor;

    public KeluhanAdapter(Activity context, List<Keluhan> listKeluhan) {
        super(context, R.layout.keluhan_list, listKeluhan);
        this.context = context;
        this.listKeluhan = listKeluhan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.keluhan_list, null, true);

        textViewKeluhan = (AppCompatTextView) view.findViewById(R.id.textViewKeluhan);
        textViewIsi = (AppCompatTextView) view.findViewById(R.id.textViewIsi);
        textViewNama = (AppCompatTextView) view.findViewById(R.id.textViewNama);
        textViewNomor = (AppCompatTextView) view.findViewById(R.id.textViewNomor);

        textViewKeluhan.setText(listKeluhan.get(position).getIdKeluhan());
        textViewIsi.setText(listKeluhan.get(position).getIsi());
        textViewNama.setText(listKeluhan.get(position).getNamaLaundry());
        textViewNomor.setText(listKeluhan.get(position).getNomorUser());

        return view;
    }
}
