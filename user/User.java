package com.bowen.library.user;

import com.bowen.library.command.IExecutable;

public abstract class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public abstract IExecutable[] getSupportedComands();

}
