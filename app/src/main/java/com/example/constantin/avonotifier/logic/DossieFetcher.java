package com.example.constantin.avonotifier.logic;

import java.util.HashSet;
import java.util.Set;

public class DossieFetcher {
    public class Result {
        public Track track;
        public boolean OK;
    }

    public interface Handler {
        void HandleTracked(Result result);
    }

    Set<Handler> subscribers;

    public DossieFetcher() {
        subscribers = new HashSet<>();
    }

    public void AddHandler(Handler handler) {
        subscribers.add(handler);
    }

    public void Track(Track data) {
        for (Handler h : subscribers) {
            Result result = new Result();
            result.track = data;
            result.OK = true;
            h.HandleTracked(result);
        }
    }
}
