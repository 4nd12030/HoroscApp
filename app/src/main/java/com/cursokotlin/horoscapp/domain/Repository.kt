package com.cursokotlin.horoscapp.domain

interface Repository {
    suspend fun getPrediction(sign: String)
}