package com.pum2.simonsays;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.pum2.simonsays.events.Event;
import com.pum2.simonsays.game.Game;
import com.pum2.simonsays.game.GestureHandler;
import com.pum2.simonsays.gesture.Gesture;
import com.pum2.simonsays.gesture.GestureList;
import com.pum2.simonsays.gesture.GestureListGenerator;
import com.pum2.simonsays.gesture.GestureListener;
import com.pum2.simonsays.game.GestureDispatcher;

import java.util.List;
import java.util.Locale;

/**
 * Created by Michal on 2016-11-11.
 */

public class GameActivity extends AppCompatActivity {
    private TextToSpeech mTextToSpeech;
    private GestureDetectorCompat mDetector;
    private GestureDispatcher gestureDispatcher;


    private static final String DEBUG_TAG = "Game";

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
                    toSpeak = getResources().getString(R.string.gesture_long_press_back);
                    mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        Game game = Game.getInstance(2);

        mDetector = new GestureDetectorCompat(GameActivity.this, new LocalGestureListener());
        mDetector.setIsLongpressEnabled(true);

        GestureHandler gestureHandler = new GestureHandler(game);
        gestureDispatcher             = GestureDispatcher.getInstance();
        gestureDispatcher.detectGestures(gestureHandler);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public class LocalGestureListener extends GestureListener {

        @Override
        public void onLongPress(MotionEvent event) {
            mTextToSpeech.stop();
            finish();
        }

        @Override
        public void dispatchEvent(Event event) {
            gestureDispatcher.dispatchEvent(event);
        }
    }
}
