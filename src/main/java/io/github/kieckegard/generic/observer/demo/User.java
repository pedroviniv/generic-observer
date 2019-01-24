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
public class User {
    
    private String name;
    private String email;

    public static class Builder {

        private String name;
        private String email;

        private Builder() {
        }

        public Builder name(final String value) {
            this.name = value;
            return this;
        }

        public Builder email(final String value) {
            this.email = value;
            return this;
        }

        public User build() {
            return new io.github.kieckegard.generic.observer.demo.User(name, email);
        }
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

    private User(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + '}';
    }
}
