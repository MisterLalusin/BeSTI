package sti.lipa.dev.besti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateReservationCashier extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper mdatabaseHelper;
    Button _bbtnreg,_btndel;
    Cursor cursor;
    TextView _btxteditfname,_btxteditsnumber,_btxteditlname,_btxteditemail,_btxteditpword,_btxteditphone;
    EditText _btxtcpword;
    private RadioButton _available;
    private RadioButton _notavailable;
    private String xtrafname,xtralname,xtrasnumber,xtraemail,xtrapword,xtraphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reservation_cashier);
        final String id,name;
        final int itemID;

        _btxteditsnumber = (TextView) findViewById(R.id.btxteditsnumber);
        _btxteditfname = (TextView) findViewById(R.id.btxteditfname);
        _btxteditlname = (TextView) findViewById(R.id.btxteditlname);
        _btxteditemail = (TextView) findViewById(R.id.btxteditemail);
        _btxteditpword = (TextView) findViewById(R.id.btxteditpword);
        _btxteditphone = (TextView) findViewById(R.id.btxteditphone);
        //_btxtcpword = (EditText) findViewById(R.id.btxtcpword);
        _available = (RadioButton)findViewById(R.id.available);
        _notavailable = (RadioButton)findViewById(R.id._notavailable);
        _btndel = (Button) findViewById(R.id.btndel);
        _bbtnreg = (Button) findViewById(R.id.bbtnreg);
        openHelper=new DatabaseHelper(this);
        mdatabaseHelper=new DatabaseHelper(this);
        Intent receivedIntent=getIntent();
        itemID=receivedIntent.getIntExtra("id", -1);
        name=receivedIntent.getStringExtra("name");
        final String dbname=name;
        //final String dbid=""+itemID;
        /*rovic mas poge ke emman*/final String dbid=itemID+"";




        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT *FROM tblreserve WHERE Show=?",new String[]{dbname});
        while(cursor.moveToNext()){
            xtrasnumber=cursor.getString(cursor.getColumnIndex("Name"));
            xtrafname=cursor.getString(cursor.getColumnIndex("Size"));
            xtralname=cursor.getString(cursor.getColumnIndex("DETAILS"));
            xtraemail=cursor.getString(cursor.getColumnIndex("VARIATION"));
            xtrapword=cursor.getString(cursor.getColumnIndex("PRICE"));
            xtraphone=cursor.getString(cursor.getColumnIndex("STATUS"));
            _btxteditsnumber.setText(xtrasnumber);
            _btxteditfname.setText(xtrafname);
            _btxteditlname.setText(xtralname);
            _btxteditemail.setText(xtraemail);
            _btxteditpword.setText(xtrapword);
            _btxteditphone.setText(xtraphone);

            //ITM_ID INTEGER PRIMARY KEY AUTOINCREMENT,Show TEXT,Name TEXT,Size TEXT, DETAILS TEXT, VARIATION TEXT,PRICE TEXT,STATUS TEXT





        }

        /*_btndel.setOnClickListener(new View.OnClickListener(){
public void onClick (View view){
    db = openHelper.getWritableDatabase();
    Cursor datateacher= db.rawQuery("DELETE FROM tblteacher WHERE ID=? AND Show=?",new String[]{dbid,dbname});
    Toast.makeText(UpdateTeacherAccount.this, "Teacher Account Deleted!", Toast.LENGTH_SHORT).show();
}*/

        //rovic mas poge ke emman

        _btndel.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){

                Toast.makeText(getApplicationContext(), "Teacher Account Deleted", Toast.LENGTH_SHORT).show();
                db = openHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                long id=db.delete("tblreserve","IDNumber=?", new String[]{dbid});
                //mas puge talaga ako sayo emman wahaha





            }

            //rovic mas poge ke emman








        });
        _bbtnreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String snumber = _btxteditsnumber.getText().toString();
                String email = _btxteditemail.getText().toString();
                String fname = _btxteditfname.getText().toString();
                String lname = _btxteditlname.getText().toString();


                if (cursor != null){
                    if (cursor.getCount() > 0){

                        db=openHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        if(_available.isChecked()) {
                            contentValues.put("Show", ""+name+"\n"+"Size: "+xtrafname+"\nPrice: Php"+xtrapword+"\nStatus: Product is Available Go To The Cashier For Payment\n" +
                                    " ");
                            contentValues.put("Status", "Product is Available Go To The Cashier For Payment");
                            long id=db.update("tblreserve",contentValues,"Show=?", new String[]{dbname});
                            Toast.makeText(getApplicationContext(),"Update Successfull",Toast.LENGTH_SHORT).show();
                        }
                        if(_notavailable.isChecked()) {
                            contentValues.put("Show", ""+name+"\n"+"Size: "+xtrafname+"\nPrice: Php"+xtrapword+"\nStatus: Sorry This Product Is Out Stock.\n" +
                                    " ");
                            contentValues.put("Status", "Sorry This Product Is Out Stock.");
                            long id=db.update("tblreserve",contentValues,"Show=?", new String[]{dbname});
                            Toast.makeText(getApplicationContext(),"Update Successfull",Toast.LENGTH_SHORT).show();
                        }

                        if(!_available.isChecked()&&!_notavailable.isChecked()){
                            Toast.makeText(getApplicationContext(), "No Response Selected!", Toast.LENGTH_SHORT).show();
                        }

                        finish();
                        startActivity(getIntent());

                        /*
                        if (_btxteditsnumber.equals("")||_btxteditemail.equals("")||_btxteditfname.equals("")||_btxteditlname.equals("")||_btxteditpword.equals("")||_btxteditphone.equals("")){


                            Toast.makeText(getApplicationContext(), "Please fill all the information", Toast.LENGTH_LONG).show();
                        }
                        else if (!_btxtcpword.getText().toString().equals(_btxteditpword.getText().toString())){

                            Toast.makeText(getApplicationContext(), "Please Check your Password", Toast.LENGTH_LONG).show();
                        }

                        else if (_btxteditemail.getText().toString().indexOf("@")==-1){
                            Toast.makeText(getApplicationContext(), "Please enter a valid Email Address", Toast.LENGTH_LONG).show();
                        }


                        else { db=openHelper.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("Show", ""+snumber+"\n"+fname+"\n"+lname);
                            contentValues.put("Status", _btxteditpword.getText().toString());
                            long id=db.update("tblreserve",contentValues,"Show=?", new String[]{dbname});
                            Toast.makeText(getApplicationContext(),"Update Successfull",Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(getApplicationContext(), getApplicationContext().class);
                            Intent intent = new Intent(getApplicationContext(), UpdateReservation.class);
                            startActivity(intent);
                            finish();
                            }
                            */
                    }

                }}



        });
    }


    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),Teacher.class);
        startActivity(i);
        finish();
    }


    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
    //emman wag mong tanggalin kaya di nagrereload agad pag dinelete yung account eh
}
