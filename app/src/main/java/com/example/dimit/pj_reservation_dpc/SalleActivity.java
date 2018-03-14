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


public class SalleActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName, editTextCapacite,editTextDescription,editTextAdresse,editTextCodepostal,editTextVille;
    String NameHolder, CapaciteHolder,DescriptionHolder,AdresseHolder,CodepostalHolder,VilleHolder,SQLiteDataBaseQueryHolder;
    Button EnterData, ButtonDisplayData,retour;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle);


        EnterData = (Button)findViewById(R.id.button);
        retour = (Button)findViewById(R.id.buttonRetour);
        ButtonDisplayData = (Button)findViewById(R.id.button2);
        editTextName = (EditText)findViewById(R.id.editText);
        editTextCapacite = (EditText)findViewById(R.id.editText2);
        editTextDescription = (EditText)findViewById(R.id.editText3);
        editTextAdresse = (EditText)findViewById(R.id.editText4);
        editTextCodepostal = (EditText)findViewById(R.id.editText5);
        editTextVille = (EditText)findViewById(R.id.editText6);

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
                Intent intent = new Intent(SalleActivity.this, DisplaySQLiteSalleActivity.class);
                startActivity(intent);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelperSalle.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+ SQLiteHelperSalle.TABLE_NAME+"("
                + SQLiteHelperSalle.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + SQLiteHelperSalle.Table_Column_1_Name+" VARCHAR, "
                + SQLiteHelperSalle.Table_Column_2_Capacite+" VARCHAR, "
                + SQLiteHelperSalle.Table_Column_3_Description+" VARCHAR, "
                + SQLiteHelperSalle.Table_Column_4_Adresse+" VARCHAR, "
                + SQLiteHelperSalle.Table_Column_5_CodePostal+" VARCHAR, "
                + SQLiteHelperSalle.Table_Column_6_Ville+" VARCHAR);");

    }

    public void CheckEditTextStatus(){

        NameHolder = editTextName.getText().toString() ;
        CapaciteHolder = editTextCapacite.getText().toString();
        DescriptionHolder = editTextDescription.getText().toString();
        AdresseHolder = editTextAdresse.getText().toString();
        CodepostalHolder = editTextCodepostal.getText().toString();
        VilleHolder = editTextVille.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(CapaciteHolder)|| TextUtils.isEmpty(DescriptionHolder)|| TextUtils.isEmpty(AdresseHolder)|| TextUtils.isEmpty(CodepostalHolder)|| TextUtils.isEmpty(VilleHolder)){

            EditTextEmptyHold = false ;
        }
        else {
            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO "+ SQLiteHelperSalle.TABLE_NAME+" (name,capacite,description,adresse,codepostal,ville) " +
                    "VALUES('"+NameHolder+"', '"+CapaciteHolder+"', '"+DescriptionHolder+"', '"+AdresseHolder+"', '"+CodepostalHolder+"', '"+VilleHolder+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            sqLiteDatabaseObj.close();

            Toast.makeText(SalleActivity.this,"Insertion effectuée", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(SalleActivity.this,"Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert(){

        editTextName.getText().clear();
        editTextCapacite.getText().clear();
        editTextDescription.getText().clear();
        editTextAdresse.getText().clear();
        editTextCodepostal.getText().clear();
        editTextVille.getText().clear();

    }

}
