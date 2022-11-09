package com.example.truongtannha_buoi0506;

import android.app.Application;

public class App extends Application {
    DbHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DbHelper(this);
        dbHelper.createTable();
    }
}
