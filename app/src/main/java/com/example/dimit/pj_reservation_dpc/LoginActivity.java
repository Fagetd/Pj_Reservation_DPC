package com.example.dimit.pj_reservation_dpc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {
      private Button bt_connexion;
      private EditText ed_login;
      private EditText ed_mdp;

      private ProgressDialog progressDialog;

      private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_log_in);
        ed_login = findViewById(R.id.ed_login);
        ed_mdp = findViewById(R.id.ed_password_login);
        bt_connexion = findViewById(R.id.bt_connexion);
        progressDialog = new ProgressDialog(this);


        bt_connexion.setOnClickListener(this);
        findViewById(R.id.bt_inscription).setOnClickListener(this);

    }


    private void userLogin() {
        String email = ed_login.getText().toString().trim();
        String mdp = ed_mdp.getText().toString().trim();

        if (mdp.isEmpty()){
           ed_mdp.setError("Veuillez rentrer votre mot de passe");
           ed_mdp.requestFocus();
            return;
        }
        if (email.isEmpty()){
            ed_login.setError("Veuillez rentrer votre login");
            ed_login.requestFocus();
            return;
        }

        progressDialog.setMessage("Connexion en cours ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,mdp).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
           }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_inscription:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.bt_connexion:
                userLogin();
                break;


        }
    }
}
