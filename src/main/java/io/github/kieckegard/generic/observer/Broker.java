/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.generic.observer;

/**
 *
 * @author Pedro Arthur
 */
public interface Broker {
    
    void subscribe(Object subscriber);
    void unsubscribe(Object subscriber);
    void publish(Object event);
    
}
