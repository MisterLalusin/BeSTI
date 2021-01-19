package sti.lipa.dev.besti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleTeacher extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    TextView _announcementstudent;
    private String s1number;
    private ListView mListView;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_teacher);
        Intent getintent = getIntent();
        s1number = getintent.getStringExtra("number");
        mListView = (ListView) findViewById(R.id.listviewteacher);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        populateListView();
        String xtrasnumber = "";
        String xtrafname = "";
        String xtralname = "";
        String xtraemail = "";
        String xtrapword = "";
        String xtraphone = "";
        String xtracourse = "";

        }
    private void populateListView() {
        Intent getintent = getIntent();
        number= s1number;
        db = openHelper.getWritableDatabase();
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
        String date = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        Cursor data = db.rawQuery("SELECT *FROM tblschedteacher WHERE Day=? AND TeacherNumber=?",new String[]{weekday_name,number});
        Toast.makeText(ScheduleTeacher.this, "Email isis "+number, Toast.LENGTH_LONG).show();

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //rovic mas poge ke emman
        //rovic mas poge ke emman

    }


    }
