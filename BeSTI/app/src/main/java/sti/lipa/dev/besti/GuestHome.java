package sti.lipa.dev.besti;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;

public class GuestHome extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    TextView _announcementhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        _announcementhome = (TextView) findViewById(R.id.announcementhome);
        String message = "Annnouncement";
        String level="Guest";
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String xtramessage = "";
        cursor = db.rawQuery("SELECT *FROM tblannouncement WHERE Level=?", new String[]{level});
        while (cursor.moveToNext()) {
            xtramessage = cursor.getString(cursor.getColumnIndex("Announcement"));
            _announcementhome.setText(xtramessage);
        }


    }
    public void register(View v) {
        Intent intreg = new Intent(GuestHome.this, Register.class);
        startActivity(intreg);
    }
    public void hymn (View v){
        Intent song=new Intent(GuestHome.this, STIHymnActivity.class);
        startActivity(song);
    }
    public void mission (View v){
        Intent song=new Intent(GuestHome.this, STIMission.class);
        startActivity(song);
    }
    public void vision (View v){
        Intent song=new Intent(GuestHome.this, STIVision.class);
        startActivity(song);
    }
    public void login (View v){
        Intent song=new Intent(GuestHome.this, Login.class);
        startActivity(song);
    }
}

