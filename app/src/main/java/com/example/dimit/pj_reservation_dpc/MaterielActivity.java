package com.example.dimit.pj_reservation_dpc;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MaterielActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextLibelle, editTextQte;
    String LibelleHolder, QteHolder, SQLiteDataBaseQueryHolder;
    Button EnterData, ButtonDisplayData, retour;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel);


        EnterData = (Button) findViewById(R.id.button);
        retour = (Button) findViewById(R.id.buttonRetour);
        ButtonDisplayData = (Button) findViewById(R.id.button2);
        editTextLibelle = (EditText) findViewById(R.id.editText);
        editTextQte = (EditText) findViewById(R.id.editText2);


        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();


            }
        });

        ButtonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MaterielActivity.this, DisplaySQLiteMaterielActivity.class);
                startActivity(intent);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MaterielActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelperMateriel.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelperMateriel.TABLE_NAME + "("
                + SQLiteHelperMateriel.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + SQLiteHelperMateriel.Table_Column_1_Libelle + " VARCHAR, "
                + SQLiteHelperMateriel.Table_Column_2_Qte + " VARCHAR);");

    }

    public void CheckEditTextStatus() {

        LibelleHolder = editTextLibelle.getText().toString();
        QteHolder = editTextQte.getText().toString();


        if (TextUtils.isEmpty(LibelleHolder) || TextUtils.isEmpty(QteHolder)) {

            EditTextEmptyHold = false;
        } else {
            EditTextEmptyHold = true;
        }
    }

    public void InsertDataIntoSQLiteDatabase() {

        if (EditTextEmptyHold == true) {

            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelperMateriel.TABLE_NAME + " (libelle,qte) " +
                    "VALUES('" + LibelleHolder + "', '" + QteHolder + "');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            sqLiteDatabaseObj.close();

            Toast.makeText(MaterielActivity.this, "Insertion effectuée", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(MaterielActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert() {

        editTextLibelle.getText().clear();
        editTextQte.getText().clear();

    }

}
