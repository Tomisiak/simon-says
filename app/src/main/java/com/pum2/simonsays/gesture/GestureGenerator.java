package com.pum2.simonsays.gesture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Grego on 19.11.2016.
 */

public class GestureGenerator {

    private ArrayList<Type> typeList;
    private GestureFactory gestureFactory;
    private Random random;

    public GestureGenerator() {
        typeList = Type.getAll();
        gestureFactory = new GestureFactory();
        random = new Random();
    }

    public GestureList<Gesture> generateList(int size) {
        GestureList<Gesture> gestureList = new GestureList<>();
        for (int i = 0; i < size; i++) {
            Gesture gesture = this.generateGesture();
            gestureList.add(gesture);
        }

        return gestureList;
    }

    public Gesture generateGesture() {
        Type randomGestureType = typeList.get(random.nextInt(typeList.size()));

        return gestureFactory.getGestureForType(randomGestureType);
    }

}
