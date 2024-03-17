package com.avansdevops.user;

public class User {
    private final String name;
    public Role role;

    public User(String name) {
        this.name = name;
        
    }
    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
