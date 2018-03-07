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
import java.util.ArrayList;

public class DisplaySQLiteDataActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
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
    String TempHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);
        retour = (Button) findViewById(R.id.buttonRetour);

        ID_Array = new ArrayList<String>();
        NAME_Array = new ArrayList<String>();
        CAPACITE_Array = new ArrayList<String>();
        DESCRIPTION_Array = new ArrayList<String>();
        ADRESSE_Array = new ArrayList<String>();
        CODEPOSTAL_Array = new ArrayList<String>();
        VILLE_Array = new ArrayList<String>();


        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(),ShowSingleRecordActivity.class);

                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());

                startActivity(intent);

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SalleActivity.class);
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

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        CAPACITE_Array.clear();
        DESCRIPTION_Array.clear();
        ADRESSE_Array.clear();
        CODEPOSTAL_Array.clear();
        VILLE_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                CAPACITE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Capacite)));
                DESCRIPTION_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Description)));
                ADRESSE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Adresse)));
                CODEPOSTAL_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_5_CodePostal)));
                VILLE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_Ville)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this,

                ID_Array,
                NAME_Array,
                CAPACITE_Array,
                DESCRIPTION_Array,
                ADRESSE_Array,
                CODEPOSTAL_Array,
                VILLE_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}