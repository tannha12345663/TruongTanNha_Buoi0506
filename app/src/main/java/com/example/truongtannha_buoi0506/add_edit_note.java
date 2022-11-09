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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class add_edit_note extends AppCompatActivity {
    EditText edtTitle, edtDetail;
    int flag;
    NoteApp noteAppEdit;
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
            noteAppEdit = (NoteApp) intent.getSerializableExtra("noteEdit");
            edtTitle.setText(noteAppEdit.getTitle());
            edtDetail.setText(noteAppEdit.getDetaile());
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
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        if (id==R.id.mnSave){
            if (edtTitle.getText().toString().isEmpty()
                    || edtDetail.getText().toString().isEmpty()){
                Toast.makeText(this, "Nhập nội dung trước khi lưu", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (flag == 1){
                NoteApp noteApp = new NoteApp(new Random().nextInt(9999),
                        edtTitle.getText().toString().trim(),
                        edtDetail.getText().toString().trim(),
                        date);
                DbHelper dbHelper = new DbHelper(add_edit_note.this);
                dbHelper.insertNote(noteApp);
                Intent intent = new Intent();
                intent.putExtra("flag",1);
                setResult(RESULT_OK,intent);
                finish();
            }
            else {
                NoteApp noteApp = new NoteApp(noteAppEdit.getId(),
                        edtTitle.getText().toString().trim(),
                        edtDetail.getText().toString().trim(),
                        date);
                DbHelper dbHelper = new DbHelper(add_edit_note.this);
                dbHelper.updateNote(noteApp);
                Intent intent = new Intent();
                intent.putExtra("flag",2);
                setResult(RESULT_OK,intent);
                finish();
            }

        }
        else if (id==R.id.mnDel){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}