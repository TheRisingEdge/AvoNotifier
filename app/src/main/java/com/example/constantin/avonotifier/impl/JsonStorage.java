package com.example.constantin.avonotifier.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class MeetingPOJO {
    @SerializedName("id")
    public String id;
}

class DossierPOJO {
    @SerializedName("id")
    public String id;

    @SerializedName("meetings")
    public List<MeetingPOJO> meetings;

    public DossierPOJO() {
        meetings = new ArrayList<>();
    }
}

class StoragePOJO {
    @SerializedName("dossiers")
    public List<DossierPOJO> dossiers;

    public StoragePOJO() {
        this.dossiers = new ArrayList<>();
    }
}

public class JsonStorage {
    Gson gson;
    String name;
    File filesDir;

    public JsonStorage(File filesDir, String name) {
        this.name = name;
        this.filesDir = filesDir;
        this.gson = new GsonBuilder().create();
    }

    public StoragePOJO load() {
        try {
            File file = new File(filesDir, name);
            if (file != null && file.exists() && file.canRead()) {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis);
                StoragePOJO storage = gson.fromJson(isr, StoragePOJO.class);
                return storage != null ? storage: new StoragePOJO();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new StoragePOJO();
    }

    public void store(StoragePOJO pojo) {
        try {
            File file = new File(filesDir, name);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            gson.toJson(pojo, StoragePOJO.class, writer);
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
