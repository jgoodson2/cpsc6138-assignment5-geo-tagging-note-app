package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class NewEntryActivity extends AppCompatActivity {

    private EditText subject;
    private EditText content;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        subject = (EditText) findViewById(R.id.txt_newEntry_subject);
        content = (EditText) findViewById(R.id.txt_newEntry_content);
        dao = new DAO(this, null, null, 1);
    }

    public void insertRecord(View view) {
        Entry entry = new Entry(new Date(), subject.getText().toString(), content.getText().toString(), 0, 0);
        dao.addNewEntry(entry);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }

}
