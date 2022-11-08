package com.example.truongtannha_buoi0506;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbHelper {
    static String DB_NAME = "NoteDB.db";
    Context context;

    public DbHelper(Context context) {
        this.context = context;
    }
    public SQLiteDatabase openDBOption2(){
        return context.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE, null);
    }
    public void createTable(){
        SQLiteDatabase db = openDBOption2();
        String query = "CREATE TABLE IF NOT EXISTS NoteTbl(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " title TEXT," +
                " content TEXT," +
                " createDate TEXT )";
        db.execSQL(query);
        closeDB(db);
    }
    void closeDB(SQLiteDatabase db){
        db.close();
    }
    public void insertNote(NoteApp note){


    }
    public void updateNote(NoteApp note){
        SQLiteDatabase db = openDBOption2();

    }
    public void deleteNote(int id){

    }
    public ArrayList<NoteApp> getNotes(){

        return null;
    }

    public NoteApp getNoteById(int id){
        return null;
    }

    public ArrayList<NoteApp> searchNote(String newString){
        return null;
    }

    public ArrayList<NoteApp> sortNote(int type){
        return null;
    }


}
