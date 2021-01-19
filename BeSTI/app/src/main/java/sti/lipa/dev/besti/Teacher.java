package sti.lipa.dev.besti;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;

public class Teacher extends AppCompatActivity {
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mListView=(ListView) findViewById(R.id.listView);
        openHelper=new DatabaseHelper(this);
    }
    public void viewteacher(View view){
        Intent intent = new Intent (Teacher.this, ViewTeachers.class);
        startActivity(intent);
    }
    public void createteacher(View view){
        Intent intent = new Intent (Teacher.this, CreateTeacherAccount.class);
        startActivity(intent);
    }
    }
