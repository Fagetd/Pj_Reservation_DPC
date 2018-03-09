package com.example.dimit.pj_reservation_dpc;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class EventsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{ //GoogleApiClient.OnConnectionFailedListener,
    private GoogleApiClient GoogleApiClient;
    public static  final int SIGN_IN_CODE = 777;
    private static final String TAG = "EventsActivity";
    String IDholder;
    String IDholder2;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDatabase sqLiteDatabase2;
    SQLiteHelper sqLiteHelper;
    SQLiteHelperMateriel sqLiteHelperMateriel;
    Cursor cursor;
    Cursor cursor2;
    private GoogleApiHelper googleApiHelper;



    Calendar dateTime = Calendar.getInstance();
    Calendar endDateTime = Calendar.getInstance();
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();


    private TextView tv_date,tv_date2,nomsalle,nommateriel,qtemateriel;
    private Button query_account,btn_time,btn_date,btn_time2,btn_date2,btn_retour,btn_display_salles,btn_display_materiel,btn_deconnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelperMateriel = new SQLiteHelperMateriel(this);
        query_account = findViewById(R.id.bt_query_account);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        qtemateriel = (TextView)findViewById(R.id.tv_qte_materiel);
        nommateriel = (TextView)findViewById(R.id.tv_nom_materiel);
        nomsalle = (TextView) findViewById(R.id.tv_nom_salle);
        tv_date   = (TextView) findViewById(R.id.tv_start_date_time);
        tv_date2  = (TextView) findViewById(R.id.tv_end_date_time);
        btn_date  = (Button) findViewById(R.id.btn_datePicker);
        btn_date2 = (Button) findViewById(R.id.btn_datePicker2);
        btn_time  = (Button) findViewById(R.id.btn_timePicker);
        btn_time2 = (Button) findViewById(R.id.btn_timePicker2);
        nommateriel = (TextView)findViewById(R.id.tv_nom_materiel);
        nomsalle = (TextView) findViewById(R.id.tv_nom_salle);
        tv_date   = (TextView) findViewById(R.id.tv_start_date_time);
        tv_date2  = (TextView) findViewById(R.id.tv_end_date_time);
        btn_date  = (Button) findViewById(R.id.btn_datePicker);
        btn_date2 = (Button) findViewById(R.id.btn_datePicker2);
        btn_time  = (Button) findViewById(R.id.btn_timePicker);
        btn_time2 = (Button) findViewById(R.id.btn_timePicker2);
        btn_retour = (Button) findViewById(R.id.btn_retour);
        btn_deconnexion = (Button) findViewById(R.id.bt_deconnexion);
        btn_display_salles = (Button) findViewById(R.id.btn_display_salles);
        btn_display_materiel = (Button) findViewById(R.id.btn_display_materiel);

        // les onClick
        query_account.setOnClickListener(this);
        btn_deconnexion.setOnClickListener(this);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });
        btn_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateEnd();
            }
        });
        btn_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeEnd();
            }
        });
        btn_display_salles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this, DisplaySQLiteEventsSallesActivity.class);
                startActivity(intent);
            }
        });
        btn_display_materiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this, DisplaySQLiteEventsMaterielActivity.class);
                startActivity(intent);
            }
        });
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        updateTextLabel();
        updateTextLabelEnd();


    }




    @Override
    protected void onResume() {
        AfficherNomSalle();
        AfficherMateriel();
        super.onResume();
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_query_account:
                signIn();
                break;
            case R.id.bt_deconnexion:

                break;
        }
    }

    //changer de la date et l'heure du début et de la fin
    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }
    private void updateDateEnd(){
        new DatePickerDialog(this, dend, endDateTime.get(Calendar.YEAR),endDateTime.get(Calendar.MONTH),endDateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateTimeEnd(){
        new TimePickerDialog(this, tend, endDateTime.get(Calendar.HOUR_OF_DAY), endDateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };
    DatePickerDialog.OnDateSetListener dend = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endDateTime.set(Calendar.YEAR, year);
            endDateTime.set(Calendar.MONTH, monthOfYear);
            endDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabelEnd();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();
        }
    };
    TimePickerDialog.OnTimeSetListener tend = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            endDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            endDateTime.set(Calendar.MINUTE, minute);
            updateTextLabelEnd();
        }
    };

    private void updateTextLabel(){
        tv_date.setText(formatDateTime.format(dateTime.getTime()));
    }
    private void updateTextLabelEnd(){
        tv_date2.setText(formatDateTime.format(endDateTime.getTime()));
    }









    //se connecter avec gmail
    private void signIn(){
        Auth.GoogleSignInApi.signOut(GoogleApiClient);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(GoogleApiClient);
        startActivityForResult(intent,SIGN_IN_CODE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleApiClient.connect();
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();       // On recup le compte
            TextView queryAccountResult =  findViewById(R.id.tv_account);
            TextView queryNameAccountResult =  findViewById(R.id.et_nom);
            queryAccountResult.setText(account.getEmail());                //On écrit l'email
            queryNameAccountResult.setText(account.getDisplayName());      //On écrit le nom
            updateUI(true);
        }else {
            updateUI(false);
            Toast.makeText(this,"La session ne peut pas se créer", Toast.LENGTH_LONG).show();
        }
    }

    private void updateUI(boolean isConnected){
        if (isConnected) {
            Button bt_query_calendar = findViewById(R.id.bt_query_calendar);
            Button bt_query_account = findViewById(R.id.bt_query_account);
            bt_query_calendar.setVisibility(View.VISIBLE);
            bt_query_account.setText("Changer de compte");
            ImageView iv_pastille = findViewById(R.id.iv_pastille);
            iv_pastille.setVisibility(View.VISIBLE);

            Toast.makeText(this,"Vous êtes connecté", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Vous n'êtes pas connecté", Toast.LENGTH_LONG).show();
        }
    }




    //permissions
    public void request_permission(View view) {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CALENDAR, android.Manifest.permission.WRITE_CALENDAR}, 1);
    }

