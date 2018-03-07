package com.example.dimit.pj_reservation_dpc;


import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParametresFragment extends Fragment {
    private static final String TAG = "ParametresFragment";
    private Button bt_parametres;


    public ParametresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parametres, container, false);
        bt_parametres = view.findViewById(R.id.bt_parametres);

        return view;
    }
}