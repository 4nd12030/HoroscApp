package com.cursokotlin.horoscapp.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    @GET("{sign}")
    suspend fun getHoroscope(@Path("sign") sign:String)
}