package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class AddEntryActivity extends AppCompatActivity {

    private EditText txt_newEntry_subject;
    private EditText txt_newEntry_content;
    private TextView lbl_newEntry_location;
    private DAO dao;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double currentLatitude;
    private double currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        txt_newEntry_subject = (EditText) findViewById(R.id.txt_newEntry_subject);
        txt_newEntry_content = (EditText) findViewById(R.id.txt_newEntry_content);
        lbl_newEntry_location = (TextView) findViewById(R.id.lbl_newEntry_location);
        dao = new DAO(this, null, null, 1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lbl_newEntry_location.setText(String.format("Location: %s,%s", location.getLatitude(), location.getLongitude()));
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 1000, 0, locationListener);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("entering onRequestPermissionsResult");
        switch (requestCode) {
            case 10:
                System.out.println("Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
                System.out.println("Build.VERSION_CODES.M = " + Build.VERSION_CODES.M);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("requesting permission");
                        requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION
                                , Manifest.permission.ACCESS_COARSE_LOCATION
                                , Manifest.permission.INTERNET
                        }, 10);
                        return;
                    }
                }
            default:
                break;
        }
    }

    public void insertRecord(View view) {
        if (txt_newEntry_subject.length() < 1) {
            Toast.makeText(this, "Subject must be entered!", Toast.LENGTH_SHORT).show();
        } else {

            Entry entry = new Entry(new Date(), txt_newEntry_subject.getText().toString(), txt_newEntry_content.getText().toString(), currentLatitude, currentLongitude);
            dao.addNewEntry(entry);

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
