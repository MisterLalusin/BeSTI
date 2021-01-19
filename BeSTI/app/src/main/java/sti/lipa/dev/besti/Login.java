package sti.lipa.dev.besti;

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

public class Login extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button  _btnlog;
    Cursor cursor;
    EditText _txteditsnumber,_txteditpword;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _txteditsnumber = (EditText) findViewById(R.id.txtsnumber);
        _txteditpword = (EditText) findViewById(R.id.txtpword);
        _btnlog = (Button) findViewById(R.id.btnlogin);
        openHelper=new DatabaseHelper(this);
        String getnumber=_txteditsnumber.getText().toString();

        db = openHelper.getReadableDatabase();
        _btnlog.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           String snumber = _txteditsnumber.getText().toString();
                                           String pass = _txteditpword.getText().toString();
                                           cursor = db.rawQuery("SELECT *FROM tbluser WHERE StudentNumber=? AND Password=?", new String[]{snumber,pass});
                                           if (cursor !=null) {
                                               if (cursor.getCount() > 0) {
                                                   Intent intent = new Intent(Login.this, StudentAccount.class);
                                                   intent.putExtra("number", snumber);
                                                   startActivity(intent);
                                                   Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_LONG).show();
                                                   finish();
                                               } else {
                                                   cursor = db.rawQuery("SELECT *FROM tblteacher WHERE TeacherNumber=? AND Password=?", new String[]{snumber, pass});
                                                   if (cursor != null) {
                                                       if (cursor.getCount() > 0) {
                                                           Intent intent = new Intent(Login.this,TeacherAccount.class);
                                                           intent.putExtra("number", snumber);
                                                           startActivity(intent);
                                                           Toast.makeText(Login.this, "Welcome Teacher!", Toast.LENGTH_SHORT).show();
                                                           finish();
                                                       } else {
                                                           cursor = db.rawQuery("SELECT *FROM tblcashier WHERE IDNUMBER=? AND Password=?", new String[]{snumber, pass});
                                                           if (cursor != null) {
                                                               if (cursor.getCount() > 0) {
                                                                   Intent intent = new Intent(Login.this, CashierAccount.class);
                                                                   intent.putExtra("number", snumber);
                                                                   startActivity(intent);
                                                                   Toast.makeText(Login.this, "Welcome Cashier!", Toast.LENGTH_SHORT).show();
                                                                   finish();
                                                               } else {
                                                                   cursor = db.rawQuery("SELECT *FROM tbladmin WHERE IDNUMBER=? AND Password=?", new String[]{snumber, pass});
                                                                   if (cursor != null) {
                                                                       if (cursor.getCount() > 0) {
                                                                           Intent intent = new Intent(Login.this, Admin.class);
                                                                           intent.putExtra("number", snumber);
                                                                           startActivity(intent);
                                                                           Toast.makeText(Login.this, "Welcome Admin!", Toast.LENGTH_SHORT).show();
                                                                           finish();
                                                                       } else {
                                                                           Toast.makeText(Login.this, "Login Error!", Toast.LENGTH_LONG).show();
                                                                       }
                                                                   }

                                                               }
                                                           }

                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   }

        );
    }
    public void Register (View view){
        Intent elyen= new Intent(Login.this, Register.class);
        startActivity(elyen);
    }
}
























