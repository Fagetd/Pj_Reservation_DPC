package com.example.dimit.pj_reservation_dpc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dimit on 02/03/2018.
 */

public class ListAdapterMateriel extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Libelle;
    ArrayList<String> Qte;



    public ListAdapterMateriel(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> libelle,
            ArrayList<String> qte

    )
    {
        this.context = context2;
        this.ID = id;
        this.Libelle = libelle;
        this.Qte = qte;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        ListAdapterMateriel.Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.itemsmateriel, null);

            holder = new ListAdapterMateriel.Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Libelle_TextView = (TextView) child.findViewById(R.id.textViewLIBELLE);
            holder.Qte_TextView = (TextView) child.findViewById(R.id.textViewQTE);


            child.setTag(holder);

        } else {

            holder = (ListAdapterMateriel.Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Libelle_TextView.setText(Libelle.get(position));
        holder.Qte_TextView.setText(Qte.get(position));

        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Libelle_TextView;
        TextView Qte_TextView;

    }
}
