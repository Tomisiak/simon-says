package com.pum2.simonsays.events;

import com.pum2.simonsays.gesture.Gesture;

/**
 * Created by Grego on 19.11.2016.
 */

public class Event {
    protected Gesture gesture;

    public Event(Gesture gesture)
    {
        this.gesture = gesture;
    }

    public Gesture getGesture() {
        return gesture;
    }
}
