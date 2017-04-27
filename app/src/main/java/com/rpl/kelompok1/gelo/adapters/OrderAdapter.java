package com.rpl.kelompok1.gelo.adapters;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.models.Order;

import java.util.List;

/**
 * Created by Lenovo on 26/04/2017.
 */

public class OrderAdapter extends ArrayAdapter<Order> {
    private List<Order> listOrder;
    private Activity context;
    public AppCompatTextView textViewOrder;
    public AppCompatTextView textViewTipe;
    public AppCompatTextView textViewAlamat;
    public AppCompatTextView textViewHarga;
    public AppCompatTextView textViewStatus;
    public AppCompatTextView textViewNama;
    public AppCompatTextView textViewBerat;
    public AppCompatTextView textViewNomor;


    public OrderAdapter(Activity context, List<Order> listOrder) {
        super(context, R.layout.order_list, listOrder);
        this.context = context;
        this.listOrder = listOrder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.order_list, null, true);

        textViewOrder = (AppCompatTextView) view.findViewById(R.id.textViewOrder);
        textViewTipe = (AppCompatTextView) view.findViewById(R.id.textViewTipe);
        textViewAlamat = (AppCompatTextView) view.findViewById(R.id.textViewAlamat);
        textViewHarga = (AppCompatTextView) view.findViewById(R.id.textViewHarga);
        textViewStatus = (AppCompatTextView) view.findViewById(R.id.textViewStatus);
        textViewNama = (AppCompatTextView) view.findViewById(R.id.textViewNama);
        textViewBerat = (AppCompatTextView) view.findViewById(R.id.textViewBerat);
        textViewNomor = (AppCompatTextView) view.findViewById(R.id.textViewNomor);

        //Laundry laundry = listLaundry.get(position);
        textViewOrder.setText(listOrder.get(position).getIdOrder());
        textViewTipe.setText(listOrder.get(position).getTipe());
        textViewAlamat.setText(listOrder.get(position).getAlamatLaundry());
        textViewHarga.setText(listOrder.get(position).getHarga());
        textViewStatus.setText(listOrder.get(position).getStatus());
        textViewNama.setText(listOrder.get(position).getNamaLaundry());
        textViewBerat.setText(listOrder.get(position).getBerat());
        textViewNomor.setText(listOrder.get(position).getNomorUser());

        return view;
    }
}
