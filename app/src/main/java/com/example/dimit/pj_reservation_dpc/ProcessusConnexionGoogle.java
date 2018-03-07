package com.example.dimit.pj_reservation_dpc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class ProcessusConnexionGoogle extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private GoogleApiClient GoogleApiClient;
    private SignInButton signInButton;
    private Button logOutButton;
    public static  final int SIGN_IN_CODE = 777;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processus_connexion_google);
        signInButton = findViewById(R.id.sign_in_button);
        logOutButton = findViewById(R.id.logOutButton);
        firebaseAuth = FirebaseAuth.getInstance();



        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getInstance() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        signInButton.setOnClickListener(this);

        logOutButton.setOnClickListener(this);

    }




    private void signIn(){
        Auth.GoogleSignInApi.signOut(GoogleApiClient);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(GoogleApiClient);
        startActivityForResult(intent,SIGN_IN_CODE);
    }
    private void LogOut(){
        Auth.GoogleSignInApi.signOut(GoogleApiClient);
        Toast.makeText(this,"Vous n'êtes plus connecté", Toast.LENGTH_LONG).show();

        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.logOutButton:
                LogOut();
                break;

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            updateUI(true);
        }else {
            updateUI(false);
            Toast.makeText(this,"La session ne peut pas se créer", Toast.LENGTH_LONG).show();
        }
    }

    private void updateUI(boolean isConnected){
        if (isConnected) {
            Toast.makeText(this,"Vous êtes connecté", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Vous n'êtes pas connecté", Toast.LENGTH_LONG).show();
        }
    }




}
