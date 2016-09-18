package com.example.constantin.avonotifier.logic;

public class Event {
    String title;
    String description;
    String location;

    public Event(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
