package com.jirmanrodriguez.misiontic.doglist

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * esta interface va a crear el metodo por el cual accedemos a nuestro API, a consumirlo.
 */
interface APIService {
 // colocamos el tipo de llamada, dame los perros por raza, la funcion a recibir por parametro.
 // : colocamos el nombre de la Dataclass
    @GET
   suspend fun getDogsByBreeds(@Url url: String):Response<DogsResponse>

}