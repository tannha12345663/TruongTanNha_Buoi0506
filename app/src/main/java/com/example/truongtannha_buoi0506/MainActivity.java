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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NoteAppAdapter.Listener {
    ArrayList<NoteApp> noteApps;
    DbHelper dbHelper;
    RecyclerView recyclerView ;
    SearchView searchViewNote;
    NoteAppAdapter noteAppAdapter;
    ActivityResultLauncher<Intent> mLauncher =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK) {
                        if (result.getData().getIntExtra("flag", 0) == 1) {
                            noteApps = new ArrayList<>();
                            RefreshNote();

                        } else if(result.getData().getIntExtra("flag", 0) == 2){
                            noteApps = new ArrayList<>();
                            RefreshNote();
                        } else if (result.getData().getIntExtra("flag", 0) == 3){
                            noteApps = new ArrayList<>();
                            RefreshNote();
                        }
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Huệ Lee mãi đỉnh ");
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(MainActivity.this);
        noteApps= new ArrayList<>();
        innitUI();
        innitListener();
    }

    private void innitListener() {
        searchViewNote.clearFocus();
        searchViewNote.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                noteAppAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                noteAppAdapter.getFilter().filter(newText);
                if (newText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
                //fileList(newText);
                return false;
            }
        });
    }

    private void innitUI() {
        ArrayList<NoteApp> data = dbHelper.getNotes();
        noteApps.addAll(data);
        // noteApps.add(new NoteApp(01,"Ghi chú 01","Ở đây xin chào","11/09/2022"));
        recyclerView=findViewById(R.id.rxNote);
        noteAppAdapter = new NoteAppAdapter(noteApps,MainActivity.this);
        recyclerView.setAdapter(noteAppAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
        searchViewNote = findViewById(R.id.searchView);

    }
    public void RefreshNote(){
        ArrayList<NoteApp> data = dbHelper.getNotes();
        noteApps.addAll(data);
        noteAppAdapter = new NoteAppAdapter(noteApps,MainActivity.this);
        recyclerView.setAdapter(noteAppAdapter);
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
            OrderNote(0);

        }
        else if (id==R.id.theoCBA){
            OrderNote(1);
        }
        else if (id==R.id.mnAdd){
            Intent intent = new Intent(MainActivity.this,add_edit_note.class);
            intent.putExtra("flag",1);
            onResume();
            mLauncher.launch(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void OrderNote(int type){
        noteApps= new ArrayList<>();
        ArrayList<NoteApp> data = dbHelper.sortNote(type);
        noteApps.addAll(data);
        noteAppAdapter = new NoteAppAdapter(noteApps,MainActivity.this);
        recyclerView.setAdapter(noteAppAdapter);
    }
    @Override
    public void onItemListener(int pos, NoteApp noteApp) {
        Intent intent = new Intent(MainActivity.this,add_edit_note.class);
        intent.putExtra("noteEdit",noteApp);
        intent.putExtra("flag",0);
        mLauncher.launch(intent);
    }
}