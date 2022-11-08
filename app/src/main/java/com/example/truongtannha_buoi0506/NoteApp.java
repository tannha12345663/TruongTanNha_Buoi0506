package com.example.truongtannha_buoi0506;

public class NoteApp {
    String ID,Title, Detaile, Date;

    public NoteApp(String ID, String title, String detaile, String date) {
        this.ID = ID;
        Title = title;
        Detaile = detaile;
        Date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
