package sti.lipa.dev.besti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateTeacherAccount extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _abtnreg;
    Cursor cursor;
    String _aitem;
    EditText _atxteditfname,_atxteditsnumber,_atxteditlname,_atxteditemail,_atxteditpword,_atxteditphone,_atxtcpword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createteacher);
        _atxteditsnumber = (EditText) findViewById(R.id.atxteditsnumber);
        _atxteditfname = (EditText) findViewById(R.id.atxteditfname);
        _atxteditlname = (EditText) findViewById(R.id.atxteditlname);
        _atxteditemail = (EditText) findViewById(R.id.atxteditemail);
        _atxteditpword = (EditText) findViewById(R.id.atxteditpword);
        _atxteditphone = (EditText) findViewById(R.id.atxteditphone);
        _atxtcpword = (EditText) findViewById(R.id.atxtcpword);
        _abtnreg = (Button) findViewById(R.id.abtnreg);
        openHelper=new DatabaseHelper(this);
        Spinner _aspinner = (Spinner)findViewById(R.id.aspinner);

        _aspinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("ICT");
        categories.add("ABM");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _aspinner.setAdapter(dataAdapter);

        _abtnreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String snumber = _atxteditsnumber.getText().toString();
                String email = _atxteditemail.getText().toString();
                String fname = _atxteditfname.getText().toString();
                String lname = _atxteditlname.getText().toString();
                db = openHelper.getWritableDatabase();
                cursor = db.rawQuery("SELECT *FROM tblteacher WHERE TeacherNumber=? OR Email=?",new String[]{snumber, email});
                if (cursor != null){
                    if (cursor.getCount() > 0){
                        Toast.makeText(CreateTeacherAccount.this, "Teacher Number nor Email is already Used", Toast.LENGTH_LONG).show();
                    }


                else if (_atxteditsnumber.equals("")||_atxteditemail.equals("")||_atxteditfname.equals("")||_atxteditlname.equals("")||_atxteditpword.equals("")||_atxteditphone.equals("")){

                    Toast.makeText(CreateTeacherAccount.this, "Please fill all the information", Toast.LENGTH_LONG).show();
                }
                else if (!_atxtcpword.getText().toString().equals(_atxteditpword.getText().toString())){

                    Toast.makeText(CreateTeacherAccount.this, "Please Check your Password", Toast.LENGTH_LONG).show();
                }

                else if (_atxteditemail.getText().toString().indexOf("@")==-1){
                    Toast.makeText(CreateTeacherAccount.this, "Please enter a valid Email Address", Toast.LENGTH_LONG).show();
                }

                else { db=openHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                        contentValues.put("Show", ""+snumber+"\n"+fname+"\n"+lname);
                    contentValues.put("TeacherNumber", _atxteditsnumber.getText().toString());
                    contentValues.put("FirstName", _atxteditfname.getText().toString());
                    contentValues.put("LastName", _atxteditlname.getText().toString());
                    contentValues.put("Email", _atxteditemail.getText().toString());
                    contentValues.put("Password", _atxteditpword.getText().toString());
                        contentValues.put("Course", _aitem);
                    long id=db.insert("tblteacher",null,contentValues);
                    Toast.makeText(CreateTeacherAccount.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                    finish();}
            }}



        });
    }

    @Override
    public void onItemSelected(AdapterView<?> aparent, View view, int i, long l) {
        String aitem = aparent.getItemAtPosition(i).toString();
        _aitem=aitem;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
