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
    private static final String KEY_ATS1 = "Pasirinkimas2";
    private static final String KEY_ATS2 = "Pasirinkimas3";
    private static final String KEY_ID1 = "code";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "passw";
    private static final String KEY_ACTIVE = "active";
    int ap = 1;



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
        db.execSQL("CREATE TABLE apk ("
                + KEY_KL + " TEXT,"
                + KEY_ATS + " TEXT, "
                + KEY_ATS1 + " TEXT, "
                + KEY_ATS2 + " TEXT " +
                ")");

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
        db.execSQL("DROP TABLE IF EXISTS apk");
       this.onCreate(db);
    }

    public boolean activeUser (String name, String c, boolean a){
        String code;
        Cursor u = db.rawQuery("SELECT code FROM user WHERE name = '"+ name + "'", null);
        if (null != u && u.moveToFirst()) {
            code = u.getString(u.getColumnIndex(KEY_ID1));
            if(code == c) {
                a = true;
                Log.e("useris egzistuoja",c);
            }else{
                a = false;
            }
        }
        return a;
    }

    public void newUser (String name, String pass, String id){
        String a = "1";

        contentValues.put("name", name);
        contentValues.put("passw", pass);
        contentValues.put("code", id);
        contentValues.put("active",a);

        db.insert("user", null, contentValues);
        Log.e("irasiau useri", name);

    }

    public void klausymai(String kl, String ats1, String ats2, String ats3){
        contentValues.put("Pavadinimas", kl);
        contentValues.put("Pasirinkimas1", ats1);
        contentValues.put("Pasirinkimas2", ats2);
        contentValues.put("Pasirinkimas3", ats3);

        db.insert("apk", null, contentValues);
    }

    public String selectKl(String k){
         Cursor klau = db.rawQuery("SELECT Pavadinimas FROM apk WHERE rowid = '"+ getCount() + "'", null);

        if (null != klau && klau.moveToFirst()) {
            k = klau.getString(klau.getColumnIndex(KEY_KL));
        }
        return k;
    }

    public String selectAts1(String a1) {
        Cursor ats1 = db.rawQuery("SELECT Pasirinkimas1 FROM apk WHERE rowid = '"+ getCount() + "'", null);
        if (null != ats1 && ats1.moveToFirst()) {
            a1 = ats1.getString(ats1.getColumnIndex(KEY_ATS));
        }
        return a1;
    }

    public String selectAts2(String a2) {
        Cursor ats2 = db.rawQuery("SELECT Pasirinkimas2 FROM apk WHERE rowid = '"+ getCount() + "'", null);
        if (null != ats2 && ats2.moveToFirst()) {
            a2 = ats2.getString(ats2.getColumnIndex(KEY_ATS1));
        }
        return a2;
    }

    public String selectAts3(String a3) {
        Cursor ats3 = db.rawQuery("SELECT Pasirinkimas3 FROM apk WHERE rowid = '"+ getCount() + "'", null);
        if (null != ats3 && ats3.moveToFirst()) {
            a3 = ats3.getString(ats3.getColumnIndex(KEY_ATS2));
    }
        return a3;
    }

    public boolean insertKl  (String kl, String ats){

        Cursor row = db.rawQuery("SELECT Pavadinimas FROM klausimai WHERE Pavadinimas= '" + kl + "'", null);

        if(row != null) {
            deleteKl();
            newAts(kl,ats);
        }else{
            newAts(kl,ats);
        }
        return true;
    }

    public void newAts (String kl, String ats){

        contentValues.put("Pavadinimas", kl);
        contentValues.put("Pasirinkimas1", ats);
        db.insert("klausimai", null, contentValues);
    }

    public void deleteKl()
    {
        //db.delete(TABLE_APK1, KEY_KL + "='" + kl + "'", null);
        db.execSQL("delete from "+ TABLE_APK1);
    }

    public void setCount(int app){
        ap = app;
    }

    public int getCount(){
        return ap;

    }
}