package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToNewEntry(View view) {
        Intent i = new Intent(this, NewEntryActivity.class);
        startActivity(i);
    }
}
