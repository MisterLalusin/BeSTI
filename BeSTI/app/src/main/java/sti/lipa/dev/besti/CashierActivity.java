package sti.lipa.dev.besti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CashierActivity extends AppCompatActivity {
    private CheckBox _ahome;
    private CheckBox _astudent;
    private CheckBox _asection;
    EditText _message;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    TextView _txtcashier;
    Cursor cursor,cursor1,cursor2,cursor3;
    CheckBox _ckbx1,_ckbx2,_ckbx3,_ckbx4,_ckbx5,_ckbx6;
    String c1,c2,c3,c4,c5,c6,status,dbname;
    RadioButton r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);
        _ckbx1 = (CheckBox)findViewById(R.id.chckbx1);
        _ckbx2 = (CheckBox)findViewById(R.id.chckbox2);
        _ckbx3 = (CheckBox)findViewById(R.id.checkBox3);
        _ckbx4 = (CheckBox)findViewById(R.id.chckbox4);
        _ckbx5 = (CheckBox)findViewById(R.id.chckbox5);
        _ckbx6 = (CheckBox)findViewById(R.id.checkBox6);
        _txtcashier=(TextView)findViewById(R.id.txtstatuscashier);
        r1 = (RadioButton)findViewById(R.id.availradio);
        r2 = (RadioButton)findViewById(R.id.unavailradio);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String xtramessage = "";
        dbname="2";
        cursor = db.rawQuery("SELECT *FROM tblcashier WHERE IDNUMBER=?", new String[]{dbname});
        while (cursor.moveToNext()) {
            xtramessage = cursor.getString(cursor.getColumnIndex("Message"));
            _txtcashier.setText(xtramessage);
        }
        c1="";
        c2="";
        c3="";
        c4="";
        c5="";
        c6="";

    }

    public void PostAnnouncementcash(View v) {
        if (_ckbx1.isChecked()) {
            c1="7:30AM-9:00AM\n";
        }
        if (_ckbx2.isChecked()) {
           c2="9:00AM-10:30AM\n";

        }
        if (_ckbx3.isChecked()) {
           c3="10:30AM-12:00PM\n";

        }
        if (_ckbx4.isChecked()) {
         c4="12:00PM-1:30PM\n";

        }
        if (_ckbx5.isChecked()) {
           c5="1:30PM-3:00PM\n";

        }
        if (_ckbx6.isChecked()) {
       c6="3:00PM-4:30PM\n";

        }
        if(r1.isChecked()){
            status="Is available on:\n";
        }
        if (r2.isChecked()){
            status="Is unavailable on:\n";}
        String message=""+status+" "+c1+""+c2+""+c3+""+c4+""+c5+""+c6+"";
        db = openHelper.getWritableDatabase();
        ContentValues cashierMessage = new ContentValues();
        cashierMessage.put("Message", message);
        db.update("tblcashier",cashierMessage,"IDNUMBER=?", new String[]{dbname});
        Toast.makeText(getApplicationContext(), "Update Posted!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(CashierActivity.this, CashierAccount.class);
        startActivity(i);
    }

}
