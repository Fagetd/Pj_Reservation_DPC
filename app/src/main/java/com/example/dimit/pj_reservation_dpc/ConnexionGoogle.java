package com.example.dimit.pj_reservation_dpc;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dimit on 13/02/2018.
 */

public class ConnexionGoogle extends DialogFragment {
    private Button bt_ok,bt_non;
    private static final String TAG = "ConnexionGoogle";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_my_custom, container, false);
        bt_ok=view.findViewById(R.id.bt_ok);
        bt_non=view.findViewById(R.id.bt_non);
        //edInput =view.findViewById(R.id.input);

        bt_non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: capturing input");
                Intent i = (new Intent(getActivity(),ProcessusConnexionGoogle.class));
                startActivity(i);


            }
        });
        return view;
    }

}

