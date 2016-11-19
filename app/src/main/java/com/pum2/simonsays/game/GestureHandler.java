package com.pum2.simonsays.game;

import android.util.Log;

import com.pum2.simonsays.events.Event;
import com.pum2.simonsays.events.IEventHandler;

/**
 * Created by Grego on 19.11.2016.
 */

public class GestureHandler implements IEventHandler {

    @Override
    public void callback(Event event) {
        Log.d("Gesture", event.getGesture().getType().toString());
    }
}
