package com.example.dimit.pj_reservation_dpc;

/**
 * Created by dimit on 28/02/2018.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    static String DATABASE_NAME="database";

    public static final String TABLE_NAME="LesSalles";

    public static final String Table_Column_ID = "id";
    public static final String Table_Column_1_Name = "name";
    public static final String Table_Column_2_Capacite = "capacite";
    public static final String Table_Column_3_Description = "description";
    public static final String Table_Column_4_Adresse = "adresse";
    public static final String Table_Column_5_CodePostal = "codepostal";
    public static final String Table_Column_6_Ville= "ville";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + Table_Column_ID + " integer primary key autoincrement, "
            + Table_Column_1_Name + " string, "
            + Table_Column_2_Capacite + " string, "
            + Table_Column_3_Description + " string, "
            + Table_Column_4_Adresse + " string, "
            + Table_Column_5_CodePostal + " string, "
            + Table_Column_6_Ville + " string);";

//    private static final String DATABASE_ALTER_1 = "ALTER TABLE "
//            + TABLE_NAME + " ADD COLUMN " + Table_Column_3_AutreColonne + " string;";
//
//    private static final String DATABASE_ALTER_2 = "ALTER TABLE "
//            + TABLE_NAME + " ADD COLUMN " + Table_Column_4_AutreColonne + " string;";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ajouter une colonne
        //if (oldVersion < 1) {
        //    db.execSQL(DATABASE_ALTER_1);
       // }
        //if (oldVersion < 2) {
        //    db.execSQL(DATABASE_ALTER_2);
        // }
    }
}