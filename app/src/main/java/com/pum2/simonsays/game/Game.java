package com.pum2.simonsays.game;


import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.pum2.simonsays.gesture.Gesture;
import com.pum2.simonsays.gesture.GestureList;
import com.pum2.simonsays.gesture.GestureListGenerator;


import java.util.List;

/**
 * Created by Grego on 19.11.2016.
 */
public class Game {
    private static Game ourInstance;
    private GestureList<Gesture> gestureList;
    private Integer currentLevel;
    private Integer gestureNo;
    private Speaker speaker;

    public static String LOG_TAG = "Game";

    private Game(Context context, GestureList<Gesture> gestureList) {
        this.gestureList = gestureList;
        currentLevel = 1;
        gestureNo = 0;
        speaker = new Speaker(context);
    }

    public void setTextToSpeech(TextToSpeech mTextToSpeech) {
        this.speaker.setTts(mTextToSpeech);
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public static Game getInstance(Context context, Integer levelSize) {
        if (ourInstance == null)
        {
            ourInstance = new Game(context, GestureListGenerator.generateList(levelSize));
        }

        return ourInstance;
    }

    public List<Gesture> getGesturesToRepeat()
    {
        return gestureList.subList(0, currentLevel);
    }


    public Boolean resolveGesture(Gesture gesture)
    {
        Boolean result = false;
        Gesture compareGesture = gestureList.get(gestureNo);
        if (gesture.getType().equals(compareGesture.getType()))
        {
            gestureNo++;
            result = true;
        }

        if (gestureNo.equals(currentLevel))
        {
            currentLevel++;
            gestureNo = 0;
        }

        return result;
    }

    public Boolean isNewLevel()
    {
        return gestureNo.equals(0);
    }

    public Boolean isFinished()
    {
        return currentLevel > gestureList.size();
    }

    public void reset()
    {
        gestureList = GestureListGenerator.generateList(gestureList.size());
        currentLevel = 1;
        gestureNo = 0;
        speaker.initialMessage(gestureList.size());
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }


}
