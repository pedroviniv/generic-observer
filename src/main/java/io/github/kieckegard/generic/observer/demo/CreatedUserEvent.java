/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.generic.observer.demo;

/**
 *
 * @author pedro
 */
public class CreatedUserEvent {
    
    private User payload;

    public static class Builder {

        private User payload;

        private Builder() {
        }

        public Builder payload(final User value) {
            this.payload = value;
            return this;
        }

        public CreatedUserEvent build() {
            return new io.github.kieckegard.generic.observer.demo.CreatedUserEvent(payload);
        }
    }

    public static CreatedUserEvent.Builder builder() {
        return new CreatedUserEvent.Builder();
    }

    private CreatedUserEvent(final User payload) {
        this.payload = payload;
    }

    public User getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "CreatedUserEvent{" + "payload=" + payload + '}';
    }
}
