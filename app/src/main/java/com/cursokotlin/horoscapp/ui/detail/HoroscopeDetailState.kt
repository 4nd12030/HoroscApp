package com.cursokotlin.horoscapp.ui.detail

sealed class HoroscopeDetailState {
    ///Cuando es un estado sencillo que no necesita parametros su esa un data object
    data object Loading: HoroscopeDetailState()
    ///Cuando es un estado que necesita parametros su esa un data class
    data class Error(val error:String): HoroscopeDetailState()
    data class Success(val success:String): HoroscopeDetailState()
}