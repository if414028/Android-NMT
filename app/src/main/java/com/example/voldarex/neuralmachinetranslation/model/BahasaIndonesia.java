package com.example.voldarex.neuralmachinetranslation.model;

import com.google.gson.annotations.SerializedName;

public class BahasaIndonesia {

    @SerializedName("id")
    private int id;

    @SerializedName("kalimat")
    private String kalimat;

    public BahasaIndonesia(int id, String kalimat) {
        this.id = id;
        this.kalimat = kalimat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKalimat() {
        return kalimat;
    }

    public void setKalimat(String kalimat) {
        this.kalimat = kalimat;
    }
}
