package com.example.voldarex.neuralmachinetranslation.api;

import com.example.voldarex.neuralmachinetranslation.model.BahasaIndonesiaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIBahasaIndonesia {

    @GET("/api/kalimat/ind")
    Call<BahasaIndonesiaResponse> getListBahasaIndonesia();

}
