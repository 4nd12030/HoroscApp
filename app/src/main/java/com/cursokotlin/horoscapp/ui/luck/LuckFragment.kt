package com.cursokotlin.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.cursokotlin.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

//Clase que recibe clases inyectadas
@AndroidEntryPoint
class LuckFragment : Fragment() {

    private val luckViewModel by viewModels<LuckViewModel>()

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    private lateinit var ivRoulette: ImageView
    private lateinit var ivCard: ImageView
    private lateinit var tvLuck: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivRoulette = binding.ivRoulette
        initUI()
    }

    private fun initUI() {
        initListner()
    }

    private fun initListner() {
        ivRoulette.setOnClickListener { spinRoulett() }
    }

    private fun spinRoulett() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(ivRoulette, View.ROTATION ,0f, degrees.toFloat())

        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        Log.i("Fragment1", "$binding")
        return binding.root


    }

}