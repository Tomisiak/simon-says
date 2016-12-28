package com.pum2.simonsays;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView mExitTextView;
    private TextView mInstructionsTextView;
    private TextView mSettingsTextView;
    private TextView mPlayTextView;
    private TextToSpeech mTextToSpeech;

    protected void initialMessage()
    {
        ArrayList<String> toSpeak = new ArrayList<String>();
        toSpeak.add(getResources().getString(R.string.main_hello_message));

        for (String text : toSpeak) {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.onCreate(this, "en");
        setContentView(R.layout.activity_main);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(MainActivity.this)));
                    initialMessage();
                }
            }
        });

        mExitTextView = (TextView) findViewById(R.id.main_exit);
        mInstructionsTextView = (TextView) findViewById(R.id.main_instructions);
        mSettingsTextView = (TextView) findViewById(R.id.main_settings);
        mPlayTextView = (TextView) findViewById(R.id.main_play);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeLeft() {
                Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
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
    public void startActivity(Intent intent) {
        mTextToSpeech.stop();
        super.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mTextToSpeech.stop();
        mTextToSpeech.shutdown();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(MainActivity.this)));
        mExitTextView.setText(R.string.main_exit);
        mInstructionsTextView.setText(R.string.main_instructions);
        mSettingsTextView.setText(R.string.main_settings);
        mPlayTextView.setText(R.string.main_play);
        initialMessage();
    }
}
