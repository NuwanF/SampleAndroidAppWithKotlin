package com.example.pupshop

import retrofit2.Call
import retrofit2.http.GET

interface PupService {
    @GET("dogsAPI.json")
    fun getPup() : Call<Pup>
}
