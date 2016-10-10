package com.example.constantin.avonotifier.logic;

public interface IDossierDownloader {
    void subscribe(DownloaderHandler handler);
    void unsubscribe(DownloaderHandler handler);
    void download(Track track);
}
