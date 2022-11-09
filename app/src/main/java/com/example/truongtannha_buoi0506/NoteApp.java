package com.example.truongtannha_buoi0506;

import java.io.Serializable;

public class NoteApp implements Serializable {
    Integer Id;
    String Title, Detaile, Date;

    public NoteApp(Integer id, String title, String detaile, String date) {
        Id = id;
        Title = title;
        Detaile = detaile;
        Date = date;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetaile() {
        return Detaile;
    }

    public void setDetaile(String detaile) {
        Detaile = detaile;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
