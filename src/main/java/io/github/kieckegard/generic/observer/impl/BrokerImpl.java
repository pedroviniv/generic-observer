/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.generic.observer.impl;

import io.github.kieckegard.generic.observer.Broker;
import io.github.kieckegard.generic.observer.Listener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class BrokerImpl implements Broker {

    //observed Event Type and the Subscribers
    private Map<Class, List<SubscriberInfo>> subscribers = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(BrokerImpl.class.getName());

    @Override
    public void subscribe(Object subscriber) {

        Class<?> subscriberType = subscriber.getClass();

        Method[] subscriberMethods = subscriberType.getMethods();

        for (Method method : subscriberMethods) {

            if (!method.isAnnotationPresent(Listener.class)) {
                continue;
            }

            Class<?>[] listenerParameterTypes
                    = method.getParameterTypes();

            if (listenerParameterTypes.length != 1) {
                continue;
            }

            Class<?> subjectType = listenerParameterTypes[0];
            List<SubscriberInfo> alreadySubscribed = this.subscribers
                    .getOrDefault(subjectType, new ArrayList<>());

            if (alreadySubscribed.isEmpty()) {
                this.subscribers.put(subjectType, alreadySubscribed);
            }

            SubscriberInfo subscriberInfo = SubscriberInfo.builder()
                    .instance(subscriber)
                    .callback(method)
                    .build();

            alreadySubscribed.add(subscriberInfo);
        }
    }

    @Override
    public void unsubscribe(Object subscriber) {
        
        Collection<List<SubscriberInfo>> allSubscribers = this.subscribers.values();
        if(allSubscribers == null || allSubscribers.isEmpty())
            return;
        
        Iterator<List<SubscriberInfo>> subscribersIterator = allSubscribers.iterator();
        while(subscribersIterator.hasNext()) {
            
            List<SubscriberInfo> currentTypeSubscribers = subscribersIterator.next();
            Iterator<SubscriberInfo> currentTypeSubscribersIterator 
                    = currentTypeSubscribers.iterator();
            
            while(currentTypeSubscribersIterator.hasNext()) {
            
                SubscriberInfo currentTypeSubscriber 
                        = currentTypeSubscribersIterator.next();
                
                if(currentTypeSubscriber.getInstance().equals(subscriber)) {
                    currentTypeSubscribersIterator.remove();
                }
            }
        }
    }

    @Override
    public void publish(Object event) {

        Class<?> subjectType = event.getClass();
        List<SubscriberInfo> eventSubscribers = this.subscribers.get(subjectType);
        if (eventSubscribers != null) {
            eventSubscribers.forEach(s -> {
                try {
                    s.onEvent(event);
                } catch (IllegalAccessException
                        | IllegalArgumentException
                        | InvocationTargetException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            });
        }
    }

}
