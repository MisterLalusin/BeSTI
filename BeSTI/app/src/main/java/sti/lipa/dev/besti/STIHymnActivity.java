package sti.lipa.dev.besti;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class STIHymnActivity extends AppCompatActivity {

    private MediaPlayer _STIhymn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stihymn);

        _STIhymn = MediaPlayer.create(getApplicationContext(),R.raw.stihymnaudio);
        _STIhymn.start();

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        _STIhymn.stop();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _STIhymn.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        _STIhymn.start();
    }
}
