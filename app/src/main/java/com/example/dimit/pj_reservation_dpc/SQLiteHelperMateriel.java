package com.example.dimit.pj_reservation_dpc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dimit on 02/03/2018.
 */

public class SQLiteHelperMateriel extends SQLiteOpenHelper {
    public SQLiteHelperMateriel(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    static String DATABASE_NAME="database";

    public static final String TABLE_NAME="LeMateriel";

    public static final String Table_Column_ID = "id";
    public static final String Table_Column_1_Libelle = "libelle";
    public static final String Table_Column_2_Qte = "qte";


    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + Table_Column_ID + " integer primary key autoincrement, "
            + Table_Column_1_Libelle + " string, "
            + Table_Column_2_Qte + " string);";

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
