package com.pum2.simonsays;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Michal on 2016-11-11.
 */

public class InstructionsActivity extends AppCompatActivity {
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(InstructionsActivity.this)));
                    String toSpeak = getResources().getString(R.string.instructions_title);
                    mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_instructions);
        layout.setOnTouchListener(new OnSwipeTouchListener(InstructionsActivity.this) {
            public void onSwipeRight() {
                mTextToSpeech.stop();
                finish();
            }
        });
    }
}
