package com.example.truongtannha_buoi0506;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NoteAppAdapter.Listener {
    ArrayList<NoteApp> noteApps;
    DbHelper dbHelper;
    RecyclerView recyclerView ;
    NoteAppAdapter noteAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(MainActivity.this);
        noteApps= new ArrayList<>();
        innitUI();
    }

    private void innitUI() {
        ArrayList<NoteApp> data = dbHelper.getNotes();
        noteApps.addAll(data);
        recyclerView=findViewById(R.id.rxNote);
        noteApps = new ArrayList<>();
        noteAppAdapter = new NoteAppAdapter(noteApps);
        recyclerView.setAdapter(noteAppAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_item,menu); // Khai báo hiển thị menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.theoABC){

        }
        else if (id==R.id.theoID){

        }
        else if (id==R.id.mnAdd){
            Intent intent = new Intent(MainActivity.this,add_edit_note.class);
            intent.putExtra("flag",1);
            onResume();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemListener(int pos, NoteApp noteApp) {
        Intent intent = new Intent(MainActivity.this,add_edit_note.class);
        intent.putExtra("flag",0);
        startActivity(intent);
    }
}