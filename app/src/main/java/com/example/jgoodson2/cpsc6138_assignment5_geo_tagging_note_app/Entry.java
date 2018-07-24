package com.example.jgoodson2.cpsc6138_assignment5_geo_tagging_note_app;

import java.util.Date;

public class Entry {

    private int _id;
    private Date _dateCreated;
    private String _subject;
    private String _content;
    private float _latitude;
    private float _longitude;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date get_dateCreated() {
        return _dateCreated;
    }

    public void set_dateCreated(Date _dateCreated) {
        this._dateCreated = _dateCreated;
    }

    public String get_subject() {
        return _subject;
    }

    public void set_subject(String _subject) {
        this._subject = _subject;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public float get_latitude() {
        return _latitude;
    }

    public void set_latitude(float _latitude) {
        this._latitude = _latitude;
    }

    public float get_longitude() {
        return _longitude;
    }

    public void set_longitude(float _longitude) {
        this._longitude = _longitude;
    }

    public Entry(Date _dateCreated, String _subject, String _content, float _latitude, float _longitude) {

        this._dateCreated = _dateCreated;
        this._subject = _subject;
        this._content = _content;
        this._latitude = _latitude;
        this._longitude = _longitude;
    }
}
