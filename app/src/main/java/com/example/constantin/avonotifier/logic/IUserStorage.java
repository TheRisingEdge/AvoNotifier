package com.example.constantin.avonotifier.logic;

import java.util.List;

public interface IUserStorage {
    void addTrack(Track track);
    void addDossier(Dossie dossie);

    List<Track> getTracks();
    List<Dossie> getDossies();

    Dossie getDossie(String dossieId);
}
