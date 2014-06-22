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

    SQLiteDatabase db = this.getWritableDatabase();

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        Log.e("Iskvieciau klase", DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE klausimai ("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_KL + " TEXT,"
                + KEY_ATS + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
       db.execSQL("DROP TABLE IF EXISTS klausimai");
       this.onCreate(db);
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

        ContentValues contentValues = new ContentValues();
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
//    public Cursor getData(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
//        return res;
//    }
//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
//        return numRows;
//    }
//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Integer deleteContact (Integer id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//    public ArrayList getAllCotacts()
//    {
//        ArrayList array_list = new ArrayList();
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}