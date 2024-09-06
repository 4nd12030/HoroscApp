package com.cursokotlin.horoscapp.data

import com.cursokotlin.horoscapp.data.network.HoroscopeApiService
import com.cursokotlin.horoscapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sign: String) {

        //Peticion retrofit
    }
}