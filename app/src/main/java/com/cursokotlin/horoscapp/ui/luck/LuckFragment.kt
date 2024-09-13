package com.cursokotlin.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.databinding.FragmentLuckBinding
import com.cursokotlin.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject
import javax.inject.Provider

//Clase que recibe clases inyectadas
@AndroidEntryPoint
class LuckFragment : Fragment() {

    private val luckViewModel by viewModels<LuckViewModel>()

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    private lateinit var preview: View
    private lateinit var prediction: View
    private lateinit var ivLuckyCard: ImageView
    private lateinit var ivRoulette: ImageView
    private lateinit var ivReverseCard: ImageView
    private lateinit var tvLucky: TextView
    private lateinit var tvShare: TextView

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initUI()
    }

    private fun initBinding() {
        ivRoulette = binding.ivRoulette
        ivReverseCard = binding.reverseCard
        preview = binding.preview
        prediction = binding.prediction
        ivLuckyCard = binding.ivLuckyCard
        tvLucky = binding.tvLucky
        tvShare = binding.tvShare
    }

    private fun initUI() {
        preparePrediction()
        initListner()
    }

    private fun preparePrediction() {
        val luck = randomCardProvider.getLucky()
        luck?.let { luck ->
            val currentPrediction = getString(luck.text)
            tvLucky.text = getString(luck.text)
            ivLuckyCard.setImageResource(luck.image)
            tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    private fun shareResult(prediction: String) {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initListner() {
        ivRoulette.setOnClickListener { spinRoulett() }
    }

    //Animaciones
    private fun spinRoulett() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(ivRoulette, View.ROTATION, 0f, degrees.toFloat())

        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                ivReverseCard.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        ivReverseCard.startAnimation(slideUpAnimation)

    }

    private fun growCard() {

        val growCardAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growCardAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                ivReverseCard.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        ivReverseCard.startAnimation(growCardAnimation)

    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                preview.isVisible = false
                prediction.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        preview.startAnimation(disappearAnimation)
        prediction.startAnimation(appearAnimation)
    }

    /////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        Log.i("Fragment1", "$binding")
        return binding.root


    }

}