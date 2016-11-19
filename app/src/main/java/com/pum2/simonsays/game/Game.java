package com.pum2.simonsays.game;

import android.support.annotation.Nullable;
import android.util.Log;

import com.pum2.simonsays.gesture.Gesture;
import com.pum2.simonsays.gesture.GestureList;
import com.pum2.simonsays.gesture.GestureListGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grego on 19.11.2016.
 */
public class Game {
    private static Game ourInstance;
    private GestureList<Gesture> gestureList;
    private Integer currentLevel;
    private Integer gestureNo;

    public static String LOG_TAG = "Game";

    public Game(GestureList<Gesture> gestureList) {
        this.gestureList = gestureList;
        currentLevel = 1;
        gestureNo = 0;
    }

    public static Game getInstance(Integer levelSize) {
        if (ourInstance == null)
        {
            ourInstance = new Game(GestureListGenerator.generateList(levelSize));
            Log.d(Game.LOG_TAG, "New game started. " + levelSize.toString() + " levels");
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
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }
}
