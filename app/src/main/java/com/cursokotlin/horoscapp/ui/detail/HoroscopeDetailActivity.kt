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
import com.cursokotlin.horoscapp.databinding.ActivityHoroscopeDetailBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private lateinit var ivDetail: ImageView
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

    }

    fun createVariables() {
        ivDetail = binding.ivDetail
        tvTitle = binding.tvTitle
        tvBody = binding.tvBody
        progressBar = binding.progressBar
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState()
                    }
                }
            }
        }
        // ivDetail.setImageResource(args.type.)
        tvTitle.text = args.type.name
    }

    private fun successState() {

    }

    private fun errorState() {

    }

    private fun loadingState() {
        progressBar.isVisible = true
    }
}