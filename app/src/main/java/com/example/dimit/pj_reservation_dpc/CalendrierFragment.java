//package com.example.dimit.pj_reservation_dpc;
//
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.DialogFragment;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
////import android.support.v4.app.Fragment;
//import android.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import javax.annotation.Nullable;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class CalendrierFragment extends Fragment {
//    private Button synchronisation;
//    private static final String TAG = "CalendrierFragment";
//    public CalendrierFragment() {
//        // Required empty public constructor
//    }
//
//    @Nullable
//
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_calendrier, container, false);
//        synchronisation = view.findViewById(R.id.bt_synchronisation);
//
//        synchronisation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG,"onClick: opening dialog");         // ConnexionGoogle message = new ConnexionGoogle();
//                ConnexionGoogle dialogue = new ConnexionGoogle();
//                dialogue.show(getFragmentManager(),"ConnexionGoogle");
//            }
//        });
//        return view;
//    }
//}
//
//


