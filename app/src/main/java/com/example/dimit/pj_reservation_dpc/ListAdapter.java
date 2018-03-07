package com.example.dimit.pj_reservation_dpc;

/**
 * Created by dimit on 28/02/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> Capacite;
    ArrayList<String> Description;
    ArrayList<String> Adresse;
    ArrayList<String> Codepostal;
    ArrayList<String> Ville;


    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> capacite,
            ArrayList<String> description,
            ArrayList<String> adresse,
            ArrayList<String> codepostal,
            ArrayList<String> ville
    )
    {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.Capacite = capacite;
        this.Description = description;
        this.Adresse = adresse;
        this.Codepostal = codepostal;
        this.Ville = ville;
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

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewNAME);
            holder.CapaciteTextView = (TextView) child.findViewById(R.id.textViewCAPACITE);
            holder.DescriptionTextView = (TextView) child.findViewById(R.id.textViewDESCRIPTION);
            holder.AdresseTextView = (TextView) child.findViewById(R.id.textViewADRESSE);
            holder.CodepostalTextView = (TextView) child.findViewById(R.id.textViewCODEPOSTAL);
            holder.VilleTextView = (TextView) child.findViewById(R.id.textViewVILLE);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Name_TextView.setText(Name.get(position));
        holder.CapaciteTextView.setText(Capacite.get(position));
        holder.DescriptionTextView.setText(Description.get(position));
        holder.AdresseTextView.setText(Adresse.get(position));
        holder.CodepostalTextView.setText(Codepostal.get(position));
        holder.VilleTextView.setText(Ville.get(position));

        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView CapaciteTextView;
        TextView DescriptionTextView;
        TextView AdresseTextView;
        TextView CodepostalTextView;
        TextView VilleTextView;
    }

}