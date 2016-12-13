package com.pum2.simonsays.events;

import com.pum2.simonsays.gesture.Type;

/**
 * Created by Grego on 19.11.2016.
 */

public class Listener {
    private Type type;
    private IEventHandler handler;

    public Listener(Type type, IEventHandler handler) {
        this.type = type;
        this.handler = handler;
    }

    public Type getType() {
        return type;
    }

    public IEventHandler getHandler() {
        return handler;
    }
}
