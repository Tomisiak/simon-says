package com.pum2.simonsays.game;

import android.util.Log;

import com.pum2.simonsays.events.Event;
import com.pum2.simonsays.events.IEventHandler;
import com.pum2.simonsays.gesture.Gesture;

import java.util.List;

/**
 * Created by Grego on 19.11.2016.
 */

public class GestureHandler implements IEventHandler {

    private Game game;

    public GestureHandler(Game game)
    {
        this.game = game;
        this.gesturesToRepeat();
    }

    @Override
    public void callback(Event event) {
        Gesture gesture = event.getGesture();

        Log.d(Game.LOG_TAG, "Recognized gesture: " + gesture.getType().toString());
        // do action on Game

        Boolean result = game.resolveGesture(gesture);
        if (result)
        {
            Log.d(Game.LOG_TAG, "Correct!");
            if (game.isFinished())
            {
                game.reset();
                Log.d(Game.LOG_TAG, "You WON!");
            }

            if (game.isNewLevel())
            {
                this.gesturesToRepeat();
            }
        }
        else if (!result)
        {
            game.reset();
            Log.d(Game.LOG_TAG, "You failed");
            this.gesturesToRepeat();
        }
    }

    public void gesturesToRepeat()
    {
        Log.d(Game.LOG_TAG, "Level: " + game.getCurrentLevel());
        List<Gesture> listToRepeat = game.getGesturesToRepeat();

        for(Gesture toRepeat : listToRepeat)
        {
            Log.d(Game.LOG_TAG, "Repeat: " + toRepeat.getType().toString());
        }
    }
}
