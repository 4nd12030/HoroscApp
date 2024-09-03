package com.cursokotlin.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cursokotlin.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Variable que conecta a la vista con el viewModel
    private val horoscopeViewModel by viewModels<HoroscopeViewModel> ()

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    //Metodo que se inicia cuando la vista ya ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        //Corrutinas del tipo lifecycleScope su uso es recomendado en un fragment o activity
        // debido a que la accion que ejecute iniciara y morira
        //con el fragment o el activity donde se ejecute (ciclo de vida)
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeViewModel.horoscope.collect{
                    Log.i("ViewModel", it.toString())
                }

            }
        }

    }


    //Metodo que crea la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        Log.i("Fragment", "$binding")
        // Inflate the layout for this fragment
        return binding.root
    }

}