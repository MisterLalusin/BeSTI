package sti.lipa.dev.besti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(400);
                    Intent i = new Intent(getApplicationContext(),GuestHome.class);
                    startActivity(i);
                }
                catch (Exception ex){}
            }
        };
        thread.start();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        finish();
        thread.interrupt();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(getApplicationContext(),GuestHome.class);
        startActivity(i);
        finish();
    }
}
