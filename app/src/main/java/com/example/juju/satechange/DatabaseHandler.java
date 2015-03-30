package com.example.juju.satechange;


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by pevd620 on 27/03/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "LieuxCapteurs";

    // Contacts table name
    private static final String TABLE_LIEUX = "Lieux";

    // Lieux Table Columns names
    private static final String KEY_LIEU_ID = "id";
    private static final String KEY_LIEU_NAME = "nomLieu";
    private static final String KEY_LIEU_TYPE = "type";

    // Capteurs Etats table name
    private static final String TABLE_CAPTEURSETATS = "CapteursEtats";

    // Lieux CapteursEtats Columns names
    private static final String KEY_CAP_ID = "id";
    private static final String KEY_CAP_NAME = "nomCapteur";
    private static final String KEY_CAP_ETAT = "etatCapteur";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIEUX_TABLE = "CREATE TABLE " + TABLE_LIEUX + "("
                + KEY_LIEU_ID + " INTEGER PRIMARY KEY," + KEY_LIEU_NAME + " TEXT,"
                + KEY_LIEU_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_LIEUX_TABLE);

        String CREATE_CAPTEURSETATS_TABLE = "CREATE TABLE " + TABLE_CAPTEURSETATS + "("
                + KEY_CAP_ETAT + " INTEGER PRIMARY KEY," + KEY_CAP_NAME + " TEXT,"
                + KEY_CAP_ETAT + " TEXT" + ")";
        db.execSQL(CREATE_CAPTEURSETATS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIEUX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAPTEURSETATS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addLieu(Lieu lieu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIEU_NAME, lieu.get_nomLieu());
        values.put(KEY_LIEU_TYPE, lieu.get_type());

        // Inserting Row
        db.insert(TABLE_LIEUX, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Lieu getLieu(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LIEUX, new String[] { KEY_LIEU_ID,
                        KEY_LIEU_NAME, KEY_LIEU_TYPE }, KEY_LIEU_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Lieu lieu = new Lieu(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return lieu;
    }

    // Getting All lieu
    public List<Lieu> getAlllieu() {
        List<Lieu> lieuList = new ArrayList<Lieu>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIEUX;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Lieu lieu = new Lieu();
                lieu.set_id(Integer.parseInt(cursor.getString(0)));
                lieu.set_nomLieu(cursor.getString(1));
                lieu.set_type(cursor.getString(2));
                // Adding contact to list
                lieuList.add(lieu);
            } while (cursor.moveToNext());
        }

        // return contact list
        return lieuList;
    }

    // Updating single lieu
    public int updateLieu(Lieu lieu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIEU_NAME, lieu.get_nomLieu());
        values.put(KEY_LIEU_TYPE, lieu.get_type());

        /* updating row */
        return db.update(TABLE_LIEUX, values, KEY_LIEU_ID + " = ?",
                new String[] { String.valueOf(lieu.get_id()) });
    }

    // Deleting single lieu
    public void deleteLieu(Lieu lieu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIEUX, KEY_LIEU_ID + " = ?",
                new String[] { String.valueOf(lieu.get_id()) });
        db.close();
    }


    // Getting lieu Count
    public int getLieuCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LIEUX;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }




}
