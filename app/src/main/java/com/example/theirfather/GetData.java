package com.example.theirfather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {
    @GET("audio%2FGbenu sohun_Odunlade.mp3")
    Call<List<AudioModel>> getAllAudio();
}
