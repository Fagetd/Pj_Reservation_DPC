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
public class MembresFragment extends Fragment {
    private static final String TAG = "MembresFragment";
    private Button bt_membres;


    public MembresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_membres, container, false);
        bt_membres = view.findViewById(R.id.bt_membres);

        return view;
    }
}