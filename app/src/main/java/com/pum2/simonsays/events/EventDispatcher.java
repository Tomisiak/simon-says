package com.pum2.simonsays.events;

import com.pum2.simonsays.gesture.Type;

import java.util.ArrayList;

/**
 * Created by Grego on 19.11.2016.
 */

abstract public class EventDispatcher implements IEventDispatcher {

    protected ArrayList<Listener> listenerList = new ArrayList<>();

    @Override
    public void addEventListener(Type type, IEventHandler handler) {
        Listener listener = new Listener(type, handler);
        removeEventListener(type);
        listenerList.add(0,listener);
    }

    @Override
    public void removeEventListener(Type type) {
        for (Listener listener : listenerList)
        {
            if (listener.getType().equals(type))
            {
                listenerList.remove(listener);
            }
        }
    }

    @Override
    public void dispatchEvent(Event event) {
        for (Listener listener : listenerList) {
            if (event.getGesture().getType().equals(listener.getType()))
            {
                IEventHandler eventHandler = listener.getHandler();
                eventHandler.callback(event);
            }
        }
    }

    @Override
    public boolean hasEventListener(Type type) {
        for (Listener listener : listenerList)
        {
            if (listener.getType().equals(type))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeAllListeners() {
        listenerList.clear();
    }
}
