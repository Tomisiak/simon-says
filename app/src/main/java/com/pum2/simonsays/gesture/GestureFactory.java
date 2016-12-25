package com.pum2.simonsays.gesture;

/**
 * Created by Grego on 19.11.2016.
 */

public class GestureFactory {

    public Gesture getGestureForType(Type type)
    {
        return new Gesture(type);
    }


}
