package com.example.kennethlopez.eventbussample;


public class FetchUserEvent {
    private User mUser;

    public FetchUserEvent(User user) {
        this.mUser = user;
    }

    public User getUser() {
        return mUser;
    }
}
