package sti.lipa.dev.besti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    public void Teachers (View view){
        Intent kumamon =new Intent(Admin.this, Teacher.class);
        startActivity(kumamon);
    }
    public void ViewStudents (View view){
        Intent kumamon =new Intent(Admin.this, ViewStudents.class);
        startActivity(kumamon);
    }
    public void UpdatePasswordAdmin (View view){
        Intent kumamon2 =new Intent(Admin.this, UpdateAdminPassword.class);
        startActivity(kumamon2);
    }
    public void Announce (View view){
        Intent kumamon2 =new Intent(Admin.this, CreateAnnouncement.class);
        startActivity(kumamon2);
    }

}
