package com.cursokotlin.horoscapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.navArgs
import com.cursokotlin.horoscapp.databinding.ActivityHoroscopeDetailBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val hosorocopeDetailViewModel : HoroscopeDetailViewModel by viewModels()

    //variable que recupera el argumento pasado desde HoroscopeFragment a traves del findNavControler
    private val args : HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        args.type


    }
}