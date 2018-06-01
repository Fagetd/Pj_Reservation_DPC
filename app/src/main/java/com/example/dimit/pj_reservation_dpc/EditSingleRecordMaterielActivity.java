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


public class EditSingleRecordMaterielActivity extends AppCompatActivity {
    EditText libelle, qte;
    TextView titre;
    Button update, retour;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelperMateriel sqLiteHelperMateriel;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record_materiel);
        titre = (TextView) findViewById(R.id.textViewTITRE);
        libelle = (EditText) findViewById(R.id.EditTextLibelle);
        qte = (EditText) findViewById(R.id.EditTextQte);


        update = (Button) findViewById(R.id.buttonUpdate);
        retour = (Button) findViewById(R.id.buttonRetour);

        sqLiteHelperMateriel = new SQLiteHelperMateriel(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetLibelle = libelle.getText().toString();
                String GetQte = qte.getText().toString();

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelperMateriel.TABLE_NAME + " SET "
                        + SQLiteHelperMateriel.Table_Column_1_Libelle + " = '" + GetLibelle + "' , "
                        + SQLiteHelperMateriel.Table_Column_2_Qte + " = '" + GetQte + "' WHERE id = " + IDholder + "";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                Toast.makeText(EditSingleRecordMaterielActivity.this, "La modification a été faite", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditSingleRecordMaterielActivity.this, DisplaySQLiteMaterielActivity.class);
                startActivity(intent);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSingleRecordMaterielActivity.this, DisplaySQLiteMaterielActivity.class);
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

        sqLiteDatabase = sqLiteHelperMateriel.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelperMateriel.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                libelle.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_1_Libelle)));
                qte.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_2_Qte)));

            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase() {

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelperMateriel.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}
