package com.example.dimit.pj_reservation_dpc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditSingleRecordSalleActivity extends AppCompatActivity {

    EditText name, capacite,description,adresse,codepostal,ville;
    TextView titre;
    Button update,retour;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record_salle);
        titre = (TextView) findViewById(R.id.textViewTITRE);
        name = (EditText) findViewById(R.id.EditTextName);
        capacite = (EditText) findViewById(R.id.editText3);
        description = (EditText) findViewById(R.id.editText4);
        adresse = (EditText) findViewById(R.id.editText5);
        codepostal = (EditText) findViewById(R.id.editText6);
        ville = (EditText) findViewById(R.id.editText7);

        update = (Button) findViewById(R.id.buttonUpdate);
        retour = (Button) findViewById(R.id.buttonRetour);

        sqLiteHelper = new SQLiteHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetName = name.getText().toString();
                String GetCapacite = capacite.getText().toString();
                String GetDescription = description.getText().toString();
                String GetAdresse = adresse.getText().toString();
                String GetCodepostal = codepostal.getText().toString();
                String GetVille = ville.getText().toString();

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET "
                        +SQLiteHelper.Table_Column_1_Name+" = '"+GetName+"' , "
                        +SQLiteHelper.Table_Column_2_Capacite+" = '"+GetCapacite+"' , "
                        +SQLiteHelper.Table_Column_3_Description+" = '"+GetDescription+"' , "
                        +SQLiteHelper.Table_Column_4_Adresse+" = '"+GetAdresse+"' , "
                        +SQLiteHelper.Table_Column_5_CodePostal+" = '"+GetCodepostal+"' , "
                        +SQLiteHelper.Table_Column_6_Ville+" = '"+GetVille+"' WHERE id = " + IDholder + "";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                Toast.makeText(EditSingleRecordSalleActivity.this,"La modification a été faite", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditSingleRecordSalleActivity.this, DisplaySQLiteSalleActivity.class);
                startActivity(intent);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSingleRecordSalleActivity.this, DisplaySQLiteSalleActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {

        ShowSRecordInEditText();

        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                name.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                capacite.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Capacite)));
                description.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Description)));
                adresse.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Adresse)));
                codepostal.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_5_CodePostal)));
                ville.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_Ville)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}