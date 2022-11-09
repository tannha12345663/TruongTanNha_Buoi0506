package com.example.truongtannha_buoi0506;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.RegistrationManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class add_edit_note extends AppCompatActivity {
    EditText edtTitle, edtDetail;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        innitUI();
        innitListener();
    }

    private void innitUI() {
        edtTitle=findViewById(R.id.EdtTitle);
        edtDetail=findViewById(R.id.edtDetail);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if(flag == 1){
            getSupportActionBar().setTitle("Thêm mới ghi chú");
        }else {
            getSupportActionBar().setTitle("Chỉnh sửa ghi chú");
        }
    }
    private void innitListener(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_del,menu); // Khai báo hiển thị menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.mnSave){

        }
        else if (id==R.id.mnDel){

        }
        return super.onOptionsItemSelected(item);
    }
}