// Chercher un calendrier
    public void query_calendar(View view) {
        EditText et_nom = findViewById(R.id.et_nom);
        String nom = et_nom.getText().toString();

        if (nom != null) {
            final String selection = null;
            final String[] selectionArgs = null;

            String[] EVENT_PROJECTION = new String[]{
                    CalendarContract.Calendars._ID,
                    CalendarContract.Calendars.ACCOUNT_NAME,
                    CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                    CalendarContract.Calendars.OWNER_ACCOUNT,
                    CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
            };

            int PROJECTION_ID_INDEX = 0;
            int PROJECTION_ACCOUNT_NAME_INDEX = 1;
            int PROJECTION_DISPLAY_NAME_INDEX = 2;
            int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
            int PROJECTION_CALENDAR_ACCESS_LEVEL = 4;

            //Obtenir l'email du compte dans l'edit text
            String targetAccount = ((TextView) findViewById(R.id.tv_account)).getText().toString();

            Cursor cur;
            ContentResolver cr = getContentResolver();
            Uri uri = CalendarContract.Calendars.CONTENT_URI;

            int permission = ContextCompat.checkSelfPermission(EventsActivity.this,
                    android.Manifest.permission.READ_CALENDAR);
            // Créer une liste pour stocker temporairement les résultats de la requête
            final List<String> accountNameList = new ArrayList<>();
            final List<Integer> calendarIdList = new ArrayList<>();
            // Si l'utilisateur a donné l'autorisation
            if (permission == PackageManager.PERMISSION_GRANTED) {
                cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
                if (cur != null) {
                    while (cur.moveToNext()) {

                        final long calendarId = cur.getLong(PROJECTION_ID_INDEX);
                        final String accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
                        final String displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
                        final String ownerAccount = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
                        final int accessLevel = cur.getInt(PROJECTION_CALENDAR_ACCESS_LEVEL);

                        //Enregistrer temporairement les informations pour que l'utilisateur choisisse
                        accountNameList.add(displayName);
                        calendarIdList.add((int) calendarId);
                    }
                    cur.close();
                }
                if (calendarIdList.size() != 0) {

                    AlertDialog.Builder adb = new AlertDialog.Builder(this);
                    CharSequence items[] = accountNameList.toArray(new CharSequence[accountNameList.size()]);
                    adb.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Button bt_query_calendar = findViewById(R.id.bt_query_calendar);                                            // chercher un calendrier
                            TextView targetCalendarId = findViewById(R.id.tv_calendar_id);                                              // id du calendrier
                            TextView tv_calendrier = findViewById(R.id.tv_adresse_calendrier);                                          // nom du calendrier
                            Button bt_query_event = findViewById(R.id.bt_query_event);                                                  // chercher un event
                            Button bt_insert_event = findViewById(R.id.insert_event);                                                   // inserer un event
                            Button bt_update_event = findViewById(R.id.bt_update_event);                                                // modifier un event
                            Button bt_delete_event = findViewById(R.id.bt_delete_event);                                                // supprimer un event
                            EditText et_titre = findViewById(R.id.et_titre);                                                            // titre de l'event
                            TextView et_start_date_time = findViewById(R.id.tv_start_date_time);                                        // date et heure de start
                            TextView et_end_date_time = findViewById(R.id.tv_end_date_time);                                            // date et heure de end
                            Button btn_datePicker = findViewById(R.id.btn_datePicker);                                                  // update date start
                            Button btn_datePicker2 = findViewById(R.id.btn_datePicker2);                                                // update time start
                            Button btn_timePicker = findViewById(R.id.btn_timePicker);                                                  // update date end
                            Button btn_timePicker2 = findViewById(R.id.btn_timePicker2);                                                // update time end
                            Button btn_display_salles= findViewById(R.id.btn_display_salles);                                           // choisir sa salle
                            TextView tv_nom_salle= findViewById(R.id.tv_nom_salle);                                                     // nom de la salle

                            TextView tv_clear = findViewById(R.id.tv_clear);
                            targetCalendarId.setText(String.format("%s", calendarIdList.get(which)));
                            tv_calendrier.setText(String.format("%s", accountNameList.get(which)));
                            dialog.dismiss();
                            // si le calendrier est choisi
                            if (tv_calendrier !=null){
                                tv_calendrier.setVisibility(View.VISIBLE);
                                bt_query_event.setVisibility(View.VISIBLE);
                                bt_insert_event.setVisibility(View.VISIBLE);
                                bt_update_event.setVisibility(View.INVISIBLE);
                                btn_datePicker.setVisibility(View.VISIBLE);
                                btn_datePicker2.setVisibility(View.VISIBLE);
                                btn_timePicker.setVisibility(View.VISIBLE);
                                btn_timePicker2.setVisibility(View.VISIBLE);
                                btn_display_salles.setVisibility(View.VISIBLE);
                                et_titre.setVisibility(View.VISIBLE);
                                et_start_date_time.setVisibility(View.VISIBLE);
                                et_end_date_time.setVisibility(View.VISIBLE);
                                tv_nom_salle.setVisibility(View.VISIBLE);
                                bt_query_calendar.setText("Changer de calendrier");
                            }
                        }
                    });
                    adb.setNegativeButton("ANNULER", null);
                    adb.show();
                } else {
                    Toast toast = Toast.makeText(this, "Aucun calendrier n'a été trouvé", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(this, "Aucune autorisation de lire le calendrier", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


//Chercher un event
    public void query_event(View view) {

        String[] INSTANCE_PROJECTION = new String[]{
                CalendarContract.Instances.EVENT_ID,      // 0 ID
                CalendarContract.Instances.BEGIN,         // 1 Date et heure de début de l'activité
                CalendarContract.Instances.TITLE,         // 2 Titre
                CalendarContract.Instances.END,         // 3 Date et heure de fin de l'activité
        };

        int PROJECTION_ID_INDEX = 0;
        int PROJECTION_BEGIN_INDEX = 1;
        int PROJECTION_TITLE_INDEX = 2;
        int PROJECTION_END_INDEX = 3;

        // Obtenir l'ID du calendrier dans EditText
        String targetCalendar = ((TextView) findViewById(R.id.tv_calendar_id)).getText().toString();
        // période pour tous les événements
        // Les mois commencent à 0, 0-11
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2018, 0, 1, 8, 0);
        long startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2100, 0, 1, 8, 0);
        long endMillis = endTime.getTimeInMillis();
        // Interroger les activités (buildUpon trouve un uri déjà existant)
        ContentResolver cr = getContentResolver();
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        // Définir la requête pour connaître toutes les activités pour la période spécifiée dans le calendrier
        String selection = CalendarContract.Events.CALENDAR_ID + " = ?";
        String[] selectionArgs = new String[]{targetCalendar};
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);


        int permission = ContextCompat.checkSelfPermission(EventsActivity.this, android.Manifest.permission.READ_CALENDAR);
        // Créer une liste pour stocker temporairement les résultats de la requête
        final List<Integer> eventIdList = new ArrayList<>();
        final List<Long> beginList = new ArrayList<>();
        final List<Long> endList = new ArrayList<>();
        final List<String> titleList = new ArrayList<>();

        Cursor cur = null;
        // Si l'utilisateur a donné l'autorisation de démarrer la requête d'agenda
        if (permission == PackageManager.PERMISSION_GRANTED) {
            cur = cr.query(builder.build(), INSTANCE_PROJECTION, selection, selectionArgs, null);
            if (cur != null) {
                while (cur.moveToNext()) {
                    int eventID = 0;
                    long beginVal = 0;
                    long endVal = 0;
                    String title = null;
                    // obtenir les infos
                    eventID = cur.getInt(PROJECTION_ID_INDEX);
                    beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
                    endVal = cur.getLong(PROJECTION_END_INDEX);
                    title = cur.getString(PROJECTION_TITLE_INDEX);

                    // Enregistrer temporairement les informations pour que l'utilisateur choisisse
                    eventIdList.add((int)eventID);
                    beginList.add(beginVal);
                    endList.add(endVal);
                    titleList.add(title);
                }
                cur.close();
            }
            if (eventIdList.size() != 0) {
                // Créer un dialogue pour laisser l'utilisateur choisir l'activité
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                CharSequence items[] = titleList.toArray(new CharSequence[titleList.size()]);
                adb.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView targetEventId = findViewById(R.id.tv_event_id);
                        TextView targetStartDateTime = findViewById(R.id.tv_start_date_time);
                        TextView targetEndDateTime = findViewById(R.id.tv_end_date_time);
                        EditText targetTitle = findViewById(R.id.et_titre);
                        targetEventId.setText(String.format("%s", eventIdList.get(which)));
                        String startDateTime = DateFormat.getDateTimeInstance().format(beginList.get(which));
                        String endDateTime = DateFormat.getDateTimeInstance().format(endList.get(which));
                        targetStartDateTime.setText(startDateTime);
                        targetEndDateTime.setText(endDateTime);
                        targetTitle.setText(String.format("%s", titleList.get(which)));
                        dialog.dismiss();

                        if(targetTitle !=null) {
                            Button bt_insert_event = findViewById(R.id.insert_event);
                            Button bt_update_event = findViewById(R.id.bt_update_event);
                            Button bt_delete_event = findViewById(R.id.bt_delete_event);
                            bt_insert_event.setVisibility(View.VISIBLE);
                            bt_update_event.setVisibility(View.VISIBLE);
                            bt_delete_event.setVisibility(View.VISIBLE);
                        }

                    }
                });
                adb.setNegativeButton("CANCEL", null);
                adb.show();
            } else {
                Toast toast = Toast.makeText(this,  "Aucun calendrier trouvé", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, "Aucune autorisation pour lire le calendrier", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //choisir sa salle
    public void AfficherNomSalle() {
        EditText et_titre = findViewById(R.id.et_titre);
        //String titre = et_titre.getText().toString();
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");


        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                nomsalle.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }
    //choisir le matériel
    public void AfficherMateriel() {

        sqLiteDatabase2 = sqLiteHelperMateriel.getWritableDatabase();

        IDholder2 = getIntent().getStringExtra("ListViewClickedItemValue2");

        cursor2 = sqLiteDatabase2.rawQuery("SELECT * FROM " + SQLiteHelperMateriel.TABLE_NAME + " WHERE id = " + IDholder2 + "", null);

        if (cursor2.moveToFirst()) {

            do {
                nommateriel.setText(cursor2.getString(cursor2.getColumnIndex(SQLiteHelperMateriel.Table_Column_1_Libelle)));
                qtemateriel.setText(cursor2.getString(cursor2.getColumnIndex(SQLiteHelperMateriel.Table_Column_2_Qte)));
            }
            while (cursor2.moveToNext());
            cursor2.close();
        }
    }




// Ajouter un evenement
    public void insert_event(View view) {

        String targetCalendarId = ((TextView) findViewById(R.id.tv_calendar_id)).getText().toString();
        EditText et_nom = findViewById(R.id.et_nom);
        String nom = et_nom.getText().toString();
        TextView tv_nom_salle = findViewById(R.id.tv_nom_salle);
        String nom_salle = tv_nom_salle.getText().toString();
        TextView tv_nom_materiel = findViewById(R.id.tv_nom_materiel);
        String nom_materiel = tv_nom_materiel.getText().toString();
        TextView tv_qte_materiel = findViewById(R.id.tv_qte_materiel);
        String qte_materiel = tv_qte_materiel.getText().toString();
        TextView et_start_date_time = findViewById(R.id.tv_start_date_time);
        TextView et_end_date_time = findViewById(R.id.tv_end_date_time);

        long startTime = dateTime.getTimeInMillis();
        long endTime = endDateTime.getTimeInMillis();

        long calendarId = Long.parseLong(targetCalendarId);

        long StartTimeMillis = startTime ; // Début de l'évenement, System.currentTimeMillis()
        long DureeTimeMillis = endTime - StartTimeMillis;
        if ((DureeTimeMillis <= 0)||(startTime==endTime)){
            et_end_date_time.setError("");
            et_start_date_time.setError("");
            return;
        }else{
            et_end_date_time.setError(null);
            et_start_date_time.setError(null);
            long endTimeMillis = StartTimeMillis + DureeTimeMillis; // Fin de l'évenement, 900000 à la base
            String targetTitle = ((EditText) findViewById(R.id.et_titre)).getText().toString();


            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, StartTimeMillis);
            values.put(CalendarContract.Events.DTEND, endTimeMillis);
            values.put(CalendarContract.Events.TITLE, targetTitle+ " ("+nom+")");
            values.put(CalendarContract.Events.DESCRIPTION, "Réservation de la salle \"" +nom_salle+ "\" équipée de "+qte_materiel+" "+nom_materiel+".");
            values.put(CalendarContract.Events.CALENDAR_ID, calendarId);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());

            int permissionCheck = ContextCompat.checkSelfPermission(EventsActivity.this,
                    android.Manifest.permission.WRITE_CALENDAR);

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

                if (uri != null) {
                    long eventID = Long.parseLong(uri.getLastPathSegment());
                    TextView targetEventId = findViewById(R.id.tv_event_id);
                    targetEventId.setText(String.format("%s", eventID));
                }
            }
        }
    }


