package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

public class DAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes.db";
    public static final String TABLE_ENTRY = "entry";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE_CREATED = "dateCreated";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public DAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_ENTRY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", " + COLUMN_DATE_CREATED + " TEXT" +
                ", " + COLUMN_SUBJECT + " TEXT" +
                ", " + COLUMN_CONTENT + " TEXT" +
                ", " + COLUMN_LATITUDE + " TEXT" +
                ", " + COLUMN_LONGITUDE + " TEXT" +
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
    public void addNewEntry(Entry entry) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE_CREATED, sdf.format(entry.get_dateCreated()));
        values.put(COLUMN_SUBJECT, entry.get_subject());
        values.put(COLUMN_CONTENT, entry.get_content());
        values.put(COLUMN_LATITUDE, entry.get_latitude());
        values.put(COLUMN_LONGITUDE, entry.get_longitude());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ENTRY, null, values);
        db.close();
    }

    public Cursor getAllEntries() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_ENTRY + " WHERE 1";
        Cursor c = db.rawQuery(sql, null);
        db.close();
        return c;
    }
}
