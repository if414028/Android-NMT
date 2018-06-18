package com.example.voldarex.neuralmachinetranslation.api;

import com.example.voldarex.neuralmachinetranslation.model.Sentence;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface APITranslation {

    @GET("/sentence/2")
    Call<Sentence> getSentence();

    @FormUrlEncoded
    @PUT("/sentence/2")
    Call<Sentence> putSentence(@Field("text") String text);

}
