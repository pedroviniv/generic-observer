/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.generic.observer.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class SubscriberInfo {
    
    private Object instance;
    private Method callback;
    
    public void onEvent(Object event) throws IllegalAccessException, 
            IllegalArgumentException, InvocationTargetException {
        this.callback.invoke(this.instance, event);
    }

    public static class Builder {

        private Object instance;
        private Method callback;

        private Builder() {
        }

        public Builder instance(final Object value) {
            this.instance = value;
            return this;
        }

        public Builder callback(final Method value) {
            this.callback = value;
            return this;
        }

        public SubscriberInfo build() {
            return new io.github.kieckegard.generic.observer.impl.SubscriberInfo(instance, callback);
        }
    }

    public static SubscriberInfo.Builder builder() {
        return new SubscriberInfo.Builder();
    }

    private SubscriberInfo(final Object instance, final Method callback) {
        this.instance = instance;
        this.callback = callback;
    }

    public Object getInstance() {
        return instance;
    }

    public Method getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "SubscriberInfo{" + "instance=" + instance + ", callback=" + callback + '}';
    }
}
