package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

public class DAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notes.db";
    public static final String TABLE_ENTRY = "entry";
    public static final String COLUMN_0_ID = "id";
    public static final String COLUMN_1_DATE_CREATED = "dateCreated";
    public static final String COLUMN_2_SUBJECT = "subject";
    public static final String COLUMN_3_CONTENT = "content";
    public static final String COLUMN_4_LATITUDE = "latitude";
    public static final String COLUMN_5_LONGITUDE = "longitude";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public DAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_ENTRY + "(" +
                COLUMN_0_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", " + COLUMN_1_DATE_CREATED + " TEXT" +
                ", " + COLUMN_2_SUBJECT + " TEXT" +
                ", " + COLUMN_3_CONTENT + " TEXT" +
                ", " + COLUMN_4_LATITUDE + " TEXT" +
                ", " + COLUMN_5_LONGITUDE + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(sql);
        System.out.println("table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY);
        System.out.println("table dropped");
        onCreate(sqLiteDatabase);
    }

    //insert new record
    public boolean addNewEntry(Entry entry) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_1_DATE_CREATED, sdf.format(entry.get_dateCreated()));
        values.put(COLUMN_2_SUBJECT, entry.get_subject());
        values.put(COLUMN_3_CONTENT, entry.get_content());
        values.put(COLUMN_4_LATITUDE, entry.get_latitude());
        values.put(COLUMN_5_LONGITUDE, entry.get_longitude());
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_ENTRY, null, values);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllEntries() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_ENTRY;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

}
