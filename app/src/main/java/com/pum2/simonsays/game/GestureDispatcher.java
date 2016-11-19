package com.pum2.simonsays.game;

import com.pum2.simonsays.events.EventDispatcher;
import com.pum2.simonsays.gesture.Type;

/**
 * Created by Grego on 19.11.2016.
 */
public class GestureDispatcher extends EventDispatcher {
    private static GestureDispatcher ourInstance = new GestureDispatcher();

    public static GestureDispatcher getInstance() {
        return ourInstance;
    }

    private GestureDispatcher() {
    }

    public void detectGestures() {
        addEventListener(Type.SWIPE_LEFT, new GestureHandler());
        addEventListener(Type.SWIPE_RIGHT, new GestureHandler());
        addEventListener(Type.SWIPE_UP, new GestureHandler());
        addEventListener(Type.SWIPE_DOWN, new GestureHandler());
    }
}
