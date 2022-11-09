package com.example.truongtannha_buoi0506;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

        SQLiteDatabase db = openDBOption2();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",note.Title);
        contentValues.put("content",note.Detaile);
        contentValues.put("createDate",note.Date);

        db.insert("NoteTbl",null,contentValues);
        closeDB(db);
    }
    public void updateNote(NoteApp note){
        SQLiteDatabase db = openDBOption2();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",note.Title);
        contentValues.put("content",note.Detaile);
        contentValues.put("createDate",note.Date);

        db.update("NoteTbl",contentValues,"id = "+note.Id,null);
        closeDB(db);

    }
    public void deleteNote(int id){
        SQLiteDatabase db = openDBOption2();
        db.delete("NoteTbl","id = "+id,null);
        closeDB(db);
    }
    public ArrayList<NoteApp> getNotes(){
        //Select * from NoteTbl
        ArrayList<NoteApp> tmp = new ArrayList<>();
        SQLiteDatabase db = openDBOption2();
        String sql = "SELECT * FROM NoteTbl ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String createDate = cursor.getString(3);
            NoteApp noteApp = new NoteApp(id,title,content,createDate);
            tmp.add(noteApp);
        }
        closeDB(db);
        return tmp;
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
