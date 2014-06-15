package com.example.apk_kpa.app.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.apk_kpa.app.Apklausa;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "apklausos";
    private static final String TABLE_APK1 = "klausimai";
    private static final String KEY_ID = "id";
    private static final String KEY_KL = "Pavadinimas";
    private static final String KEY_ATS = "Pasirinkimas1";
//    private static final String KEY_CH2 = "Pasirinkimas2";
//    private static final String KEY_CH3 = "Pasirinkimas3";
//    private static final String KEY_SHOPID = "parduotuvesID";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_APK1 + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_KL + " TEXT," + KEY_ATS + " TEXT," + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APK1);

        // Create tables again
        onCreate(db);
        addApklausa();
    }

     void addApklausa() {

        Apklausa apk = new Apklausa();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_KL, apk.getKlausymas());
        values.put(KEY_ATS, apk.getAtsakymas());
//        values.put(KEY_CH2, question.getPhoneNumber());
//        values.put(KEY_CH3, question.getPhoneNumber());
//        values.put(KEY_SHOPID, question.getPhoneNumber());


        // Inserting Row
        db.insert(TABLE_APK1, null, values);
        db.close();
    }
}
//
//    // Getting single contact
//    Question getQuestion(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { KEY_ID,
//                        KEY_TITLE, KEY_CH1,KEY_CH2,KEY_CH3,KEY_SHOPID }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Question question = new Question(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return contact
//        return question;
//    }
//
//    // Getting All Contacts
//    public List<Question> getAllQuestions() {
//        List<Question> questionList = new ArrayList<Question>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Question question = new Question();
//                question.setID(Integer.parseInt(cursor.getString(0)));
//                question.setName(cursor.getString(1));
//                question.setPhoneNumber(cursor.getString(2));
//                // Adding contact to list
//                questionList.add(question);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return questionList;
//    }
//
//    // Updating single contact
//    public int updateQuestion(Question question) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, question.getName());
//        values.put(KEY_PH_NO, question.getPhoneNumber());
//
//        // updating row
//        return db.update(TABLE_QUESTIONS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(question.getID()) });
//    }
//
//    // Deleting single contact
//    public void deleteQuestion(Question question) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_QUESTIONS, KEY_ID + " = ?",
//                new String[] { String.valueOf(question.getID()) });
//        db.close();
//    }
//
//
//    // Getting contacts Count
//    public int getQuestionsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
//
