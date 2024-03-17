package com.avansdevops.user;

public class User {
    private final String name;
    private Role role;

    public User(String name) {
        this(name, Role.UNKNOWN);
    }
    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
