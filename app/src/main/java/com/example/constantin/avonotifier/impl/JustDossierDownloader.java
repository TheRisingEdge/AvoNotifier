package com.example.constantin.avonotifier.impl;

import com.example.constantin.avonotifier.impl.just.AAKArrayOfDosar;
import com.example.constantin.avonotifier.impl.just.AAKArrayOfDosarSedinta;
import com.example.constantin.avonotifier.impl.just.AAKDosar;
import com.example.constantin.avonotifier.impl.just.AAKDosarSedinta;
import com.example.constantin.avonotifier.impl.just.AAKIServiceEvents;
import com.example.constantin.avonotifier.impl.just.AAKOperationResult;
import com.example.constantin.avonotifier.impl.just.AAKQuerySoap12;
import com.example.constantin.avonotifier.logic.Dossier;
import com.example.constantin.avonotifier.logic.DownloaderHandler;
import com.example.constantin.avonotifier.logic.IDossierDownloader;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.Time;
import com.example.constantin.avonotifier.logic.Track;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JustDossierDownloader implements IDossierDownloader {
    Set<DownloaderHandler> handlers;

    public JustDossierDownloader() {
        handlers = new HashSet<>();
    }

    @Override
    public void subscribe(DownloaderHandler handler) {
        handlers.add(handler);
    }

    @Override
    public void unsubscribe(DownloaderHandler handler) {
        handlers.remove(handler);
    }

    @Override
    public void download(final Track track) {
        String url = "http://portalquery.just.ro/query.asmx";
        AAKQuerySoap12 service = new AAKQuerySoap12(new AAKIServiceEvents() {
            @Override
            public void Starting() { }

            @Override
            public void Completed(AAKOperationResult result) {
                if (result.Exception == null) {
                    AAKArrayOfDosar dosare = (AAKArrayOfDosar) result.Result;
                    AAKDosar found = dosare.elementAt(0);
                    notifyDownloaded(track, Map(found));
                }
            }
        }, url);

        service.CautareDosareAsync(track.getDossieId(), null, null, null, null, null);
    }

    private void notifyDownloaded(Track track, Dossier dossie) {
        for (DownloaderHandler handler: handlers) {
            DownloaderHandler.DownloadResult res = new DownloaderHandler.DownloadResult();
            res.dossier = dossie;
            res.track = track;
            res.OK = true;

            handler.HandleDossierDownload(res);
        }
    }

    private Dossier Map(AAKDosar found) {
        String id = found.numar;
        Time registered = Time.FromDate(found.data);
        Time modified = Time.FromDate(found.dataModificare);
        String category = found.categorieCazNume;
        String departament = found.departament;
        String obiect = found.obiect;
        String stadiu = found.stadiuProcesualNume;

        List<Meeting> meetings = Map(found, found.sedinte);
        Dossier dossier = new Dossier(id, meetings, obiect, registered, modified, category, departament, stadiu);
        return dossier;
    }

    private List<Meeting> Map(AAKDosar dosar, AAKArrayOfDosarSedinta sedinte) {
        ArrayList<Meeting> bucket = new ArrayList<>(sedinte.size());
        for (AAKDosarSedinta sedinta: sedinte) {
            Time time = Time.FromDate(sedinta.data);
            String ora = sedinta.ora;
            String id = dosar.numar + "/" + time.year + "/" +time.month + "/" + time.day + "/" + ora;
            String complet = sedinta.complet;
            String solutie = sedinta.solutie;
            String sumar = sedinta.solutieSumar;

            Meeting meeting = new Meeting(id, dosar.numar, time, complet, solutie, sumar);

            bucket.add(meeting);
        }

        return bucket;
    }
}
