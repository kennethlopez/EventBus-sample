package com.example.kennethlopez.eventbussample;


public class UserEvent {
    private User mUser;
    public UserEvent(User user) {
        mUser = user;
    }

    public User getUser() {
        return mUser;
    }
}
