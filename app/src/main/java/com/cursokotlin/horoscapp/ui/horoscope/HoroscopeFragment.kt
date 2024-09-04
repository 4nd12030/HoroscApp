package com.cursokotlin.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.horoscapp.databinding.FragmentHoroscopeBinding
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel
import com.cursokotlin.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Variable que conecta a la vista con el viewModel
    private val horoscopeViewModel by viewModels<HoroscopeViewModel> ()

    //Adapter
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvHoroscope: RecyclerView

    //Metodo que se inicia cuando la vista ya ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvHoroscope = binding.rvHoroscope

        initUI()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            //Toast.makeText(context, getString(it.name), Toast.LENGTH_SHORT).show()
            val type =when(it){
                HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                HoroscopeInfo.Gemini -> HoroscopeModel.Gemini
                HoroscopeInfo.Leo -> HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Pisces -> HoroscopeModel.Pisces
                HoroscopeInfo.Sagittarius -> HoroscopeModel.Sagittarius
                HoroscopeInfo.Scorpio -> HoroscopeModel.Scorpio
                HoroscopeInfo.Taurus -> HoroscopeModel.Taurus
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })

        //Codigo simplificado
        rvHoroscope.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = horoscopeAdapter
        }
        //Es lo mismo de arriba
//        rvHoroscope.layoutManager = LinearLayoutManager(context)
//        rvHoroscope.adapter = horoscopeAdapter
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
                    //Log.i("ViewModel", it.toString())
                    //Cambios en el listado de horosocpe
                    horoscopeAdapter.updateList(it)
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