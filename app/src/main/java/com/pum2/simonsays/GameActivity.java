package com.pum2.simonsays;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.pum2.simonsays.events.Event;
import com.pum2.simonsays.game.Game;
import com.pum2.simonsays.game.GestureHandler;
import com.pum2.simonsays.gesture.GestureListener;
import com.pum2.simonsays.game.GestureDispatcher;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Michal on 2016-11-11.
 */

public class GameActivity extends AppCompatActivity {
    private TextToSpeech mTextToSpeech;
    private GestureDetectorCompat mDetector;
    private GestureDispatcher gestureDispatcher;

    private Game game;

    public static final String GAME_SETTING = "gameSettings";
    public static final String DIFFICULTY_LEVEL = "difficultyLevel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences settings = getSharedPreferences(GameActivity.GAME_SETTING, 0);
        Integer difficulty = settings.getInt(GameActivity.DIFFICULTY_LEVEL, 1);

        game = Game.getInstance(getApplicationContext(), difficulty);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(new Locale(LocaleManager.getLanguage(GameActivity.this)));
                    ArrayList<String> toSpeak = new ArrayList<String>();
                    toSpeak.add(getResources().getString(R.string.game_title));
                    toSpeak.add(getResources().getString(R.string.gesture_long_press_back));

                    for (String text : toSpeak) {
                        mTextToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
                    }
                    game.setTextToSpeech(mTextToSpeech);
                    game.reset();
                    game.getSpeaker().gesturesToRepeat(game.getCurrentLevel(), game.getGesturesToRepeat());
                }
            }
        });

        mDetector = new GestureDetectorCompat(GameActivity.this, new LocalGestureListener());
        mDetector.setIsLongpressEnabled(true);

        GestureHandler gestureHandler = new GestureHandler(game);
        gestureDispatcher = GestureDispatcher.getInstance();
        gestureDispatcher.detectGestures(gestureHandler);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech = null;
        }

        super.onDestroy();
    }

    public class LocalGestureListener extends GestureListener {

        @Override
        public void onLongPress(MotionEvent event) {
            mTextToSpeech.stop();
            String text = getResources().getString(R.string.game_exit);
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
            while(mTextToSpeech.isSpeaking())
            {
            }
            finish();
        }

        @Override
        public void dispatchEvent(Event event) {
            if (mTextToSpeech.isSpeaking() == false)
            {
                gestureDispatcher.dispatchEvent(event);
            }
        }
    }
}
