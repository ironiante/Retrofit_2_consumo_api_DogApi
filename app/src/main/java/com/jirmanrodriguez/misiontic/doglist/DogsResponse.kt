package com.jirmanrodriguez.misiontic.doglist

import com.google.gson.annotations.SerializedName

// al crear esta data class las variables tienen que ser con el mismo nombre del json, ya
// que mensage es una array y luego una lista de strings.
// se coloca el @serializedme el nombre que aparece en el json el otro es como se quiera llamar.tiene
// que ser igual a la llamada de la api
data class DogsResponse (
    @SerializedName (value = "status") var status:String,
    @SerializedName (value = "message")var images:List<String>)
