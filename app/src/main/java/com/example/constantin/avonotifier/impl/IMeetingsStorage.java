package com.example.constantin.avonotifier.impl;

import com.example.constantin.avonotifier.logic.Meeting;

import java.util.List;

public interface IMeetingsStorage {
    Meeting getById(String meetingId);
    List<Meeting> getByDossieId(String dossieId);
    void insert(Meeting meeting);
    void delete(Meeting meeting);
}
