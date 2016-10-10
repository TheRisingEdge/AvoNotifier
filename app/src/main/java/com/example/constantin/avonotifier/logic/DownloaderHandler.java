package com.example.constantin.avonotifier.logic;

public interface DownloaderHandler {
    class DownloadResult {
        public Track track;
        public Dossier dossier;
        public boolean OK;
    }
    void HandleDossierDownload(DownloadResult result);
}
