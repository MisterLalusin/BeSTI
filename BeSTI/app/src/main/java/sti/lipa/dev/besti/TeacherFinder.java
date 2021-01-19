package sti.lipa.dev.besti;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TeacherFinder extends AppCompatActivity {
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    private ListView mListView;
    private String number;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_finder);
        mListView = (ListView) findViewById(R.id.listview);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        populateListView();

    }
    private void populateListView() {
        Intent getintent = getIntent();
        number= getintent.getStringExtra("number");
        db = openHelper.getWritableDatabase();
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
        //String date = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        Cursor data = db.rawQuery("SELECT *FROM tblschedteacher WHERE Day=?",new String[]{weekday_name});

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

    }
}
