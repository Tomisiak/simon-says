package com.pum2.simonsays;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView mExitTextView;
    private TextView mInstructionsTextView;
    private TextView mLanguageTextView;
    private TextView mPlayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.onCreate(this, "en");
        setContentView(R.layout.activity_main);

        mExitTextView = (TextView) findViewById(R.id.main_exit);
        mInstructionsTextView = (TextView) findViewById(R.id.main_instructions);
        mLanguageTextView = (TextView) findViewById(R.id.main_language);
        mPlayTextView = (TextView) findViewById(R.id.main_play);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeLeft() {
                Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intent);
            }

            public void onSwipeBottom() {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }

            public void onSwipeTop() {
                MainActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mExitTextView.setText(R.string.main_exit);
        mInstructionsTextView.setText(R.string.main_instructions);
        mLanguageTextView.setText(R.string.main_language);
        mPlayTextView.setText(R.string.main_play);
    }
}
