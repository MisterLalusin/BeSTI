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

public class UpdateStudentAccount extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _dbtnreg;
    Cursor cursor;
    EditText _dtxteditfname,_dtxteditsnumber,_dtxteditlname,_dtxteditemail,_dtxteditpword,_dtxteditphone,_dtxtcpword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id,name;
        int itemID;
        setContentView(R.layout.activity_update_student_account);
        _dtxteditsnumber = (EditText) findViewById(R.id.dtxteditsnumber);
        _dtxteditfname = (EditText) findViewById(R.id.dtxteditfname);
        _dtxteditlname = (EditText) findViewById(R.id.dtxteditlname);
        _dtxteditemail = (EditText) findViewById(R.id.dtxteditemail);
        _dtxteditpword = (EditText) findViewById(R.id.dtxteditpword);
        _dtxteditphone = (EditText) findViewById(R.id.dtxteditphone);
        _dtxtcpword = (EditText) findViewById(R.id.dtxtcpword);
        _dbtnreg = (Button) findViewById(R.id.dbtnreg);
        openHelper=new DatabaseHelper(this);
        Intent receivedIntent=getIntent();
        itemID=receivedIntent.getIntExtra("id", -1);
        name=receivedIntent.getStringExtra("name");
        id=""+itemID;
        final String dbname=name;

        String xtrafname;
        String xtralname;
        String xtrasnumber;
        String xtraemail;
        String xtrapword;
        String xtraphone;
        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT *FROM tbluser WHERE Show=?",new String[]{dbname});
        while(cursor.moveToNext()){
            xtrasnumber=cursor.getString(cursor.getColumnIndex("StudentNumber"));
            xtrafname=cursor.getString(cursor.getColumnIndex("FirstName"));
            xtralname=cursor.getString(cursor.getColumnIndex("LastName"));
            xtraemail=cursor.getString(cursor.getColumnIndex("Email"));
            xtrapword=cursor.getString(cursor.getColumnIndex("Password"));
            xtraphone=cursor.getString(cursor.getColumnIndex("Phone"));
            _dtxteditsnumber.setText(xtrasnumber);
            _dtxteditfname.setText(xtrafname);
            _dtxteditlname.setText(xtralname);
            _dtxteditemail.setText(xtraemail);
            _dtxteditpword.setText(xtrapword);
            _dtxteditphone.setText(xtraphone);





        }
        _dbtnreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String snumber = _dtxteditsnumber.getText().toString();
                String email = _dtxteditemail.getText().toString();
                String fname = _dtxteditfname.getText().toString();
                String lname = _dtxteditlname.getText().toString();
                db = openHelper.getWritableDatabase();
                cursor = db.rawQuery("SELECT *FROM tbluser WHERE Show=?",new String[]{dbname});


                if (cursor != null){
                    if (cursor.getCount() > 0){
                        if (_dtxteditsnumber.equals("")||_dtxteditemail.equals("")||_dtxteditfname.equals("")||_dtxteditlname.equals("")||_dtxteditpword.equals("")||_dtxteditphone.equals("")){

                            Toast.makeText(UpdateStudentAccount.this, "Please fill all the information", Toast.LENGTH_LONG).show();
                        }
                        else if (!_dtxtcpword.getText().toString().equals(_dtxteditpword.getText().toString())){

                            Toast.makeText(UpdateStudentAccount.this, "Please Check your Password", Toast.LENGTH_LONG).show();
                        }

                        else if (_dtxteditemail.getText().toString().indexOf("@")==-1){
                            Toast.makeText(UpdateStudentAccount.this, "Please enter a valid Email Address", Toast.LENGTH_LONG).show();
                        }

                        else { db=openHelper.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("Show", ""+snumber+"\n"+fname+"\n"+lname);
                            contentValues.put("studentNumber", _dtxteditsnumber.getText().toString());
                            contentValues.put("FirstName", _dtxteditfname.getText().toString());
                            contentValues.put("LastName", _dtxteditlname.getText().toString());
                            contentValues.put("Email", _dtxteditemail.getText().toString());
                            contentValues.put("Password", _dtxteditpword.getText().toString());
                            contentValues.put("Phone", _dtxteditphone.getText().toString());
                            long id=db.update("tbluser",contentValues,"Show=?", new String[]{dbname});
                            Toast.makeText(UpdateStudentAccount.this,"Update Successfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpdateStudentAccount.this, ViewStudents.class);
                            startActivity(intent);
                            finish();}
                    }

                }}



        });
    }
}
