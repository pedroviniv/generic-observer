/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.generic.observer.demo;

import io.github.kieckegard.generic.observer.Listener;

/**
 *
 * @author pedro
 */
public class SampleListener {
    
    @Listener
    public void onCreatedUser(CreatedUserEvent createdUser) {
        System.out.println("[SampleListener] createdUser: " + createdUser.getPayload());
    }
    
    @Listener
    public void onDouble(DeletedUserEvent deletedUser) {
        System.out.println("[SampleListener] deleted user: " + deletedUser.getUserId());
    }
}
