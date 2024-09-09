package com.cursokotlin.horoscapp.data

import android.util.Log
import com.cursokotlin.horoscapp.data.network.HoroscopeApiService
import com.cursokotlin.horoscapp.data.network.response.PredictionResponse
import com.cursokotlin.horoscapp.domain.Repository
import com.cursokotlin.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: HoroscopeApiService
) : Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        //Hace la llamada a internet
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("repository", "Ha ocurrido un error ${it.message}") }
        return null
    }
}
