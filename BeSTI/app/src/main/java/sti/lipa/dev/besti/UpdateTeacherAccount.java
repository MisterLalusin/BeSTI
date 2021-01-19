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
import android.widget.Toast;

public class UpdateTeacherAccount extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper mdatabaseHelper;
    Button _bbtnreg,_btndel;
    Cursor cursor;
    EditText _btxteditfname,_btxteditsnumber,_btxteditlname,_btxteditemail,_btxteditpword,_btxteditphone,_btxtcpword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String id,name;
        final int itemID;
        setContentView(R.layout.activity_update_teacher_account);
        _btxteditsnumber = (EditText) findViewById(R.id.btxteditsnumber);
        _btxteditfname = (EditText) findViewById(R.id.btxteditfname);
        _btxteditlname = (EditText) findViewById(R.id.btxteditlname);
        _btxteditemail = (EditText) findViewById(R.id.btxteditemail);
        _btxteditpword = (EditText) findViewById(R.id.btxteditpword);
        _btxteditphone = (EditText) findViewById(R.id.btxteditphone);
        _btxtcpword = (EditText) findViewById(R.id.btxtcpword);
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


        String xtrafname;
        String xtralname;
        String xtrasnumber;
        String xtraemail;
        String xtrapword;
        String xtraphone;
        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT *FROM tblteacher WHERE Show=?",new String[]{dbname});
        while(cursor.moveToNext()){
            xtrasnumber=cursor.getString(cursor.getColumnIndex("TeacherNumber"));
            xtrafname=cursor.getString(cursor.getColumnIndex("FirstName"));
            xtralname=cursor.getString(cursor.getColumnIndex("LastName"));
            xtraemail=cursor.getString(cursor.getColumnIndex("Email"));
            xtrapword=cursor.getString(cursor.getColumnIndex("Password"));
            xtraphone=cursor.getString(cursor.getColumnIndex("Phone"));
            _btxteditsnumber.setText(xtrasnumber);
            _btxteditfname.setText(xtrafname);
            _btxteditlname.setText(xtralname);
            _btxteditemail.setText(xtraemail);
            _btxteditpword.setText(xtrapword);
            _btxteditphone.setText(xtraphone);





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

                Toast.makeText(UpdateTeacherAccount.this, "Teacher Account Deleted", Toast.LENGTH_SHORT).show();
                db = openHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                long id=db.delete("tblteacher","ID=?", new String[]{dbid});
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
                        if (_btxteditsnumber.equals("")||_btxteditemail.equals("")||_btxteditfname.equals("")||_btxteditlname.equals("")||_btxteditpword.equals("")||_btxteditphone.equals("")){

                            Toast.makeText(UpdateTeacherAccount.this, "Please fill all the information", Toast.LENGTH_LONG).show();
                        }
                        else if (!_btxtcpword.getText().toString().equals(_btxteditpword.getText().toString())){

                            Toast.makeText(UpdateTeacherAccount.this, "Please Check your Password", Toast.LENGTH_LONG).show();
                        }

                        else if (_btxteditemail.getText().toString().indexOf("@")==-1){
                            Toast.makeText(UpdateTeacherAccount.this, "Please enter a valid Email Address", Toast.LENGTH_LONG).show();
                        }

                        else { db=openHelper.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("Show", ""+snumber+"\n"+fname+"\n"+lname);
                            contentValues.put("TeacherNumber", _btxteditsnumber.getText().toString());
                            contentValues.put("FirstName", _btxteditfname.getText().toString());
                            contentValues.put("LastName", _btxteditlname.getText().toString());
                            contentValues.put("Email", _btxteditemail.getText().toString());
                            contentValues.put("Password", _btxteditpword.getText().toString());
                            long id=db.update("tblteacher",contentValues,"Show=?", new String[]{dbname});
                            Toast.makeText(UpdateTeacherAccount.this,"Update Successfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpdateTeacherAccount.this, ViewTeachers.class);
                            startActivity(intent);
                            finish();}
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
