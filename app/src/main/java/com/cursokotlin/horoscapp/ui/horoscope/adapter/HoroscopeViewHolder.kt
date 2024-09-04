package com.cursokotlin.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.horoscapp.databinding.ItemHoroscopeBinding
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //view es el paremetro que recibe del adapter, el item_horosocope
    private val binding = ItemHoroscopeBinding.bind(view)
    private val ivHoroscope = binding.ivHoroscope
    private val tvTittle = binding.tvTittle
    private val parent = binding.parent

    //Esta funcion indica como y en que elemetos de la vista se van
    //acomodar los datos
    fun render(horoscopeItem: HoroscopeInfo, onItemSelected:(HoroscopeInfo) -> Unit){
        val context = tvTittle.context
        tvTittle.text = context.getString(horoscopeItem.name)
        ivHoroscope.setImageResource(horoscopeItem.img)

        parent.setOnClickListener {
            startAnimation(ivHoroscope, newLambda = {onItemSelected(horoscopeItem)} )
        }

    }

    private fun startAnimation(view : View, newLambda:() -> Unit ) {
        view.animate().apply {
            duration = 500 //medio segundo
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction{ newLambda() }
            start()
        }
    }

}