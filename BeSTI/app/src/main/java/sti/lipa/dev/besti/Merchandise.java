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
import android.widget.Toast;

public class Merchandise extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _abtnreg;
    Cursor cursor;
    String snumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise);
        openHelper=new DatabaseHelper(this);
        Intent getintent = getIntent();
        snumber = getintent.getStringExtra("number");
    }


    public void viewTran(View view) {
        db = openHelper.getWritableDatabase();
        Toast.makeText(Merchandise.this, "View Reservation.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Merchandise.this, ListViewCashier.class);
        startActivity(intent);
    }
    public void washdaysmall(View view){
        db = openHelper.getWritableDatabase();
        ContentValues cashier1Values = new ContentValues();
        cashier1Values.put("Show", snumber+"\nSHS WASH DAY SHIRT\nSize:Small\nPrice:Php150\nStatus:Pending\n ");
        cashier1Values.put("IDNumber", snumber);
        cashier1Values.put("Name", "SHS WASH DAY SHIRT");
        cashier1Values.put("Size", "Small");
        cashier1Values.put("DETAILS", "SHS Wash day shirt for SHS STI Students");
        cashier1Values.put("VARIATION", "Small");
        cashier1Values.put("Price", "150");
        cashier1Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier1Values);
        Toast.makeText(Merchandise.this, "Reservation Success!",Toast.LENGTH_SHORT).show();
    }
    public void washdaymedium(View view){
        db = openHelper.getWritableDatabase();
        ContentValues cashier2Values = new ContentValues();
        cashier2Values.put("Show", snumber+"\nSHS WASH DAY SHIRT\nSize:Medium\nPrice:Php150\nStatus:Pending\n ");
        cashier2Values.put("IDNumber", snumber);
        cashier2Values.put("Name", "SHS WASH DAY SHIRT");
        cashier2Values.put("Size", "Medium");
        cashier2Values.put("DETAILS", "SHS Wash day shirt for STI Students");
        cashier2Values.put("VARIATION", "Medium");
        cashier2Values.put("Price", "150");
        cashier2Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier2Values);
        Toast.makeText(Merchandise.this, "Reservation Success!",Toast.LENGTH_SHORT).show();
    }
    public void washdaylarge(View view){
        db = openHelper.getWritableDatabase();
        ContentValues cashier3Values = new ContentValues();
        cashier3Values.put("Show", snumber+"\nSHS WASH DAY SHIRT\nSize:Large\nPrice:Php150\nStatus:Pending\n" +
                " ");
        cashier3Values.put("IDNumber", snumber);
        cashier3Values.put("Name", "SHS WASH DAY SHIRT");
        cashier3Values.put("Size", "Large");
        cashier3Values.put("DETAILS", "SHS Wash day shirt for STI Students");
        cashier3Values.put("VARIATION", "Large");
        cashier3Values.put("Price", "150");
        cashier3Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier3Values);
        Toast.makeText(Merchandise.this, "Reservation Success!",Toast.LENGTH_SHORT).show();
    }

    ///////////

    public void buttonpin(View view) {
        db = openHelper.getWritableDatabase();
        ContentValues cashier5Values = new ContentValues();
        cashier5Values.put("Show", snumber+"\nSTI Button Pin\nSize:Normal\nPrice:Php10\nStatus:Pending\n" +
                " ");
        cashier5Values.put("IDNumber", snumber);
        cashier5Values.put("Name", "STI Button Pin");
        cashier5Values.put("Size", "Normal");
        cashier5Values.put("DETAILS", "STI Limited Edition Pins");
        cashier5Values.put("VARIATION", "Normal");
        cashier5Values.put("Price", "10");
        cashier5Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier5Values);
        Toast.makeText(Merchandise.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
    }

    public void shsjakcketsmall(View view) {
        db = openHelper.getWritableDatabase();
        ContentValues cashier6Values = new ContentValues();
        cashier6Values.put("Show", snumber+"\nSHS Jacket\nSize:Small\nPrice:Php300\nStatus:Pending\n" +
                " ");
        cashier6Values.put("IDNumber", snumber);
        cashier6Values.put("Name", "SHS Jacket");
        cashier6Values.put("Size", "Small");
        cashier6Values.put("DETAILS", "SHS Jacket for STI Students");
        cashier6Values.put("VARIATION", "Small");
        cashier6Values.put("Price", "300");
        cashier6Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier6Values);
        Toast.makeText(Merchandise.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
    }
    public void shsjakcketmedium(View view) {
        db = openHelper.getWritableDatabase();
        ContentValues cashier7Values = new ContentValues();
        cashier7Values.put("Show", snumber+"\nSHS Jacket\nSize:Medium\nPrice:Php300\nStatus:Pending\n" +
                " ");
        cashier7Values.put("IDNumber", snumber);
        cashier7Values.put("Name", "SHS Jacket");
        cashier7Values.put("Size", "Small");
        cashier7Values.put("DETAILS", "SHS Jacket for STI Students");
        cashier7Values.put("VARIATION", "Medium");
        cashier7Values.put("Price", "300");
        cashier7Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier7Values);
        Toast.makeText(Merchandise.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
    }
    public void shsjakcketlarge(View view) {
        db = openHelper.getWritableDatabase();
        ContentValues cashier8Values = new ContentValues();
        cashier8Values.put("Show", snumber+"\nSHS Jacket\nSize:Large\nPrice:Php300\nStatus:Pending\n" +
                " ");
        cashier8Values.put("IDNumber", snumber);
        cashier8Values.put("Name", "SHS Jacket");
        cashier8Values.put("Size", "Large");
        cashier8Values.put("DETAILS", "SHS Jacket for STI Students");
        cashier8Values.put("VARIATION", "Large");
        cashier8Values.put("Price", "300");
        cashier8Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier8Values);
        Toast.makeText(Merchandise.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
    }
    public void shsjakcketxlarge(View view) {
        db = openHelper.getWritableDatabase();
        ContentValues cashier9Values = new ContentValues();
        cashier9Values.put("Show", snumber+"\nSHS Jacket\nSize:Extra Large\nPrice:Php300\nStatus:Pending\n" +
                " ");
        cashier9Values.put("IDNumber", snumber);
        cashier9Values.put("Name", "SHS Jacket");
        cashier9Values.put("Size", "Extra Large");
        cashier9Values.put("DETAILS", "SHS Jacket for STI Students");
        cashier9Values.put("VARIATION", "Extra Large");
        cashier9Values.put("Price", "300");
        cashier9Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier9Values);
        Toast.makeText(Merchandise.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
    }
    public void stioriginalscap(View view) {
        db = openHelper.getWritableDatabase();
        ContentValues cashier10Values = new ContentValues();
        cashier10Values.put("Show", snumber+"\nSTI Originals Cap\nSize:Normal\nPrice:Php100\nStatus:Pending\n" +
                " ");
        cashier10Values.put("IDNumber", snumber);
        cashier10Values.put("Name", "STI Originals Cap");
        cashier10Values.put("Size", "Normal");
        cashier10Values.put("DETAILS", "Limited Edition STI Originals Cap");
        cashier10Values.put("VARIATION", "Normal");
        cashier10Values.put("Price", "100");
        cashier10Values.put("Status", "Pending");
        db.insert("tblreserve", null, cashier10Values);
        Toast.makeText(Merchandise.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
    }

    ///////////


}
