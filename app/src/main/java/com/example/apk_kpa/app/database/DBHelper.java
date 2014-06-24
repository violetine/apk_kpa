package com.example.apk_kpa.app.database;

/**
 * Created by VIO on 6/19/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "apklausos";
    private static final String TABLE_APK1 = "klausimai";
    private static final String KEY_ID = "id";
    private static final String KEY_KL = "Pavadinimas";
    private static final String KEY_ATS = "Pasirinkimas1";
    private static final String TABLE_USER = "user";
    private static final String KEY_ID1 = "code";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "passw";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_isActive = "1";


    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        Log.e("Iskvieciau klase", DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE user ("
                + KEY_ID1 + " INTEGER,"
                + KEY_NAME + " TEXT,"
                + KEY_PASS + " TEXT, "
                + KEY_ACTIVE + " INTEGER " +
                ")");

        db.execSQL("CREATE TABLE klausimai ("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_KL + " TEXT,"
                + KEY_ATS + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS klausimai");
       this.onCreate(db);
    }

    public void insertUser (String name, String pass, String id){

        Log.e("naujas irasas", name);
        newUser(name,pass,id);

        //Cursor row = db.rawQuery("SELECT code FROM user WHERE name= '" + name + "'", null);

//        if(row != null) {
//              setActive(name);
//        }
    }

    public void newUser (String name, String pass, String id){
        String a = "1";

        contentValues.put("name", name);
        contentValues.put("passw", pass);
        contentValues.put("code", id);
        contentValues.put("active",a);

        db.insert("user", null, contentValues);
        Log.e("Irasiau viska", name + pass + id + a);

    }

    public boolean insertKl  (String kl, String ats)
    {

        Cursor row = db.rawQuery("SELECT Pavadinimas FROM klausimai WHERE Pavadinimas= '" + kl + "'", null);

        if(row != null) {

            Log.e("jau egzistuoja irasas", kl);
            deleteKl();
            Log.e("istryniau ir irasiau is naujo", kl);
            newAts(kl,ats);
        }else{
            Log.e("naujas irasas", kl);
            newAts(kl,ats);
        }
        return true;
    }

    public void newAts (String kl, String ats){

        contentValues.put("Pavadinimas", kl);
        contentValues.put("Pasirinkimas1", ats);

        db.insert("klausimai", null, contentValues);
        Log.e("Irasiau klausima:", kl);
        Log.e("Irasiau atsakyma:", ats);

    }

    public void deleteKl()
    {
        //db.delete(TABLE_APK1, KEY_KL + "='" + kl + "'", null);
        db.execSQL("delete from "+ TABLE_APK1);
    }
}