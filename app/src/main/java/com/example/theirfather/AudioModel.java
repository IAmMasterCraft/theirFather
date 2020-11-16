package com.example.theirfather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AudioModel {
    @SerializedName("name")
    private String name;

    public AudioModel (String name) {
        this.name = name;
    }
    public String getName () {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}