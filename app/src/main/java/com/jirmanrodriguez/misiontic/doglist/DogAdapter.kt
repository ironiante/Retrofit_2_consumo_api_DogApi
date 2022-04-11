package com.jirmanrodriguez.misiontic.doglist

import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * que recibe nuestro adapter; va arecibir una lista de imagenes y se le pasa el viewholder
 */
class DogAdapter(private val images: List<String>):RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
       val layoutInflater =LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))

    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        // creamos una variable para las posiciones
        val item = images[position]
        // llamamos a nuestro holder y le pasamos el item
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
        // tabien se podria asi ; override fun getItemCount(): Int =images.size
    }


}