package com.pum2.simonsays.gesture;

import com.pum2.simonsays.R;

import java.util.ArrayList;

/**
 * Created by Grego on 19.11.2016.
 */

public enum Type {
    SWIPE_UP(R.string.gesture_swipe_up),
    SWIPE_DOWN(R.string.gesture_swipe_down),
    SWIPE_LEFT(R.string.gesture_swipe_left),
    SWIPE_RIGHT(R.string.gesture_swipe_right);

    private final int resourceId;

    private Type(final int resourceId)
    {
        this.resourceId = resourceId;
    }

    public int getResourceId() { return resourceId; }

    public static ArrayList<Type> getAll()
    {
        ArrayList<Type> list = new ArrayList<>();
        list.add(Type.SWIPE_UP);
        list.add(Type.SWIPE_DOWN);
        list.add(Type.SWIPE_LEFT);
        list.add(Type.SWIPE_RIGHT);

        return list;
    }
}
