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

import java.util.ArrayList;

public class ListViewCashier extends AppCompatActivity {

    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    private ListView mListView;
    String snumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_cashier);
        mListView = (ListView) findViewById(R.id.listviewcash);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        populateListView();

    }
    private void populateListView() {
        Intent getintent = getIntent();
        snumber = getintent.getStringExtra("number");
        db = openHelper.getWritableDatabase();
        Cursor data = openHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor datateacher= db.rawQuery("SELECT *FROM tblreserve WHERE Show=?",new String[]{name});
                int itemID = -1;
                while (datateacher.moveToNext()) {
                    itemID = datateacher.getInt(0);
                }
                if(itemID > -1) {
                    Intent editScreenIntent = new Intent(getApplicationContext(),UpdateReservationCashier.class);
                    editScreenIntent.putExtra("name", name);
                    editScreenIntent.putExtra("id", itemID);
                    startActivity(editScreenIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "NO ID associated with that name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
