package sti.lipa.dev.besti;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewCashierInfo extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    TextView _cashierinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cashier_info);
_cashierinfo = (TextView) findViewById(R.id.viewcashierinfouser);
        String message = "Annnouncement";
        String level="cashier@sti.edu";
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String xtramessage = "";
        cursor = db.rawQuery("SELECT *FROM tblcashier WHERE Email=?", new String[]{level});
        while (cursor.moveToNext()) {
            xtramessage = cursor.getString(cursor.getColumnIndex("Message"));
            _cashierinfo.setText(xtramessage);
        }




    }
}
