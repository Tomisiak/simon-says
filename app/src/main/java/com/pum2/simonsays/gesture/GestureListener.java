package com.pum2.simonsays.gesture;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.pum2.simonsays.events.Event;

/**
 * Created by Grego on 19.11.2016.
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final GestureFactory gestureFactory = new GestureFactory();

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
                result = true;
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();;
        }

        return result;
    }


    public void onSwipeLeft() {
        Gesture gesture = gestureFactory.getGestureForType(Type.SWIPE_LEFT);
        Event ev = new Event(gesture);
        dispatchEvent(ev);
    }

    public void onSwipeRight() {
        Gesture gesture = gestureFactory.getGestureForType(Type.SWIPE_RIGHT);
        Event ev = new Event(gesture);
        dispatchEvent(ev);
    }

    public void onSwipeBottom() {
        Gesture gesture = gestureFactory.getGestureForType(Type.SWIPE_DOWN);
        Event ev = new Event(gesture);
        dispatchEvent(ev);
    }

    public void onSwipeTop() {
        Gesture gesture = gestureFactory.getGestureForType(Type.SWIPE_UP);
        Event ev = new Event(gesture);
        dispatchEvent(ev);
    }

    public void dispatchEvent(Event event)
    {

    }

}
