package com.jirmanrodriguez.misiontic.doglist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jirmanrodriguez.misiontic.doglist.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

/**
 *recibe la vista que vamos a pintar , creamos el metodo bind, este recibe un image, este se va
 * a llamar por cada celda que vamos a mostrar
 */
class DogViewHolder(view:View):RecyclerView.ViewHolder(view) {
  // añadimos el viewBinding
    private val binding = ItemDogBinding.bind(view)

  // va acargar las imagenes de internet con la libreria picasso
    fun bind(image:String){
     Picasso.get ().load(image).into(binding.ivDog)
    }
}