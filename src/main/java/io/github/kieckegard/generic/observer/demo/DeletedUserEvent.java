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
public class DeletedUserEvent {
    
    private Long userId;

    public static class Builder {

        private Long userId;

        private Builder() {
        }

        public Builder userId(final Long value) {
            this.userId = value;
            return this;
        }

        public DeletedUserEvent build() {
            return new io.github.kieckegard.generic.observer.demo.DeletedUserEvent(userId);
        }
    }

    public static DeletedUserEvent.Builder builder() {
        return new DeletedUserEvent.Builder();
    }

    private DeletedUserEvent(final Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "DeletedUserEvent{" + "userId=" + userId + '}';
    }
}
