package com.example.voldarex.neuralmachinetranslation.model;

import com.google.gson.annotations.SerializedName;

public class Sentence {

    @SerializedName("id")
    int id;

    @SerializedName("text")
    String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
