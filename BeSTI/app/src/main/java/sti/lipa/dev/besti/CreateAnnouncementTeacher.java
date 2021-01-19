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

public class CreateAnnouncementTeacher extends AppCompatActivity {

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
        setContentView(R.layout.activity_create_announcement_teacher);
        _txtuser = (TextView)findViewById(R.id.txtuser);
        _message = (EditText)findViewById(R.id.txtmessage);
        openHelper=new DatabaseHelper(this);
        String User = "User";
        String xtramessageuser ="";
        db = openHelper.getWritableDatabase();
        cursor2 = db.rawQuery("SELECT *FROM tblannouncement WHERE Level=?", new String[]{User});
        while (cursor2.moveToNext()) {
            xtramessageuser = "Students: "+cursor2.getString(cursor2.getColumnIndex("Announcement"));
            _txtuser.setText(xtramessageuser);
        }



    }
    public void PostAnnouncement(View v) {
        String User = "User";
        String message=_message.getText().toString();
        if (message.length()>60){
            Toast.makeText(getApplicationContext(), "Max characters is only 60 characters", Toast.LENGTH_SHORT).show();

        }
        else {
            db = openHelper.getWritableDatabase();
            ContentValues announcementUserValues = new ContentValues();
            announcementUserValues.put("Announcement", message);
            db.update("tblannouncement",announcementUserValues,"Level=?", new String[]{User});
            Toast.makeText(getApplicationContext(), "Announcement Posted!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAnnouncementTeacher.this, CreateAnnouncementTeacher.class);
            startActivity(intent);
        }
    }
}
