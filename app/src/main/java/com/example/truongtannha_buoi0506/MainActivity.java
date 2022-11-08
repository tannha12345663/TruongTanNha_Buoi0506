package com.example.truongtannha_buoi0506;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}