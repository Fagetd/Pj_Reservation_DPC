package com.example.dimit.pj_reservation_dpc;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";
    NavigationView navigationView = null;
    Toolbar toolbar= null;
    private TextView emailTextView;
    private GoogleApiClient googleApiClient;
    private String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment,"MainFragment");
        transaction.commit();


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getInstance() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


        emailTextView = (TextView)findViewById(R.id.emailuser);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        emailTextView.setText(user.getEmail());






        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }








    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_calendrier) {

            //CalendrierFragment fragment = new CalendrierFragment();
            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragment_container,fragment,"CalendrierFragment");
            //transaction.commit();
            Intent intent = new Intent(this,EventsActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //Intent intent = new Intent(this,CalendrierActivity.class);
            //intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            //startActivity(intent);

        } else if (id == R.id.nav_salles) {

            //SallesFragment fragment = new SallesFragment();
            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragment_container,fragment,"SallesFragment");
            //transaction.commit();
            Intent intent = new Intent(this,SalleActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //CalendrierFragment fragment = new CalendrierFragment();
            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragment_container,fragment,"CalendrierFragment");
            //transaction.commit();

        } else if (id == R.id.nav_materiel) {
            Intent intent = new Intent(this,MaterielActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_support) {
            SupportFragment fragment = new SupportFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,fragment,"SupportFragment");
            transaction.commit();


            //SupportFragment fragment = new SupportFragment();
            //android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.fragment_container,fragment);
            //fragmentTransaction.commit();
            //Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_parametres) {

            ParametresFragment fragment = new ParametresFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,fragment,"ParametresFragment");
            transaction.commit();


        } else if (id == R.id.nav_deconnexion) {
            //if (googleApiClient.isConnected()){
              //  Auth.GoogleSignInApi.signOut(googleApiClient);
            //}else {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                //Toast.makeText(this, "deco", Toast.LENGTH_SHORT).show();
        //    }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
