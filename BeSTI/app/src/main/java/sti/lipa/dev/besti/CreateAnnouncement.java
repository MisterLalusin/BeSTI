package sti.lipa.dev.besti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAnnouncement extends AppCompatActivity {

    private CheckBox _ahome;
    private CheckBox _astudent;
    private CheckBox _asection;
    EditText _message;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    TextView _txtguest,_txtuser,_txtteacher;
    Cursor cursor,cursor1,cursor2,cursor3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_announcement);
        _txtguest = (TextView)findViewById(R.id.txtguest);
        _txtuser = (TextView)findViewById(R.id.txtuser);
        _txtteacher = (TextView)findViewById(R.id.txtteacher);
        _ahome = (CheckBox)findViewById(R.id.ahome);
        _astudent = (CheckBox)findViewById(R.id.astudents);
        _asection = (CheckBox)findViewById(R.id.ateacher);
        _message = (EditText)findViewById(R.id.txtmessage);
        openHelper=new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String Guest = "Guest";
        String xtramessageguest = "";
        cursor1 = db.rawQuery("SELECT *FROM tblannouncement WHERE Level=?", new String[]{Guest});
        while (cursor1.moveToNext()) {
            xtramessageguest = "Guest: "+cursor1.getString(cursor1.getColumnIndex("Announcement"));
            _txtguest.setText(xtramessageguest);
        }
        String User = "User";
        String xtramessageuser = "";
        cursor2 = db.rawQuery("SELECT *FROM tblannouncement WHERE Level=?", new String[]{User});
        while (cursor2.moveToNext()) {
            xtramessageuser = "Students: "+cursor2.getString(cursor2.getColumnIndex("Announcement"));
            _txtuser.setText(xtramessageuser);
        }
        String Teacher = "Teacher";
        String xtramessageteacher = "";
        cursor3 = db.rawQuery("SELECT *FROM tblannouncement WHERE Level=?", new String[]{Teacher});
        while (cursor3.moveToNext()) {
            xtramessageteacher= "Teachers: "+cursor3.getString(cursor3.getColumnIndex("Announcement"));
            _txtteacher.setText(xtramessageteacher);
        }



    }
    public void PostAnnouncement(View v) {
        String Guest = "Guest";
        String User = "User";
        String Teacher = "Teacher";
        String message=_message.getText().toString();
        if (message.length()>60){
            Toast.makeText(getApplicationContext(), "Max characters is only 60 characters", Toast.LENGTH_SHORT).show();

        }
        else {
            if (_ahome.isChecked()) {
                ContentValues announcementGuestValues = new ContentValues();
                announcementGuestValues.put("Announcement", message);
                db.update("tblannouncement",announcementGuestValues,"Level=?", new String[]{Guest});
            }
            if (_astudent.isChecked()) {
                ContentValues announcementUserValues = new ContentValues();
                announcementUserValues.put("Announcement", message);
                db.update("tblannouncement",announcementUserValues,"Level=?", new String[]{User});
            }
            if (_asection.isChecked()) {
                ContentValues announcementTeacherValues = new ContentValues();
                announcementTeacherValues.put("Announcement", message);
                db.update("tblannouncement",announcementTeacherValues,"Level=?", new String[]{Teacher});
            }
            Toast.makeText(getApplicationContext(), "Announcement Posted!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAnnouncement.this, CreateAnnouncement.class);
            startActivity(intent);
        }
        }
}
