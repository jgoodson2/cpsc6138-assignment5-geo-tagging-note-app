package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewEntryActivity extends AppCompatActivity {


    private static final String TAG = "ViewEntryActivity";
    private TextView lbl_viewEntry_subject, lbl_viewEntry_content, lbl_viewEntry_info;
    private DAO dao;
    private int selectedID;
    Cursor recordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        lbl_viewEntry_subject = (TextView) findViewById(R.id.lbl_viewEntry_subject);
        lbl_viewEntry_content = (TextView) findViewById(R.id.lbl_viewEntry_content);
        lbl_viewEntry_info = (TextView) findViewById(R.id.lbl_viewEntry_info);
//        lbl_viewEntry_latitude = (TextView) findViewById(R.id.lbl_viewEntry_latitude);
//        lbl_viewEntry_longitude = (TextView) findViewById(R.id.lbl_viewEntry_longitude);
        dao = new DAO(this, null, null, 1);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);
        Log.d(TAG, "intent extra received: id=" + String.valueOf(selectedID));
        Long latitude, longitude;
        if (selectedID == -1) {
            Toast.makeText(this, "error: no record with this id", Toast.LENGTH_LONG);
        } else {
            recordData = dao.getRecordById(selectedID);
            while (recordData.moveToNext()) {
                lbl_viewEntry_subject.setText(recordData.getString(2));
                lbl_viewEntry_info.setText("Date created: " + recordData.getString(1) +
                        "\nLatitude: " + recordData.getString(4) +
                        "\nLongitude: " + recordData.getString(5)
                );
                lbl_viewEntry_content.setText(recordData.getString(3));
            }
        }
    }

    public void goBackToViewAllEntries(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
