package com.cursokotlin.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(
    private var horoscopeList: List<HoroscopeInfo> = emptyList(),
    private val onItemSelected: (HoroscopeInfo) -> Unit
) : RecyclerView.Adapter<HoroscopeViewHolder>() {

    fun updateList(list: List<HoroscopeInfo>) {
        horoscopeList = list
        //Se agrega porque la lista solo se actualizara la primera vez
        notifyDataSetChanged()
    }

    //Este metodo indica en que vista va a mostrar los datos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    //Este metodo indica cuantos elementos hay en la lista de datos
    override fun getItemCount() = horoscopeList.size

    //Este metodo indica al viewHolder que datos va a pintar
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onItemSelected)

    }
}