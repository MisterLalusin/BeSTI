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

public class UpdateAdminPassword extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnupdateadminpassword;
    EditText _txtoldpass, _txtunpass, _txtcidpass;
    Button _buttonupdate;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin_password);

        _txtoldpass = (EditText)findViewById(R.id.txteditaoldpass);
        _txtunpass = (EditText)findViewById(R.id.txteditanewpass);
        _txtcidpass = (EditText)findViewById(R.id.txteditaconpass);

        _btnupdateadminpassword = (Button)findViewById(R.id._btnupdatepassadmin);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();

        _btnupdateadminpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = _txtoldpass.getText().toString();
                String newpass = _txtunpass.getText().toString();
                String conpass = _txtcidpass.getText().toString();

                Intent intenta = getIntent();
                String xtrapass="";
                String email = "sti@admin.com";

                cursor = db.rawQuery("SELECT *FROM tbladmin WHERE Email =?", new String[]{email});

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
                        contentValues.put("Password", _txtunpass.getText().toString());
                        long id = db.update("tbladmin", contentValues,"Email =?", new String[]{email});
                        Toast.makeText(getApplicationContext(), "Password Was Successfully Changed", Toast.LENGTH_SHORT).show();
                    }
                }

                //

            }
        });


    }
}
