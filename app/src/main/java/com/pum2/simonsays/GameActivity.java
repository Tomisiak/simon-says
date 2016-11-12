package com.pum2.simonsays;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Michal on 2016-11-11.
 */

public class GameActivity extends AppCompatActivity {
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(GameActivity.this)));
                    String toSpeak = getResources().getString(R.string.game_title);
                    mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_game);
        layout.setOnTouchListener(new OnSwipeTouchListener(GameActivity.this) {
            public void onSwipeTop() {
                mTextToSpeech.stop();
                finish();
            }
        });
    }
}
