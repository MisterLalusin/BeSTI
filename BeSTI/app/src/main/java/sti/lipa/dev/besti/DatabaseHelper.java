package sti.lipa.dev.besti;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by CompLab502-13PC on 2/27/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG ="DB Helper";
    private static final String DATABASE_NAME="register.db";
    private static final String TABLE_NAME="tbluser";
    private static final String TABLE_NAME1="tblteacher";

    public static final String COL_1="ID";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Password";
    public static final String COL_5="Email";
    public static final String COL_6="Phone";
    public static final String COL_7="ID";
    public static final String COL_8="Show";
    public static final String COL_9="ID";
    public static final String COL_10="Show";
    public static final String Guest="Guest";
    public static final String User="User";
    public static final String Teacher="Teacher";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbluser(ID INTEGER PRIMARY KEY AUTOINCREMENT,Show TEXT,StudentNumber TEXT, FirstName TEXT, LastName TEXT,Email TEXT, Password TEXT, Phone TEXT,Course TEXT, Profile BLOB)");
        db.execSQL("CREATE TABLE tblteacher(ID INTEGER PRIMARY KEY AUTOINCREMENT,Show TEXT,TeacherNumber TEXT, FirstName TEXT, LastName TEXT,Email TEXT, Password TEXT, Phone TEXT,Course TEXT)");
        db.execSQL("CREATE TABLE tbladmin(ID INTEGER PRIMARY KEY AUTOINCREMENT, IDNUMBER TEXT,FirstName TEXT, LastName TEXT, Password TEXT,Email TEXT, Phone TEXT)");
        db.execSQL("CREATE TABLE tblscheduser(ID INTEGER PRIMARY KEY AUTOINCREMENT, Show Text,StudentNumber TEXT, FirstName TEXT, LastName TEXT,Time TEXT, Day TEXT, Subject TEXT,Details TEXT,Course TEXT)");
        db.execSQL("CREATE TABLE tblschedteacher(ID INTEGER PRIMARY KEY AUTOINCREMENT, Show Text,TeacherNumber TEXT, FirstName TEXT, LastName TEXT,Time TEXT, Day TEXT, Subject TEXT,Details TEXT,Course TEXT)");
        db.execSQL("CREATE TABLE tblcashier(ID INTEGER PRIMARY KEY AUTOINCREMENT,IDNUMBER TEXT,Email TEXT, FirstName TEXT, LastName TEXT, Password TEXT, Phone TEXT, Message TEXT)");
        db.execSQL("CREATE TABLE tblannouncement(ID INTEGER PRIMARY KEY AUTOINCREMENT,Level TEXT, Announcement TEXT)");
        db.execSQL("CREATE TABLE tbltutorfinder(ID INTEGER PRIMARY KEY AUTOINCREMENT,StudentNo TEXT, TeacherNo TEXT, RequestDate TEXT, Response TEXT, Reason TEXT)");//STF
        db.execSQL("CREATE TABLE tblimagebanner(ID INTEGER PRIMARY KEY AUTOINCREMENT, Banner BLOB, SetAs TEXT)");
        db.execSQL("CREATE TABLE tblmerchandise (ITM_ID INTEGER PRIMARY KEY AUTOINCREMENT,Show TEXT,Name TEXT, QTY INTEGER,Size TEXT, DETAILS TEXT, VARIATION TEXT,PRICE TEXT)");
        db.execSQL("CREATE TABLE tblreserve (ITM_ID INTEGER PRIMARY KEY AUTOINCREMENT,Show TEXT,IDNumber TEXT,Name TEXT,Size TEXT, DETAILS TEXT, VARIATION TEXT,PRICE TEXT,STATUS TEXT)");
        ContentValues adminValues = new ContentValues();
        adminValues.put("IDNUMBER", "1");
        adminValues.put("FirstName", "STI COLLEGE");
        adminValues.put("LastName", "ADMINISTRATOR");
        adminValues.put("Password", "a");
        adminValues.put("Email", "sti@admin.com");
        adminValues.put("Phone", "+96969696969");
        db.insert("tbladmin", null, adminValues);

        ContentValues cashierValues = new ContentValues();
        cashierValues.put("IDNUMBER", "2");
        cashierValues.put("FirstName", "STI COLLEGE");
        cashierValues.put("LastName", "CASHIER");
        cashierValues.put("Password", "c");
        cashierValues.put("Email", "cashier@sti.edu");
        cashierValues.put("Phone", "+96969696969");
        cashierValues.put("Message", "Cashier Available");
        db.insert("tblcashier", null, cashierValues);

        ContentValues schedValues = new ContentValues();
        schedValues.put("Show", "2000069693\nSTILIPA\nDEVELOPER\n9:00\nScience\nRoom 102");
        schedValues.put("StudentNumber", "2000069693");
        schedValues.put("FirstName", "STILIPA");
        schedValues.put("LastName", "DEVELOPER");
        schedValues.put("Time", "9:00");
        schedValues.put("Day", "Wednesday");
        schedValues.put("Subject", "Science");
        schedValues.put("Details", "Room 102");
        schedValues.put("Course", "ICT");
        db.insert("tblscheduser", null, schedValues);



        ContentValues createTeach = new ContentValues();
        createTeach.put("Show", "3\nSTILIPA\nDEVELOPER\n9:00\nScience\nRoom 102");
        createTeach.put("TeacherNumber", "3");
        createTeach.put("FirstName", "STILIPA");
        createTeach.put("LastName", "DEVELOPER");
        createTeach.put("Email", "teacher@sti.edu");
        createTeach.put("Password", "c");
        createTeach.put("Phone", "0969696969");
        createTeach.put("Course", "ICT");
        db.insert("tblteacher", null, createTeach);










        ContentValues levelhomeValues = new ContentValues();
        levelhomeValues.put("Level", "Guest");
        levelhomeValues.put("Announcement", "");
        db.insert("tblannouncement", null, levelhomeValues);


        ContentValues leveluserValues = new ContentValues();
        leveluserValues.put("Level", "User");
        leveluserValues.put("Announcement", "");
        db.insert("tblannouncement", null, leveluserValues);


        ContentValues levelteacherValues = new ContentValues();
        levelteacherValues.put("Level", "Teacher");
        levelteacherValues.put("Announcement", "");
        db.insert("tblannouncement", null, levelteacherValues);


        ContentValues announcementValues = new ContentValues();
        announcementValues.put("Announcement", "Aim High with STI!");
        db.update("tblannouncement",announcementValues,"Level=?", new String[]{Guest});

        ContentValues announcementuserValues = new ContentValues();
        announcementuserValues.put("Announcement", "Aim High with STI!");
        db.update("tblannouncement",announcementuserValues,"Level=?", new String[]{User});
        ContentValues announcementteachValues = new ContentValues();
        announcementteachValues.put("Announcement", "Aim High with STI!");
        db.update("tblannouncement",announcementteachValues,"Level=?", new String[]{Teacher});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblteacher");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tbladmin");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblreserve");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblcashier");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblannouncement");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblscheduser");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tbltutorfinder");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblimagebanner");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblschedteacher");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tblmerchandise");
        onCreate(db);
    }

    //rovic mas poge ke emman

    public Cursor getDataTeachers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM tblteacher";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getDataSchedUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM tblscheduser";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getDataStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM tbluser";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDataReservation(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM tblreserve";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getItemIDteacher(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID FROM tblteacher WHERE Show="+name+"";
        Cursor datateacher = db.rawQuery(query, null);
        return datateacher;
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *FROM tblreserve";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    //rovic mas poge ke emman
    //POGI SI EMMAN


}





















