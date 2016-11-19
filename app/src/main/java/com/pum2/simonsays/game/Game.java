package com.pum2.simonsays.game;

import android.support.annotation.Nullable;

import com.pum2.simonsays.gesture.Gesture;
import com.pum2.simonsays.gesture.GestureList;
import com.pum2.simonsays.gesture.GestureListGenerator;

/**
 * Created by Grego on 19.11.2016.
 */
public class Game {
    private static Game ourInstance;
    private GestureList<Gesture> gestureList;
    private Integer currentLevel;

    public Game(GestureList<Gesture> gestureList) {
        this.gestureList = gestureList;
        currentLevel = 1;
    }

    public static Game getInstance(Integer levelSize) {
        if (ourInstance == null)
        {
            ourInstance = new Game(GestureListGenerator.generateList(levelSize));
        }

        return ourInstance;
    }
}