// update un evenement
    public void update_event(View view) {
        EditText et_nom = findViewById(R.id.et_nom);
        String nom = et_nom.getText().toString();
        String targetEventId = ((TextView) findViewById(R.id.tv_event_id)).getText().toString();
        TextView et_start_date_time = findViewById(R.id.tv_start_date_time);
        TextView et_end_date_time = findViewById(R.id.tv_end_date_time);

        long eventId = Long.parseLong(targetEventId);
        long startTime = dateTime.getTimeInMillis();
        long endTime = endDateTime.getTimeInMillis();
        long StartTimeMillis = startTime; // Début de l'évenement, System.currentTimeMillis()
        long DureeTimeMillis = endTime - StartTimeMillis;
        if ((DureeTimeMillis <= 0)||(startTime==endTime)) {
            et_end_date_time.setError("");
            et_start_date_time.setError("");
            return;
        } else {
            et_end_date_time.setError(null);
            et_start_date_time.setError(null);
            long endTimeMillis = StartTimeMillis + DureeTimeMillis;
            String targetTitle = ((EditText) findViewById(R.id.et_titre)).getText().toString();

            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.TITLE, targetTitle + " (" + nom + ")");
            values.put(CalendarContract.Events.DTSTART, StartTimeMillis);
            values.put(CalendarContract.Events.DTEND, endTimeMillis);

            int permission = ContextCompat.checkSelfPermission(EventsActivity.this,
                    android.Manifest.permission.WRITE_CALENDAR);

            if (permission == PackageManager.PERMISSION_GRANTED) {
                Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId);
                cr.update(uri, values, null, null);
            }
        }
    }
