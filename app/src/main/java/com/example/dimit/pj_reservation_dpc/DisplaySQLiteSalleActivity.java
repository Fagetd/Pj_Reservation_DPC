package com.example.dimit.pj_reservation_dpc;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class DisplaySQLiteSalleActivity extends AppCompatActivity {

    SQLiteHelperSalle sqLiteHelperSalle;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapterSalle listAdapterSalle;
    ListView LISTVIEW;
    Button retour;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> CAPACITE_Array;
    ArrayList<String> DESCRIPTION_Array;
    ArrayList<String> ADRESSE_Array;
    ArrayList<String> CODEPOSTAL_Array;
    ArrayList<String> VILLE_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_salle);

        LISTVIEW = (ListView) findViewById(R.id.listView1);
        retour = (Button) findViewById(R.id.buttonRetour);

        ID_Array = new ArrayList<String>();
        NAME_Array = new ArrayList<String>();
        CAPACITE_Array = new ArrayList<String>();
        DESCRIPTION_Array = new ArrayList<String>();
        ADRESSE_Array = new ArrayList<String>();
        CODEPOSTAL_Array = new ArrayList<String>();
        VILLE_Array = new ArrayList<String>();


        sqLiteHelperSalle = new SQLiteHelperSalle(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(), ShowSingleRecordSalleActivity.class);

                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());

                startActivity(intent);

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SalleActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        ShowSQLiteDBdata();
        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelperSalle.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelperSalle.TABLE_NAME + "", null);

        ID_Array.clear();
        NAME_Array.clear();
        CAPACITE_Array.clear();
        DESCRIPTION_Array.clear();
        ADRESSE_Array.clear();
        CODEPOSTAL_Array.clear();
        VILLE_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_ID)));
                //Insertion de l'ID de colonne dans le tableau. A utiliser lors de la méthode Click Listener dans ListView.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_ID)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_1_Name)));
                CAPACITE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_2_Capacite)));
                DESCRIPTION_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_3_Description)));
                ADRESSE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_4_Adresse)));
                CODEPOSTAL_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_5_CodePostal)));
                VILLE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelperSalle.Table_Column_6_Ville)));


            } while (cursor.moveToNext());
        }

        listAdapterSalle = new ListAdapterSalle(DisplaySQLiteSalleActivity.this,

                ID_Array,
                NAME_Array,
                CAPACITE_Array,
                DESCRIPTION_Array,
                ADRESSE_Array,
                CODEPOSTAL_Array,
                VILLE_Array
        );

        LISTVIEW.setAdapter(listAdapterSalle);

        cursor.close();
    }
}