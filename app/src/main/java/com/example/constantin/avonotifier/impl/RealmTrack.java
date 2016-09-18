package com.example.constantin.avonotifier.impl;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmTrack extends RealmObject {
    @PrimaryKey
    private String dossierId;

    public RealmTrack() { }

    public String getDossierId() {
        return dossierId;
    }

    public void setDossierId(String dossierId) {
        this.dossierId = dossierId;
    }
}
