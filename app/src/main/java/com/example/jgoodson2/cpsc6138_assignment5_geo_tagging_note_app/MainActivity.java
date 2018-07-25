package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DAO dao;
    ListView listViewEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new DAO(this, null, null, 1);
        listViewEntries = (ListView) findViewById(R.id.listViewEntries);

        populateEntriesListView();
    }

    private void populateEntriesListView() {
        Cursor data = dao.getAllEntries();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add("\"" + data.getString(2) + "\"  [entered " + data.getString(1) + "] Entry ID: " + data.getString(0));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listViewEntries.setAdapter(adapter);

        listViewEntries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = adapterView.getItemAtPosition(i).toString();
                String id = selection.substring(selection.indexOf("Entry ID")).trim();
                Log.d(TAG, "onItemClick: You Clicked on " + id);
            }
        });
    }

    public void goToNewEntry(View view) {
        Intent i = new Intent(this, AddEntryActivity.class);
        startActivity(i);
    }
}
