package com.pum2.simonsays;

import android.content.SharedPreferences;
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

public class SettingsActivity extends AppCompatActivity {
    TextToSpeech mTextToSpeech;
    TextView mTitleTextView;
    TextView mChangeTextView;
    TextView mBackTextView;
    TextView mGameDifficultyTextView;

    public static String LOG_TAG = "OPTIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mTitleTextView = (TextView) findViewById(R.id.language_title);
        mChangeTextView = (TextView) findViewById(R.id.language_change);
        mBackTextView = (TextView) findViewById(R.id.language_back);
        mGameDifficultyTextView = (TextView) findViewById(R.id.change_difficulty);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(SettingsActivity.this)));
                    String toSpeak = getResources().getString(R.string.language_title);
                    mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_settings);
        layout.setOnTouchListener(new OnSwipeTouchListener(SettingsActivity.this) {
            public void onSwipeLeft() {
                mTextToSpeech.stop();
                finish();
            }

            public void onSwipeTop() {
                if ("pl".equals(LocaleManager.getLanguage(SettingsActivity.this))) {
                    LocaleManager.setLocale(SettingsActivity.this, "en");
                } else {
                    LocaleManager.setLocale(SettingsActivity.this, "pl");
                }

                mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(SettingsActivity.this)));
                String toSpeak = getResources().getString(R.string.language_successful_change);
                mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                updateView();
            }

            public void onSwipeBottom() {
                SharedPreferences settings = getSharedPreferences(GameActivity.GAME_SETTING, 0);
                Integer curLevel = settings.getInt(GameActivity.DIFFICULTY_LEVEL, 1);
                SharedPreferences.Editor editor = settings.edit();
                Integer newLevel = (curLevel < 4) ? curLevel + 1 : 1;
                editor.putInt(GameActivity.DIFFICULTY_LEVEL, newLevel);
                editor.commit();

                Log.d(SettingsActivity.LOG_TAG, "Game difficulty changed to: " + newLevel);
                String lvlText = getResources().getString(R.string.game_difficulty_begginer);
                switch (newLevel) {
                    case 1:
                        lvlText = getResources().getString(R.string.game_difficulty_begginer);
                        break;
                    case 2:
                        lvlText = getResources().getString(R.string.game_difficulty_easy);
                        break;
                    case 3:
                        lvlText = getResources().getString(R.string.game_difficulty_medium);
                        break;
                    case 4:
                        lvlText = getResources().getString(R.string.game_difficulty_high);
                        break;
                }
                String toSpeak = getResources().getString(R.string.game_difficulty) + " " + lvlText;
                mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void updateView() {
        mTitleTextView.setText(R.string.language_title);
        mChangeTextView.setText(R.string.language_change);
        mBackTextView.setText(R.string.app_back);
        mGameDifficultyTextView.setText(R.string.change_difficulty);
    }
}
