package com.example.metaldude.sportflex.api;

import com.example.metaldude.sportflex.entities.Hero;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}