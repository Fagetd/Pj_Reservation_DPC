package com.example.dimit.pj_reservation_dpc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplaySQLiteMaterielActivity extends AppCompatActivity {

    SQLiteHelperMateriel sqLiteHelperMateriel;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapterMateriel listAdapterMateriel ;
    ListView LISTVIEW;
    Button retour;

    ArrayList<String> ID_Array;
    ArrayList<String> LIBELLE_Array;
    ArrayList<String> QTE_Array;


    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_materiel);

        LISTVIEW = (ListView) findViewById(R.id.listView1);
        retour = (Button) findViewById(R.id.buttonRetour);

        ID_Array = new ArrayList<String>();
        LIBELLE_Array = new ArrayList<String>();
        QTE_Array = new ArrayList<String>();



        sqLiteHelperMateriel = new SQLiteHelperMateriel(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(),ShowSingleRecordMaterielActivity.class);

                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());

                startActivity(intent);

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MaterielActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onResume() {
        ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelperMateriel.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelperMateriel.TABLE_NAME+"", null);

        ID_Array.clear();
        LIBELLE_Array.clear();
        QTE_Array.clear();


        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_ID)));
                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_ID)));
                LIBELLE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_1_Libelle)));
                QTE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperMateriel.Table_Column_2_Qte)));



            } while (cursor.moveToNext());
        }

        listAdapterMateriel = new ListAdapterMateriel(DisplaySQLiteMaterielActivity.this,

                ID_Array,
                LIBELLE_Array,
                QTE_Array

        );

        LISTVIEW.setAdapter(listAdapterMateriel);

        cursor.close();
    }
}