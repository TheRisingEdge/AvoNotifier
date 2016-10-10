package com.example.constantin.avonotifier.logic;

import java.util.List;

public interface IUserStorage {
    void addTrack(Track track);
    void addDossier(Dossier dossie);

    List<Track> getTracks();
    List<Dossier> getDossiers();

    Dossier getDossier(String dossieId);
}
