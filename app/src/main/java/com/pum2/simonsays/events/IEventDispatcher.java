package com.pum2.simonsays.events;

import com.pum2.simonsays.gesture.Type;

/**
 * Created by Grego on 19.11.2016.
 */

public interface IEventDispatcher {
    public void addEventListener(Type type, IEventHandler handler);
    public void removeEventListener(Type type);
    public void dispatchEvent(Event event);
    public boolean hasEventListener(Type type);
    public void removeAllListeners();
}
