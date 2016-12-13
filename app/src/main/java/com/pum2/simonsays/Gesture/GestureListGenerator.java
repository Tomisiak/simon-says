package com.pum2.simonsays.gesture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Grego on 19.11.2016.
 */

public class GestureListGenerator {

    public static GestureList<Gesture> generateList(int size)
    {
        ArrayList<Type> typeList = Type.getAll();
        GestureFactory gestureFactory = new GestureFactory();
        GestureList<Gesture> gestureList = new GestureList<>();

        for (int i = 0; i < size; i++) {
            Type randomGestureType = typeList.get(new Random().nextInt(typeList.size()));
            Gesture gesture = gestureFactory.getGestureForType(randomGestureType);
            gestureList.add(gesture);
        }

        return gestureList;
    }

}
