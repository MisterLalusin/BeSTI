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
import android.widget.Toast;

public class UpdatePasswordCashier extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnupdateadminpassword;
    EditText _atxtoldpass, _atxtunpass, _atxtcidpass;

    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password_cashier);

        _atxtoldpass = (EditText)findViewById(R.id.atxteditaoldpass);
        _atxtunpass = (EditText)findViewById(R.id.atxteditanewpass);
        _atxtcidpass = (EditText)findViewById(R.id.atxteditaconpass);

        _btnupdateadminpassword = (Button)findViewById(R.id.abtnupdatepassadmin);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();

        _btnupdateadminpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = _atxtoldpass.getText().toString();
                String newpass = _atxtunpass.getText().toString();
                String conpass = _atxtcidpass.getText().toString();

                Intent intenta = getIntent();
                String xtrapass="";
                String email = "cashier@sti.edu";

                cursor = db.rawQuery("SELECT *FROM tblcashier WHERE Email =?", new String[]{email});

                //

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        xtrapass = cursor.getString(cursor.getColumnIndex("Password"));
                    }
                    if(!oldpass.equals(xtrapass)) {
                        Toast.makeText(getApplicationContext(), "Please Check Your Old Password", Toast.LENGTH_SHORT).show();
                    }
                    else if (oldpass.equals(newpass)) {
                        Toast.makeText(getApplicationContext(), "You Entered your old password", Toast.LENGTH_SHORT).show();
                    }
                    else if (newpass.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your New Password", Toast.LENGTH_SHORT).show();
                    }
                    else if (newpass.length()<8) {
                        Toast.makeText(getApplicationContext(), "Password Must Be Atleast 8 Characters", Toast.LENGTH_SHORT).show();
                    }
                    else if (!newpass.equals(conpass)) {
                        Toast.makeText(getApplicationContext(), "Please Confirm Your Password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        db=openHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("Password", _atxtunpass.getText().toString());
                        long id = db.update("tblcashier", contentValues,"Email =?", new String[]{email});
                        Toast.makeText(getApplicationContext(), "Password Was Successfully Changed", Toast.LENGTH_SHORT).show();
                    }
                }

                //

            }
        });


    }
}
