package com.example.dimit.pj_reservation_dpc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    //objets de la vue
    EditText ed_login, ed_password, ed_nom, ed_prenom, ed_adresse1, ed_adresse2, ed_lieuDit, ed_codePostal, ed_ville, ed_numTelephone, ed_societe, ed_mail;
    // objet auth de firebase
    private FirebaseAuth firebaseAuth;
    // objet database de firebase
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        ed_nom = (EditText) findViewById(R.id.ed_nom);
        ed_prenom = (EditText) findViewById(R.id.ed_prenom);
        ed_adresse1 = (EditText) findViewById(R.id.ed_adresse1);
        ed_adresse2 = (EditText) findViewById(R.id.ed_adresse2);
        ed_lieuDit = (EditText) findViewById(R.id.ed_lieuDit);
        ed_codePostal = (EditText) findViewById(R.id.ed_codePostal);
        ed_ville = (EditText) findViewById(R.id.ed_ville);
        ed_numTelephone = (EditText) findViewById(R.id.ed_numTelephone);
        ed_societe = (EditText) findViewById(R.id.ed_societe);
        ed_mail = (EditText) findViewById(R.id.ed_mail);
        ed_login = (EditText) findViewById(R.id.ed_login);
        ed_password = (EditText) findViewById(R.id.ed_password);

        findViewById(R.id.bt_sign_up).setOnClickListener(this);
        findViewById(R.id.bt_annuler).setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    private void registerUser() {

        String email = ed_login.getText().toString().trim(); //trim ça enleve les blancs
        String password = ed_password.getText().toString().trim();
        String nom = ed_nom.getText().toString().trim();
        String prenom = ed_prenom.getText().toString().trim();
        String email_info = ed_mail.getText().toString().trim();
        String adresse1 = ed_adresse1.getText().toString().trim();
        String cp = ed_codePostal.getText().toString().trim();
        String ville = ed_ville.getText().toString().trim();
        String tel = ed_numTelephone.getText().toString().trim();
        String societe = ed_societe.getText().toString().trim();


        if (email.isEmpty()) {
            ed_login.setError("Veuillez rentrer votre adresse de connexion");
            ed_login.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed_login.setError("Veuillez entrer une adresse mail valide");
            ed_login.requestFocus();
            return;
        }
        if (email_info.isEmpty()) {
            ed_mail.setError("Veuillez rentrer votre adresse mail");
            ed_mail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_info).matches()) {
            ed_mail.setError("Veuillez entrer une adresse mail valide");
            ed_mail.requestFocus();
            return;
        }
        if (nom.isEmpty()) {
            ed_nom.setError("Veuillez rentrer votre nom");
            ed_nom.requestFocus();
            return;
        }
        if (prenom.isEmpty()) {
            ed_prenom.setError("Veuillez rentrer votre prénom");
            ed_prenom.requestFocus();
            return;
        }
        if (adresse1.isEmpty()) {
            ed_adresse1.setError("Veuillez rentrer votre adresse");
            ed_adresse1.requestFocus();
            return;
        }
        if (cp.isEmpty()) {
            ed_codePostal.setError("Veuillez rentrer votre code postal");
            ed_codePostal.requestFocus();
            return;
        }
        if (ville.isEmpty()) {
            ed_ville.setError("Veuillez rentrer votre ville");
            ed_ville.requestFocus();
            return;
        }
        if (tel.isEmpty()) {
            ed_numTelephone.setError("Veuillez rentrer votre numéro de téléphone");
            ed_numTelephone.requestFocus();
            return;
        }
        if (tel.length() > 10) {
            ed_numTelephone.setError("Le numéro de téléphone est invalide");
            ed_numTelephone.requestFocus();
            return;
        }
        if (societe.isEmpty()) {
            ed_societe.setError("Veuillez rentrer votre société");
            ed_societe.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            ed_password.setError("Veuillez rentrer votre mot de passe");
            ed_password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            ed_password.setError("La longueur minimum du mot de passe doit être supérieure à 6 charactères ");
            ed_password.requestFocus();
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Utilisateur enregistré", Toast.LENGTH_SHORT).show();
                    saveUserInformation();
                } else {
                    Toast.makeText(getApplicationContext(), "Ce login existe déjà", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void saveUserInformation() {
        String nom = ed_nom.getText().toString().trim();
        String prenom = ed_prenom.getText().toString().trim();
        String adresse1 = ed_adresse1.getText().toString().trim();
        String adresse2 = ed_adresse2.getText().toString().trim();
        String lieuDit = ed_lieuDit.getText().toString().trim();
        String codePostal = ed_codePostal.getText().toString().trim();
        String ville = ed_ville.getText().toString().trim();
        String numTelephone = ed_numTelephone.getText().toString().trim();
        String societe = ed_societe.getText().toString().trim();
        String adressemail = ed_mail.getText().toString().trim();


        UserInformation userInformation = new UserInformation(nom, prenom,
                adresse1, adresse2, lieuDit, codePostal, ville, numTelephone, societe, adressemail);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child("users").child(user.getUid()).setValue(userInformation);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sign_up:
                registerUser();
                break;
            case R.id.bt_annuler:
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }


}
