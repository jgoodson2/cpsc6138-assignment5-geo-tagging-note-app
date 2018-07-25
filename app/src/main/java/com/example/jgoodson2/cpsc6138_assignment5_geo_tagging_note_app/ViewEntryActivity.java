package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewEntryActivity extends AppCompatActivity {


    private static final String TAG = "ViewEntryActivity";
    private TextView lbl_viewEntry_subject, lbl_viewEntry_content, lbl_viewEntry_dateCreated;
    private DAO dao;
    private int selectedID;
    Cursor recordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        lbl_viewEntry_subject = (TextView) findViewById(R.id.lbl_viewEntry_subject);
        lbl_viewEntry_content = (TextView) findViewById(R.id.lbl_viewEntry_content);
        lbl_viewEntry_dateCreated = (TextView) findViewById(R.id.lbl_viewEntry_dateCreated);
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
                lbl_viewEntry_dateCreated.setText("Date created: " + recordData.getString(1));
                lbl_viewEntry_content.setText(recordData.getString(3));
            }
        }
    }

    public void goBackToViewAllEntries(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
