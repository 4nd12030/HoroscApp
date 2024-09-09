package com.cursokotlin.horoscapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel.*

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private lateinit var ivDetail: ImageView
    private lateinit var ivBack: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView
    private lateinit var progressBar: ProgressBar


    //variable que recupera el argumento pasado desde HoroscopeFragment a traves del findNavControler
    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createVariables()
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)

    }

    fun createVariables() {
        ivDetail = binding.ivDetail
        tvTitle = binding.tvTitle
        tvBody = binding.tvBody
        progressBar = binding.progressBar
        ivBack = binding.ivBack
    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    private fun initListener() {
        ivBack.setOnClickListener {onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
        // ivDetail.setImageResource(args.type.)
        tvTitle.text = args.type.name
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        progressBar.isVisible = false

        tvTitle.text = state.sign
        tvBody.text = state.prediction

        val image = when(state.horoscopeModel){
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }

        ivDetail.setImageResource(image)

    }

    private fun errorState() {
        progressBar.isVisible = false

    }

    private fun loadingState() {
        progressBar.isVisible = true
    }
}