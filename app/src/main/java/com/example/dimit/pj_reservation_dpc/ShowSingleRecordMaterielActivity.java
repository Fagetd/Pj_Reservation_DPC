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

public class ShowSingleRecordMaterielActivity extends AppCompatActivity {

    String IDholder;
    TextView id, libelle, qte;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelperMateriel sqLiteHelperMateriel;
    Cursor cursor;
    Button Delete, Edit, retour;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record_materiel);

        id = (TextView) findViewById(R.id.textViewID);
        libelle = (TextView) findViewById(R.id.textViewLibelle);
        qte = (TextView) findViewById(R.id.textViewQte);


        Delete = (Button)findViewById(R.id.buttonDelete);
        Edit = (Button)findViewById(R.id.buttonEdit);
        retour = (Button)findViewById(R.id.buttonRetour);

        sqLiteHelperMateriel = new SQLiteHelperMateriel(this);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "DELETE FROM "+SQLiteHelperMateriel.TABLE_NAME+" WHERE id = "+IDholder+"";

                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                finish();

            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),EditSingleRecordMaterielActivity.class);

                intent.putExtra("EditID", IDholder);

                startActivity(intent);

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),DisplaySQLiteMaterielActivity.class);
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

        sqLiteDatabase = sqLiteHelperMateriel.getWritableDatabase();

        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelperMateriel.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                id.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_ID)));
                libelle.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_1_Libelle)));
                qte.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_2_Qte)));

            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelperMateriel.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
}
