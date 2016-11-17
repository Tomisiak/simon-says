package com.pum2.simonsays;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;

import java.util.Locale;

/**
 * Created by Michal on 2016-11-11.
 */

public class GameActivity extends AppCompatActivity {
    private TextToSpeech mTextToSpeech;
    private GestureDetectorCompat mDetector;

    private static final String DEBUG_TAG = "Gestures";

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

        mDetector = new GestureDetectorCompat(GameActivity.this, new MyGestureListener());
        mDetector.setIsLongpressEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }


        @Override
        public void onLongPress(MotionEvent event) {
            Log.e("", "onLongPress: X=" + String.valueOf(event.getX()) + ", Y=" + String.valueOf(event.getY()));

            mTextToSpeech.stop();
            finish();
        }


    }
}
