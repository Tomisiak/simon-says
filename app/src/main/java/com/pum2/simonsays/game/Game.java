package com.pum2.simonsays.game;


import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.pum2.simonsays.gesture.Gesture;
import com.pum2.simonsays.gesture.GestureList;
import com.pum2.simonsays.gesture.GestureGenerator;


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
    private GestureGenerator gestureGenerator;
    private Integer difficulty;

    public static String LOG_TAG = "Game";

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    private Game(Context context, GestureGenerator gestureGenerator) {
        gestureList = new GestureList<>();
        this.gestureGenerator = gestureGenerator;
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

    public static Game getInstance(Context context, Integer difficulty) {
        if (ourInstance == null)
        {
            ourInstance = new Game(context, new GestureGenerator());
        }

        ourInstance.setDifficulty(difficulty);

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
            randNewGesture();
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
        Integer listSize = difficulty == 1 ? difficulty : difficulty * 3;
        gestureList = new GestureList<>();
        currentLevel = listSize;
        gestureNo = 0;
        speaker.initialMessage();
        gestureList = gestureGenerator.generateList(listSize);
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    private void randNewGesture()
    {
        gestureList.add(gestureGenerator.generateGesture());
    }
}
