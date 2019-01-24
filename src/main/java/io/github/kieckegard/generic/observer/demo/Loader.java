/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.generic.observer.demo;

import io.github.kieckegard.generic.observer.Broker;
import io.github.kieckegard.generic.observer.impl.BrokerImpl;

/**
 *
 * @author Pedro Arthur
 */
public class Loader {
    
    public static void main(String[] args) {
        
        Broker broker = new BrokerImpl();
        
        SampleListener sampleListener1 = new SampleListener();
        broker.subscribe(sampleListener1);
        
        CreatedUserEvent createdUser = CreatedUserEvent.builder()
                .payload(
                        User.builder()
                                .name("Pedro Arthur")
                                .email("pedro@gmail.com")
                                .build()
                ).build();
                
        
        broker.publish(createdUser);
    }
}
