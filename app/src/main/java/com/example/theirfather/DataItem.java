package com.example.theirfather;

public class DataItem {
    private String audioName;
    private String link;

    public DataItem (String audioName) {
        this.audioName = audioName;
    }

    public String getAudioName () {
        return this.audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
}
