package com.pum2.simonsays.gesture;

import java.util.ArrayList;

/**
 * Created by Grego on 19.11.2016.
 */

public enum Type {
    SWIPE_UP("swipe up"), SWIPE_DOWN("swipe down"), SWIPE_LEFT("swipe left"), SWIPE_RIGHT("swipe right");

    private final String value;

    private Type(final String value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

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
