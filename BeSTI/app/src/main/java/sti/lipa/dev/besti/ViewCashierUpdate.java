package sti.lipa.dev.besti;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ViewCashierUpdate extends AppCompatActivity {
    TextView _announcemencashier;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cashier_update);
        _announcemencashier = (TextView) findViewById(R.id.viewannouncementcashier);
        String message = "Annnouncement";
        String level="2";
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String xtramessage = "";
        cursor = db.rawQuery("SELECT *FROM tblcashier WHERE IDNUMBER=?", new String[]{level});
        while (cursor.moveToNext()) {
            xtramessage = cursor.getString(cursor.getColumnIndex("Message"));
            _announcemencashier.setText(xtramessage);

        }


    }
}
