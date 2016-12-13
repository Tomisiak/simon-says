package com.pum2.simonsays.game;

import com.pum2.simonsays.events.EventDispatcher;
import com.pum2.simonsays.events.IEventHandler;
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

    public void detectGestures(IEventHandler handler) {
        addEventListener(Type.SWIPE_LEFT, handler);
        addEventListener(Type.SWIPE_RIGHT, handler);
        addEventListener(Type.SWIPE_UP, handler);
        addEventListener(Type.SWIPE_DOWN, handler);
    }
}
