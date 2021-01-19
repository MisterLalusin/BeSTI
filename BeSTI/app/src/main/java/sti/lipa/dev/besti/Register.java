package sti.lipa.dev.besti;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Register extends Activity implements AdapterView.OnItemSelectedListener {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    Cursor cursor;
    EditText _txteditfname,_txteditsnumber,_txteditlname,_txteditemail,_txteditpword,_txteditphone,_txtcpword;
    String _item;
    private ImageView _iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _txteditsnumber = (EditText) findViewById(R.id.txteditsnumber);
        _txteditfname = (EditText) findViewById(R.id.txteditfname);
        _txteditlname = (EditText) findViewById(R.id.txteditlname);
        _txteditemail = (EditText) findViewById(R.id.txteditemail);
        _txteditpword = (EditText) findViewById(R.id.txteditpword);
        _txteditphone = (EditText) findViewById(R.id.txteditphone);
        _txtcpword = (EditText) findViewById(R.id.txtcpword);
        openHelper=new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        Spinner _spinner = (Spinner)findViewById(R.id.spinner);

        _spinner.setOnItemSelectedListener(this);

        final ImageView _iv = new ImageView(this);
        _iv.setImageResource(R.drawable.ic_choose);

        List<String> categories = new ArrayList<String>();
        categories.add("ICT");
        categories.add("ABM");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _spinner.setAdapter(dataAdapter);

        _btnreg = (Button) findViewById(R.id.btnreg);
        _btnreg.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v){
                                           String item="";
                                           String snumber = _txteditsnumber.getText().toString();
                                           String email = _txteditemail.getText().toString();
                                           String fname = _txteditfname.getText().toString();
                                           String lname = _txteditlname.getText().toString();
                                           cursor = db.rawQuery("SELECT *FROM tbluser WHERE StudentNumber=? OR Email=?",new String[]{snumber, email});
                                           if (cursor != null){
                                               if (cursor.getCount() > 0){
                                                   Toast.makeText(Register.this, "Student Number nor Email is already Used", Toast.LENGTH_LONG).show();
                                               }



                                               else if (_txteditfname.getText().toString().equals("")||_txteditlname.getText().toString().equals("")||_txteditsnumber.getText().toString().equals("")||_txteditemail.getText().toString().equals("")||_txteditpword.getText().toString().equals("")||_txteditphone.getText().toString().equals("")){

                                                   Toast.makeText(Register.this, "Please fill all the information", Toast.LENGTH_LONG).show();
                                               }
                                               else if (!_txtcpword.getText().toString().equals(_txteditpword.getText().toString())){

                                                   Toast.makeText(Register.this, "Please Check your Password", Toast.LENGTH_LONG).show();
                                               }

                                               else if (_txteditemail.getText().toString().indexOf("@")==-1){
                                                   Toast.makeText(Register.this, "Please enter a valid Email Address", Toast.LENGTH_LONG).show();
                                               }
                                               else if (_txteditpword.getText().length()<8){
                                                   Toast.makeText(Register.this, "Password must be atleast 8 characters", Toast.LENGTH_LONG).show();
                                               }
                                               else if (_txteditsnumber.getText().length()<10||_txteditsnumber.getText().length()>10){
                                                   Toast.makeText(Register.this, "Please Enter a valid student number \n Example: 2000012345", Toast.LENGTH_LONG).show();
                                               }

                                               else { db=openHelper.getWritableDatabase();
                                                   ContentValues contentValues = new ContentValues();
                                                   contentValues.put("Show", "" + snumber + "\n" + fname + "\n" + lname);
                                                   contentValues.put("StudentNumber", _txteditsnumber.getText().toString());
                                                   contentValues.put("FirstName", _txteditfname.getText().toString());
                                                   contentValues.put("LastName", _txteditlname.getText().toString());
                                                   contentValues.put("Email", _txteditemail.getText().toString());
                                                   contentValues.put("Password", _txteditpword.getText().toString());
                                                   contentValues.put("Phone", _txteditphone.getText().toString());
                                                   contentValues.put("Course", _item);
                                                   contentValues.put("Profile", imageViewToByte(_iv));
                                                   long id=db.insert("tbluser",null,contentValues);
                                                   Toast.makeText(Register.this,"Course "+_item,Toast.LENGTH_SHORT).show();
                                                   Toast.makeText(Register.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                                                   Intent intent = new Intent(Register.this, Login.class);
                                                   startActivity(intent);
                                                   finish();}
                                           }
                                       }



                                   }
        );
    }
    public void Login (View v){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        _item=item; }
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    //

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

        //
    }
}
