package sti.lipa.dev.besti;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewMyReservation extends AppCompatActivity {

    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    private ListView mListView;
    String snumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_reservation);
        mListView = (ListView) findViewById(R.id.listviewreserveuser);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        populateListView();

    }
    private void populateListView() {
        Intent getintent = getIntent();
        snumber = getintent.getStringExtra("number");
        db = openHelper.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT *FROM tblreserve WHERE IDNumber=?",new String[]{snumber});
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }
}