// Delete un evenement
    public void delete_event(View view) {

        String targetEventId = ((TextView) findViewById(R.id.tv_event_id)).getText().toString();
        long eventId = Long.parseLong(targetEventId);

        ContentResolver cr = getContentResolver();

        int permission = ContextCompat.checkSelfPermission(EventsActivity.this, android.Manifest.permission.WRITE_CALENDAR);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId);
            cr.delete(uri, null, null);
            clear(view);
        }
    }


    public void clear(View view) {

        Button bt_insert_event = findViewById(R.id.insert_event);                                                   // inserer un event
        EditText et_titre = findViewById(R.id.et_titre);                                                            // titre de l'event
        TextView et_start_date_time = findViewById(R.id.tv_start_date_time);                                        // date et heure de l'event
        Button bt_update_event = findViewById(R.id.bt_update_event);                                                // modifier un event
        Button bt_delete_event = findViewById(R.id.bt_delete_event);                                                // supprimer un event
        TextView et_end_date_time = findViewById(R.id.tv_end_date_time);                                            // date et heure de end
        TextView tv_nom_salle= findViewById(R.id.tv_nom_salle);                                                     // nom de la salle
        TextView tv_nom_materiel = findViewById(R.id.tv_nom_materiel);                                              // nom du materiel
        TextView tv_qte_materiel = findViewById(R.id.tv_qte_materiel);                                              // quantité du matériel


        bt_insert_event.setVisibility(View.VISIBLE);
        bt_update_event.setVisibility(View.INVISIBLE);
        bt_delete_event.setVisibility(View.INVISIBLE);
        et_titre.setText("");
        et_start_date_time.setText("");
        tv_nom_salle.setText("");
        et_end_date_time.setText("");
        tv_nom_materiel.setText("");
        tv_qte_materiel.setText("");


    }

}
