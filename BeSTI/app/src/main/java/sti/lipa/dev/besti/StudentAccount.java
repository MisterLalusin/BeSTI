package sti.lipa.dev.besti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StudentAccount extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor, cursor2;
    TextView _announcementstudent,_loginasuser;
    private String number, course, fname, lname;

    //
    ImageView _imageView;
    final int REQUEST_CODE_GALLERY = 999;
    private Button _btnChoose;
    private ImageView _disbanner;
    private byte[] _dbgetbanner;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_account);
        _loginasuser = (TextView) findViewById(R.id.loginasuser);
        Intent getintent = getIntent();
        number = getintent.getStringExtra("number");
        String snumber = number;
        String xtrasnumber ="";
        String xtrafname = "";
        String xtralname = "";
        String xtraemail = "";
        String xtrapword = "";
        String xtraphone = "";
        String xtracourse = "";
        _announcementstudent = (TextView) findViewById(R.id.txtannouncementstudent);
        String message = "Annnouncement";
        String level="User";
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String xtramessage = "";
        cursor = db.rawQuery("SELECT *FROM tblannouncement WHERE Level=?", new String[]{level});
        while (cursor.moveToNext()) {
            xtramessage = cursor.getString(cursor.getColumnIndex("Announcement"));
            _announcementstudent.setText(xtramessage);
        }





        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT *FROM tbluser WHERE StudentNumber=?", new String[]{number});
        while (cursor.moveToNext()) {
            xtrasnumber = cursor.getString(cursor.getColumnIndex("StudentNumber"));
            xtrafname = cursor.getString(cursor.getColumnIndex("FirstName"));
            xtralname = cursor.getString(cursor.getColumnIndex("LastName"));
            xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
            xtrapword = cursor.getString(cursor.getColumnIndex("Password"));
            xtraphone = cursor.getString(cursor.getColumnIndex("Phone"));
            xtracourse = cursor.getString(cursor.getColumnIndex("Course"));
            _loginasuser.setText("You are logged in as: "+xtraemail);
        }
        course = xtracourse;
        fname = xtrafname;
        lname = xtralname;
        Intent getintent2 = getIntent();
        number = getintent2.getStringExtra("number");

        //
        _btnChoose = (Button)findViewById(R.id.btnchoose);
        _imageView = (ImageView)findViewById(R.id.imgprofile);

        _btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(StudentAccount.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);

                // , REQUEST_CODE_GALLERY);
                //Toast.makeText(getApplicationContext(), "Method Test", Toast.LENGTH_SHORT).show();
            }
        });

        //

        ////

        _disbanner = (ImageView) findViewById(R.id.imgprofile);////////////////////////////////

        db = openHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT *FROM  tbluser WHERE StudentNumber=?", new String[]{snumber});
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext())
                _dbgetbanner = cursor.getBlob(cursor.getColumnIndex("Profile"));
        }

        Bitmap disbanner = BitmapFactory.decodeByteArray(_dbgetbanner, 0, _dbgetbanner.length);

        _disbanner.setImageBitmap(disbanner);

        ////



        cursor = db.rawQuery("SELECT *FROM tblscheduser WHERE StudentNumber=?", new String[]{snumber});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Toast.makeText(StudentAccount.this, "Welcome " + fname + " " + lname + "!", Toast.LENGTH_LONG).show();
            }
            else if (xtracourse.equals("ICT")) {
                ContentValues sched1Values = new ContentValues();
                sched1Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "9:00AM\n" + "Monday\n" + "Statistics and Probability\n" + "Room 102");
                sched1Values.put("StudentNumber", number);
                sched1Values.put("FirstName", xtrafname);
                sched1Values.put("LastName", xtralname);
                sched1Values.put("Time", "9:00AM");
                sched1Values.put("Day", "Monday");
                sched1Values.put("Subject", "Statistics and Probability");
                sched1Values.put("Details", "Room 102");
                db.insert("tblscheduser", null, sched1Values);

                ContentValues sched2Values = new ContentValues();
                sched2Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "10:30AM\n" + "Monday\n" + "Earth and Life Science\n" + "Room 108");
                sched2Values.put("StudentNumber", number);
                sched2Values.put("FirstName", xtrafname);
                sched2Values.put("LastName", xtralname);
                sched2Values.put("Time", "10:30AM");
                sched2Values.put("Day", "Monday");
                sched2Values.put("Subject", "Earth and Life Science");
                sched2Values.put("Details", "Room 102");
                sched2Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched2Values);

                ContentValues sched3Values = new ContentValues();
                sched3Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Monday\n" + "Oral Communication\n" + "Room 102");
                sched3Values.put("StudentNumber", number);
                sched3Values.put("FirstName", xtrafname);
                sched3Values.put("LastName", xtralname);
                sched3Values.put("Time", "12:00PM");
                sched3Values.put("Day", "Monday");
                sched3Values.put("Subject", "Oral Communication");
                sched3Values.put("Details", "Room 102");
                sched3Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched3Values);

                ContentValues sched4Values = new ContentValues();
                sched4Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30AM\n" + "Monday\n" + "Lunch Break\n" + "");
                sched4Values.put("StudentNumber", number);
                sched4Values.put("FirstName", xtrafname);
                sched4Values.put("LastName", xtralname);
                sched4Values.put("Time", "1:30AM");
                sched4Values.put("Day", "Monday");
                sched4Values.put("Subject", "Lunch Break");
                sched4Values.put("Details", "Room 102");
                sched4Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched4Values);


                ContentValues schedICTnoClass = new ContentValues();
                schedICTnoClass.put("Show", "Today is Tuesday\n You don't have any classes \n");
                schedICTnoClass.put("StudentNumber", number);
                schedICTnoClass.put("FirstName", "N/A");
                schedICTnoClass.put("LastName", "N/A");
                schedICTnoClass.put("Time", "N/A");
                schedICTnoClass.put("Day", "Tuesday");
                schedICTnoClass.put("Subject", "N/A");
                schedICTnoClass.put("Details", "N/A");
                schedICTnoClass.put("Course", xtracourse);
                db.insert("tblscheduser", null, schedICTnoClass);


                ContentValues schedICTnoClass1 = new ContentValues();
                schedICTnoClass1.put("Show", "Today is Friday\n You don't have any classes \n");
                schedICTnoClass1.put("StudentNumber", number);
                schedICTnoClass1.put("FirstName", "N/A");
                schedICTnoClass1.put("LastName", "N/A");
                schedICTnoClass1.put("Time", "N/A");
                schedICTnoClass1.put("Day", "Friday");
                schedICTnoClass1.put("Subject", "N/A");
                schedICTnoClass1.put("Details", "N/A");
                schedICTnoClass1.put("Course", xtracourse);
                db.insert("tblscheduser", null, schedICTnoClass1);

                ContentValues schedICTnoClass2 = new ContentValues();
                schedICTnoClass2.put("Show", "Today is Sunday\n You don't have any classes \n");
                schedICTnoClass2.put("StudentNumber", number);
                schedICTnoClass2.put("FirstName", "N/A");
                schedICTnoClass2.put("LastName", "N/A");
                schedICTnoClass2.put("Time", "N/A");
                schedICTnoClass2.put("Day", "Sunday");
                schedICTnoClass2.put("Subject", "N/A");
                schedICTnoClass2.put("Details", "N/A");
                schedICTnoClass2.put("Course", xtracourse);
                db.insert("tblscheduser", null, schedICTnoClass2);


                ContentValues sched5Values = new ContentValues();
                sched5Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Monday\n" + "Practical Research\n" + "Room 102");
                sched5Values.put("StudentNumber", number);
                sched5Values.put("FirstName", xtrafname);
                sched5Values.put("LastName", xtralname);
                sched5Values.put("Time", "3:00PM");
                sched5Values.put("Day", "Monday");
                sched5Values.put("Subject", "Practical Research");
                sched5Values.put("Details", "Room 102");
                sched5Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched5Values);


                ContentValues sched7Values = new ContentValues();
                sched7Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30PM\n" + "Monday\n" + "DISMISSAL\n" + "");
                sched7Values.put("StudentNumber", number);
                sched7Values.put("FirstName", xtrafname);
                sched7Values.put("LastName", xtralname);
                sched7Values.put("Time", "4:30PM");
                sched7Values.put("Day", "Monday");
                sched7Values.put("Subject", "DISMISSAL");
                sched7Values.put("Details", "Room 102");
                sched7Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched7Values);

                ContentValues sched11Values = new ContentValues();
                sched11Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "9:00AM\n" + "Thursday\n" + "Statistics and Probability\n" + "Room 102");
                sched11Values.put("StudentNumber", number);
                sched11Values.put("FirstName", xtrafname);
                sched11Values.put("LastName", xtralname);
                sched11Values.put("Time", "9:00AM");
                sched11Values.put("Day", "Thursday");
                sched11Values.put("Subject", "Statistics and Probability");
                sched11Values.put("Details", "Room 102");
                sched11Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched11Values);

                ContentValues sched12Values = new ContentValues();
                sched12Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "10:30AM\n" + "Thursday\n" + "Science\n" + "Room 102");
                sched12Values.put("StudentNumber", number);
                sched12Values.put("FirstName", xtrafname);
                sched12Values.put("LastName", xtralname);
                sched12Values.put("Time", "10:30AM");
                sched12Values.put("Day", "Thursday");
                sched12Values.put("Subject", "Earth and Life Science");
                sched12Values.put("Details", "Room 102");
                sched12Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched12Values);

                ContentValues sched13Values = new ContentValues();
                sched13Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Thursday\n" + "Lunch\n" + "");
                sched13Values.put("StudentNumber", number);
                sched13Values.put("FirstName", xtrafname);
                sched13Values.put("LastName", xtralname);
                sched13Values.put("Time", "12:00PM");
                sched13Values.put("Day", "Thursday");
                sched13Values.put("Subject", "Lunch");
                sched13Values.put("Details", "Room 102");
                sched13Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched13Values);

                ContentValues sched14Values = new ContentValues();
                sched14Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30PM\n" + "Thursday\n" + "Lunch Break\n" + "");
                sched14Values.put("StudentNumber", number);
                sched14Values.put("FirstName", xtrafname);
                sched14Values.put("LastName", xtralname);
                sched14Values.put("Time", "1:30PM");
                sched14Values.put("Day", "Thursday");
                sched14Values.put("Subject", "Lunch Break");
                sched14Values.put("Details", "Room 102");
                sched14Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched14Values);


                ContentValues sched15Values = new ContentValues();
                sched15Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Thursday\n" + "Practical Research\n" + "Room 102\n" +
                        " ");
                sched15Values.put("StudentNumber", number);
                sched15Values.put("FirstName", xtrafname);
                sched15Values.put("LastName", xtralname);
                sched15Values.put("Time", "3:00PM");
                sched15Values.put("Day", "Thursday");
                sched15Values.put("Subject", "Practical Research");
                sched15Values.put("Details", "Room 102");
                sched15Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched15Values);


                ContentValues sched17Values = new ContentValues();
                sched17Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30\n" + "Thursday\n" + "DISMISSAL\n" + "Room 102\n" +
                        " ");
                sched17Values.put("StudentNumber", number);
                sched17Values.put("FirstName", xtrafname);
                sched17Values.put("LastName", xtralname);
                sched17Values.put("Time", "5:00PM");
                sched17Values.put("Day", "Thursday");
                sched17Values.put("Subject", "DISMISSAL");
                sched17Values.put("Details", "Room 102");
                sched17Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched17Values);


                ContentValues ICTsched13Values = new ContentValues();
                ICTsched13Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Wednesday\n" + "\"WEB PROG1\n" + "Room 102\n" +
                        " ");
                ICTsched13Values.put("StudentNumber", number);
                ICTsched13Values.put("FirstName", xtrafname);
                ICTsched13Values.put("LastName", xtralname);
                ICTsched13Values.put("Time", "12:00PM");
                ICTsched13Values.put("Day", "Wednesday");
                ICTsched13Values.put("Subject", "WEB PROG1");
                ICTsched13Values.put("Details", "Room 102");
                ICTsched13Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTsched13Values);

                ContentValues ICTsched14Values = new ContentValues();
                ICTsched14Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30PM\n" + "Wednesday\n" + "MOBILE APP1\n" + "");
                ICTsched14Values.put("StudentNumber", number);
                ICTsched14Values.put("FirstName", xtrafname);
                ICTsched14Values.put("LastName", xtralname);
                ICTsched14Values.put("Time", "1:30PM");
                ICTsched14Values.put("Day", "Wednesday");
                ICTsched14Values.put("Subject", "MOBILE APP1");
                ICTsched14Values.put("Details", "Room 102");
                ICTsched14Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTsched14Values);


                ContentValues ICTsched15Values = new ContentValues();
                ICTsched15Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Wednesday\n" + "MOBILE APP2\n" + "Room 102\n" +
                        " ");
                ICTsched15Values.put("StudentNumber", number);
                ICTsched15Values.put("FirstName", xtrafname);
                ICTsched15Values.put("LastName", xtralname);
                ICTsched15Values.put("Time", "3:00PM");
                ICTsched15Values.put("Day", "Wednesday");
                ICTsched15Values.put("Subject", "MOBILE APP2");
                ICTsched15Values.put("Details", "Room 102");
                ICTsched15Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTsched15Values);


                ContentValues ICTsched17Values = new ContentValues();
                ICTsched17Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30\n" + "Wednesday\n" + "DISMISSAL\n" + "Room 102\n" +
                        " ");
                ICTsched17Values.put("StudentNumber", number);
                ICTsched17Values.put("FirstName", xtrafname);
                ICTsched17Values.put("LastName", xtralname);
                ICTsched17Values.put("Time", "5:00PM");
                ICTsched17Values.put("Day", "Wednesday");
                ICTsched17Values.put("Subject", "DISMISSAL");
                ICTsched17Values.put("Details", "Room 102");
                ICTsched17Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTsched17Values);


                ContentValues ICTschedSAT13Values = new ContentValues();
                ICTschedSAT13Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Wednesday\n" + "WEB PROG1\n" + "Room 102\n" +
                        " ");
                ICTschedSAT13Values.put("StudentNumber", number);
                ICTschedSAT13Values.put("FirstName", xtrafname);
                ICTschedSAT13Values.put("LastName", xtralname);
                ICTschedSAT13Values.put("Time", "12:00PM");
                ICTschedSAT13Values.put("Day", "Wednesday");
                ICTschedSAT13Values.put("Subject", "WEB PROG1");
                ICTschedSAT13Values.put("Details", "Room 102");
                ICTschedSAT13Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTschedSAT13Values);

                ContentValues ICTschedSAT14Values = new ContentValues();
                ICTschedSAT14Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30PM\n" + "Wednesday\n" + "MOBILE APP1\n" + "");
                ICTschedSAT14Values.put("StudentNumber", number);
                ICTschedSAT14Values.put("FirstName", xtrafname);
                ICTschedSAT14Values.put("LastName", xtralname);
                ICTschedSAT14Values.put("Time", "1:30PM");
                ICTschedSAT14Values.put("Day", "Wednesday");
                ICTschedSAT14Values.put("Subject", "MOBILE APP1");
                ICTschedSAT14Values.put("Details", "Room 102");
                ICTschedSAT14Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTschedSAT14Values);


                ContentValues ICTschedSAT15Values = new ContentValues();
                ICTschedSAT15Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Wednesday\n" + "MOBILE APP2\n" + "Room 102\n" +
                        " ");
                ICTschedSAT15Values.put("StudentNumber", number);
                ICTschedSAT15Values.put("FirstName", xtrafname);
                ICTschedSAT15Values.put("LastName", xtralname);
                ICTschedSAT15Values.put("Time", "3:00PM");
                ICTschedSAT15Values.put("Day", "Wednesday");
                ICTschedSAT15Values.put("Subject", "MOBILE APP2");
                ICTschedSAT15Values.put("Details", "Room 102");
                ICTschedSAT15Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTschedSAT15Values);


                ContentValues ICTschedSAT17Values = new ContentValues();
                ICTschedSAT17Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30\n" + "Wednesday\n" + "DISMISSAL\n" + "Room 102\n" +
                        " ");
                ICTschedSAT17Values.put("StudentNumber", number);
                ICTschedSAT17Values.put("FirstName", xtrafname);
                ICTschedSAT17Values.put("LastName", xtralname);
                ICTschedSAT17Values.put("Time", "5:00PM");
                ICTschedSAT17Values.put("Day", "Wednesday");
                ICTschedSAT17Values.put("Subject", "DISMISSAL");
                ICTschedSAT17Values.put("Details", "Room 102");
                ICTschedSAT17Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ICTschedSAT17Values);

                Toast.makeText(StudentAccount.this, "ICT Schedule Successfully Inserted!", Toast.LENGTH_SHORT).show();

            }

            else if (xtracourse.equals("ABM")){


                ContentValues schedICTnoClass1 = new ContentValues();
                schedICTnoClass1.put("Show", "Today is Wednesday\n You don't have any classes \n ");
                schedICTnoClass1.put("StudentNumber", number);
                schedICTnoClass1.put("FirstName", "N/A");
                schedICTnoClass1.put("LastName", "N/A");
                schedICTnoClass1.put("Time", "N/A");
                schedICTnoClass1.put("Day", "Wednesday");
                schedICTnoClass1.put("Subject", "N/A");
                schedICTnoClass1.put("Details", "N/A");
                schedICTnoClass1.put("Course", xtracourse);
                db.insert("tblscheduser", null, schedICTnoClass1);

                ContentValues schedICTnoClass2 = new ContentValues();
                schedICTnoClass2.put("Show", "Today is Saturday\n You don't have any classes \n ");
                schedICTnoClass2.put("StudentNumber", number);
                schedICTnoClass2.put("FirstName", "N/A");
                schedICTnoClass2.put("LastName", "N/A");
                schedICTnoClass2.put("Time", "N/A");
                schedICTnoClass2.put("Day", "Saturday");
                schedICTnoClass2.put("Subject", "N/A");
                schedICTnoClass2.put("Details", "N/A");
                schedICTnoClass2.put("Course", xtracourse);
                db.insert("tblscheduser", null, schedICTnoClass2);

                ContentValues schedICTnoClass3 = new ContentValues();
                schedICTnoClass3.put("Show", "Today is Sunday\n You don't have any classes \n ");
                schedICTnoClass3.put("StudentNumber", number);
                schedICTnoClass3.put("FirstName", "N/A");
                schedICTnoClass3.put("LastName", "N/A");
                schedICTnoClass3.put("Time", "N/A");
                schedICTnoClass3.put("Day", "Sunday");
                schedICTnoClass3.put("Subject", "N/A");
                schedICTnoClass3.put("Details", "N/A");
                schedICTnoClass3.put("Course", xtracourse);
                db.insert("tblscheduser", null, schedICTnoClass3);


                ContentValues sched1Values = new ContentValues();
                sched1Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "9:00AM\n" + "Thursday\n" + "Statistics and Probability\n" + "Room 206\n" +
                        " ");
                sched1Values.put("StudentNumber", number);
                sched1Values.put("FirstName", xtrafname);
                sched1Values.put("LastName", xtralname);
                sched1Values.put("Time", "9:00AM");
                sched1Values.put("Day", "Thursday");
                sched1Values.put("Subject", "Statistics and Probability");
                sched1Values.put("Details", "Room 206");
                sched1Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched1Values);

                ContentValues sched2Values = new ContentValues();
                sched2Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "10:30AM\n" + "Thursday\n" + "Earth and Life Science\n" + "Room 108\n" +
                        " ");
                sched2Values.put("StudentNumber", number);
                sched2Values.put("FirstName", xtrafname);
                sched2Values.put("LastName", xtralname);
                sched2Values.put("Time", "10:30AM");
                sched2Values.put("Day", "Thursday");
                sched2Values.put("Subject", "Earth and Life Science");
                sched2Values.put("Details", "Room 206");
                sched2Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched2Values);

                ContentValues sched3Values = new ContentValues();
                sched3Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Thursday\n" + "Oral Communication\n" + "Room 206\n" +
                        " ");
                sched3Values.put("StudentNumber", number);
                sched3Values.put("FirstName", xtrafname);
                sched3Values.put("LastName", xtralname);
                sched3Values.put("Time", "12:00PM");
                sched3Values.put("Day", "Thursday");
                sched3Values.put("Subject", "Oral Communication");
                sched3Values.put("Details", "Room 206");
                sched3Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched3Values);

                ContentValues sched4Values = new ContentValues();
                sched4Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30AM\n" + "Thursday\n" + "Lunch Break\n" + "");
                sched4Values.put("StudentNumber", number);
                sched4Values.put("FirstName", xtrafname);
                sched4Values.put("LastName", xtralname);
                sched4Values.put("Time", "1:30AM");
                sched4Values.put("Day", "Thursday");
                sched4Values.put("Subject", "Lunch Break");
                sched4Values.put("Details", "Room 206");
                sched4Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched4Values);


                ContentValues sched5Values = new ContentValues();
                sched5Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Thursday\n" + "Practical Research\n" + "Room 206\n" +
                        " ");
                sched5Values.put("StudentNumber", number);
                sched5Values.put("FirstName", xtrafname);
                sched5Values.put("LastName", xtralname);
                sched5Values.put("Time", "3:00PM");
                sched5Values.put("Day", "Thursday");
                sched5Values.put("Subject", "Practical Research");
                sched5Values.put("Details", "Room 206");
                sched5Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, sched5Values);


                ContentValues MonICTSched1Values = new ContentValues();
                MonICTSched1Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "9:00AM\n" + "Monday\n" + "Statistics and Probability\n" + "Room 206\n" +
                        " ");
                MonICTSched1Values.put("StudentNumber", number);
                MonICTSched1Values.put("FirstName", xtrafname);
                MonICTSched1Values.put("LastName", xtralname);
                MonICTSched1Values.put("Time", "9:00AM");
                MonICTSched1Values.put("Day", "Monday");
                MonICTSched1Values.put("Subject", "Statistics and Probability");
                MonICTSched1Values.put("Details", "Room 206");
                MonICTSched1Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, MonICTSched1Values);

                ContentValues MonICTSched2Values = new ContentValues();
                MonICTSched2Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "10:30AM\n" + "Monday\n" + "Earth and Life Science\n" + "Room 108\n");
                MonICTSched2Values.put("StudentNumber", number);
                MonICTSched2Values.put("FirstName", xtrafname);
                MonICTSched2Values.put("LastName", xtralname);
                MonICTSched2Values.put("Time", "10:30AM");
                MonICTSched2Values.put("Day", "Monday");
                MonICTSched2Values.put("Subject", "Earth and Life Science");
                MonICTSched2Values.put("Details", "Room 206");
                MonICTSched2Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, MonICTSched2Values);

                ContentValues MonICTSched3Values = new ContentValues();
                MonICTSched3Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Monday\n" + "Oral Communication\n" + "Room 206\n");
                MonICTSched3Values.put("StudentNumber", number);
                MonICTSched3Values.put("FirstName", xtrafname);
                MonICTSched3Values.put("LastName", xtralname);
                MonICTSched3Values.put("Time", "12:00PM");
                MonICTSched3Values.put("Day", "Monday");
                MonICTSched3Values.put("Subject", "Oral Communication");
                MonICTSched3Values.put("Details", "Room 206");
                MonICTSched3Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, MonICTSched3Values);

                ContentValues MonICTSched4Values = new ContentValues();
                MonICTSched4Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30AM\n" + "Monday\n" + "Lunch Break\n" + "");
                MonICTSched4Values.put("StudentNumber", number);
                MonICTSched4Values.put("FirstName", xtrafname);
                MonICTSched4Values.put("LastName", xtralname);
                MonICTSched4Values.put("Time", "1:30AM");
                MonICTSched4Values.put("Day", "Monday");
                MonICTSched4Values.put("Subject", "Lunch Break");
                MonICTSched4Values.put("Details", "Room 206");
                MonICTSched4Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, MonICTSched4Values);


                ContentValues MonICTSched5Values = new ContentValues();
                MonICTSched5Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Monday\n" + "Practical Research\n" + "Room 206\n" +
                        " ");
                MonICTSched5Values.put("StudentNumber", number);
                MonICTSched5Values.put("FirstName", xtrafname);
                MonICTSched5Values.put("LastName", xtralname);
                MonICTSched5Values.put("Time", "3:00PM");
                MonICTSched5Values.put("Day", "Monday");
                MonICTSched5Values.put("Subject", "Practical Research");
                MonICTSched5Values.put("Details", "Room 206");
                MonICTSched5Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, MonICTSched5Values);


                ContentValues MonICTSched7Values = new ContentValues();
                MonICTSched7Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30PM\n" + "Monday\n" + "DISMISSAL\n" + "");
                MonICTSched7Values.put("StudentNumber", number);
                MonICTSched7Values.put("FirstName", xtrafname);
                MonICTSched7Values.put("LastName", xtralname);
                MonICTSched7Values.put("Time", "4:30PM");
                MonICTSched7Values.put("Day", "Monday");
                MonICTSched7Values.put("Subject", "DISMISSAL");
                MonICTSched7Values.put("Details", "Room 206");
                MonICTSched7Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, MonICTSched7Values);
                ContentValues ABMschedTues13Values = new ContentValues();
                ABMschedTues13Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Tuesday\n" + "Business Math1\n" + "Room 208\n" +
                        " ");
                ABMschedTues13Values.put("StudentNumber", number);
                ABMschedTues13Values.put("FirstName", xtrafname);
                ABMschedTues13Values.put("LastName", xtralname);
                ABMschedTues13Values.put("Time", "12:00PM");
                ABMschedTues13Values.put("Day", "Tuesday");
                ABMschedTues13Values.put("Subject", "Business Math1");
                ABMschedTues13Values.put("Details", "Room 208");
                ABMschedTues13Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedTues13Values);

                ContentValues ABMschedTues14Values = new ContentValues();
                ABMschedTues14Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30PM\n" + "Tuesday\n" + "Business Math2\n" + "");
                ABMschedTues14Values.put("StudentNumber", number);
                ABMschedTues14Values.put("FirstName", xtrafname);
                ABMschedTues14Values.put("LastName", xtralname);
                ABMschedTues14Values.put("Time", "1:30PM");
                ABMschedTues14Values.put("Day", "Tuesday");
                ABMschedTues14Values.put("Subject", "Business Math2");
                ABMschedTues14Values.put("Details", "Room 208");
                ABMschedTues14Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedTues14Values);


                ContentValues ABMschedTues15Values = new ContentValues();
                ABMschedTues15Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Tuesday\n" + "General Math1\n" + "Room 208\n" +
                        " ");
                ABMschedTues15Values.put("StudentNumber", number);
                ABMschedTues15Values.put("FirstName", xtrafname);
                ABMschedTues15Values.put("LastName", xtralname);
                ABMschedTues15Values.put("Time", "3:00PM");
                ABMschedTues15Values.put("Day", "Tuesday");
                ABMschedTues15Values.put("Subject", "General Math1");
                ABMschedTues15Values.put("Details", "Room 208");
                ABMschedTues15Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedTues15Values);


                ContentValues ABMschedTues17Values = new ContentValues();
                ABMschedTues17Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30\n" + "Tuesday\n" + "DISMISSAL\n" + "Room 208\n" +
                        " ");
                ABMschedTues17Values.put("StudentNumber", number);
                ABMschedTues17Values.put("FirstName", xtrafname);
                ABMschedTues17Values.put("LastName", xtralname);
                ABMschedTues17Values.put("Time", "5:00PM");
                ABMschedTues17Values.put("Day", "Tuesday");
                ABMschedTues17Values.put("Subject", "General Math2");
                ABMschedTues17Values.put("Details", "Room 208");
                ABMschedTues17Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedTues17Values);

                ContentValues ABMschedFri13Values = new ContentValues();
                ABMschedFri13Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "12:00PM\n" + "Friday\n" + "Business Math1\n" + "Room 208\n" +
                        " ");
                ABMschedFri13Values.put("StudentNumber", number);
                ABMschedFri13Values.put("FirstName", xtrafname);
                ABMschedFri13Values.put("LastName", xtralname);
                ABMschedFri13Values.put("Time", "12:00PM");
                ABMschedFri13Values.put("Day", "Friday");
                ABMschedFri13Values.put("Subject", "Business Math1");
                ABMschedFri13Values.put("Details", "Room 208");
                ABMschedFri13Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedFri13Values);

                ContentValues ABMschedFri14Values = new ContentValues();
                ABMschedFri14Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "1:30PM\n" + "Friday\n" + "Business Math2\n" + "");
                ABMschedFri14Values.put("StudentNumber", number);
                ABMschedFri14Values.put("FirstName", xtrafname);
                ABMschedFri14Values.put("LastName", xtralname);
                ABMschedFri14Values.put("Time", "1:30PM");
                ABMschedFri14Values.put("Day", "Friday");
                ABMschedFri14Values.put("Subject", "Business Math2");
                ABMschedFri14Values.put("Details", "Room 208");
                ABMschedFri14Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedFri14Values);


                ContentValues ABMschedFri15Values = new ContentValues();
                ABMschedFri15Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "3:00PM\n" + "Friday\n" + "General Math1\n" + "Room 208\n" +
                        " ");
                ABMschedFri15Values.put("StudentNumber", number);
                ABMschedFri15Values.put("FirstName", xtrafname);
                ABMschedFri15Values.put("LastName", xtralname);
                ABMschedFri15Values.put("Time", "3:00PM");
                ABMschedFri15Values.put("Day", "Friday");
                ABMschedFri15Values.put("Subject", "General Math1");
                ABMschedFri15Values.put("Details", "Room 208");
                ABMschedFri15Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedFri15Values);


                ContentValues ABMschedFri17Values = new ContentValues();
                ABMschedFri17Values.put("Show", "" + xtrasnumber + "\n" + xtrafname + "\n" + xtralname + "\n" + "4:30\n" + "Friday\n" + "DISMISSAL\n" + "Room 208\n ");
                ABMschedFri17Values.put("StudentNumber", number);
                ABMschedFri17Values.put("FirstName", xtrafname);
                ABMschedFri17Values.put("LastName", xtralname);
                ABMschedFri17Values.put("Time", "5:00PM");
                ABMschedFri17Values.put("Day", "Friday");
                ABMschedFri17Values.put("Subject", "General Math2");
                ABMschedFri17Values.put("Details", "Room 208");
                ABMschedFri17Values.put("Course", xtracourse);
                db.insert("tblscheduser", null, ABMschedFri17Values);
                Toast.makeText(StudentAccount.this, "ABM Schedulle was successfully loaded into your account", Toast.LENGTH_SHORT).show();

            }}
        else {
            Toast.makeText(StudentAccount.this, "An Error Occured!", Toast.LENGTH_SHORT).show();
        }

    }

    public void viewsched(View view) {
        Intent getintent = getIntent();
        String snumber = getintent.getStringExtra("number");
        Intent intent = new Intent(StudentAccount.this, ScheduleUser.class);
        intent.putExtra("number", snumber);
        startActivity(intent);
    }

    //

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                _imageView.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //

    /*public void gohome(View v) {
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Go To Another Activity", Toast.LENGTH_SHORT).show();
    }
    */

    //


    public void updateprofile(View v) {

        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Profile", imageViewToByte(_imageView));
        long id = db.update("tbluser", contentValues,"StudentNumber =?", new String[]{number});


        Toast.makeText(getApplicationContext(), "Profile Updated!", Toast.LENGTH_SHORT).show();
    }
    public void updatepass(View v){
        Intent intent = new Intent(this, UpdatePassUser.class);
        intent.putExtra("number", number);
        startActivity(intent);
    }
    public void logout(View v){
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("number", number);
        startActivity(intent);
        finish();
    }

    public void viewcashier(View v) {
        Intent intent= new Intent (StudentAccount.this, ViewCashierInfo.class);
        startActivity(intent);
    }
    public void teacherfinder(View v) {
        Intent intent= new Intent (StudentAccount.this, TeacherFinder.class);
        startActivity(intent);
    }
    public void merchandise(View v) {
        Intent intent= new Intent (StudentAccount.this, Merchandise.class);
        intent.putExtra("number", number);
        startActivity(intent);
    }
    public void viewreserves(View v) {
        Intent intent= new Intent (StudentAccount.this, ViewMyReservation.class);
        intent.putExtra("number", number);
        startActivity(intent);
    }





    //
}
