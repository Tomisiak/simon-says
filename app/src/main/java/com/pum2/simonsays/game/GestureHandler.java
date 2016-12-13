package com.pum2.simonsays.game;

import android.util.Log;


import com.pum2.simonsays.events.Event;
import com.pum2.simonsays.events.IEventHandler;
import com.pum2.simonsays.gesture.Gesture;


/**
 * Created by Grego on 19.11.2016.
 */

public class GestureHandler implements IEventHandler {

    private Game game;

    public GestureHandler(Game game)
    {
        this.game = game;
    }

    @Override
    public void callback(Event event) {
        Gesture gesture = event.getGesture();

        Log.d(Game.LOG_TAG, "Recognized gesture: " + gesture.getType().toString());
        // do action on Game

        Boolean result = game.resolveGesture(gesture);
        if (result)
        {
            game.getSpeaker().correctMove();
            if (game.isFinished())
            {
                game.reset();
                game.getSpeaker().endOfTheGame(true);
            }

            if (game.isNewLevel())
            {
                game.getSpeaker().gesturesToRepeat(game.getCurrentLevel(), game.getGesturesToRepeat());
            }
        }
        else
        {
            game.getSpeaker().endOfTheGame(false);
            game.reset();
            game.getSpeaker().gesturesToRepeat(game.getCurrentLevel(), game.getGesturesToRepeat());
        }
    }

}
