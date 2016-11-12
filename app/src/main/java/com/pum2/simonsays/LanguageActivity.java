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
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Michal on 2016-11-11.
 */

public class LanguageActivity extends AppCompatActivity {
    TextToSpeech mTextToSpeech;
    TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        mTitleTextView = (TextView) findViewById(R.id.textView3);
        mTitleTextView.setText(R.string.language_activity_title);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(LanguageActivity.this)));
                    String toSpeak = getResources().getString(R.string.language_activity_title);
                    mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_language);
        layout.setOnTouchListener(new OnSwipeTouchListener(LanguageActivity.this) {
            public void onSwipeLeft() {
                Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(intent);
            }

            public void onSwipeTop() {
                if ("pl".equals(LocaleManager.getLanguage(LanguageActivity.this))) {
                    LocaleManager.setLocale(LanguageActivity.this, "en");
                } else {
                    LocaleManager.setLocale(LanguageActivity.this, "pl");
                }

                mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(LanguageActivity.this)));
                String toSpeak = getResources().getString(R.string.successful_language_change);
                mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                updateView();
            }
        });
    }

    private void updateView() {
        mTitleTextView.setText(R.string.language_activity_title);
    }
}
