package com.example.dimit.pj_reservation_dpc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowSingleRecordSalleActivity extends AppCompatActivity {

    String IDholder;
    TextView id, name, capacite, description, adresse, codepostal, ville;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelperSalle sqLiteHelperSalle;
    Cursor cursor;
    Button Delete, Edit, retour;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record_salle);

        id = (TextView) findViewById(R.id.textViewID);
        name = (TextView) findViewById(R.id.textViewName);
        capacite = (TextView) findViewById(R.id.textViewCapacite);
        description = (TextView) findViewById(R.id.textViewDescription);
        adresse = (TextView) findViewById(R.id.textViewAdresse);
        codepostal = (TextView) findViewById(R.id.textViewCodepostal);
        ville = (TextView) findViewById(R.id.textViewVille);

        Delete = (Button) findViewById(R.id.buttonDelete);
        Edit = (Button) findViewById(R.id.buttonEdit);
        retour = (Button) findViewById(R.id.buttonRetour);

        sqLiteHelperSalle = new SQLiteHelperSalle(this);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "DELETE FROM " + SQLiteHelperSalle.TABLE_NAME + " WHERE id = " + IDholder + "";

                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();
                Intent intent = new Intent(ShowSingleRecordSalleActivity.this, DisplaySQLiteSalleActivity.class);
                startActivity(intent);

                finish();

            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EditSingleRecordSalleActivity.class);

                intent.putExtra("EditID", IDholder);

                startActivity(intent);

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DisplaySQLiteSalleActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {

        ShowSingleRecordInTextView();

        super.onResume();
    }

    public void ShowSingleRecordInTextView() {

        sqLiteDatabase = sqLiteHelperSalle.getWritableDatabase();

        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelperSalle.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                id.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_ID)));
                name.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_1_Name)));
                capacite.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_2_Capacite)));
                description.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_3_Description)));
                adresse.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_4_Adresse)));
                codepostal.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_5_CodePostal)));
                ville.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_6_Ville)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase() {

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelperSalle.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
}