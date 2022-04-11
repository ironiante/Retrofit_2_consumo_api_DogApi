package com.jirmanrodriguez.misiontic.doglist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jirmanrodriguez.misiontic.doglist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.temporal.TemporalQuery

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
 /**
 * preparamos el viewBinding
 */
  private  lateinit var binding: ActivityMainBinding
  private lateinit var adapter: DogAdapter
  private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svdogs.setOnQueryTextListener(this)

        /**
         * creamos un metodo llamado initRecyclerview
          */
    initRecyclerview()
    }

    /**
     * vamos a inicializarlo y le tenemos que pasar el adapter pero este se tiene que crear arriba
     * como no se tiene las imagenes se va a crear una variable --> dogImages=Mutablelist, se le
     * pasa esa variable
     */
    private fun initRecyclerview() {
    adapter=DogAdapter(dogImages)
    binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter=adapter
        // le pasamos el adpter

    }

    /**
  * vamos a crear un metodo privado -->  getRetrofit y nos a devolver un retrofit
  * .baseUrl--->colocamos la parte fija de la direcion del json que no cambia.
  * .addCallAdapterFactory(aqui va la libreria de json converter)
   * . build --> construir
   * simplemente con llamar a este metodo que nos va a devolver retrofit podemos decirle que
   * nos de un listado por la raza que queramos.
   *
  */
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     *el usuario ha escrito y llega a l query,despues creamos la corutina
     * CoroutineScope(Dispatchers.IO).launch {
    }
     * creamos la variable call lo que se va hacer va a esta r en un hilo secundario.
     *
      */
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch{
            val call  =getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread{   // todo lo que ejecute despues queda en el hilo principal
                if(call.isSuccessful){
                    val images=puppies?.images ?: emptyList() //almacenamos las imagenes, esto es nulo vamos a tener una lista vacia
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                    //show recyclerview
                } else {
                     ShowError() //metodo
            }

            }
        }
    }

    private fun ShowError() { //por si no tiene internet
        Toast.makeText(this," ha ocurrido un error",Toast.LENGTH_SHORT).show()
    }
 // estos dos metodos nos van a avisar cuando el usuario escriba cada letra o borre, cada vez que pase un
    // cambio va llamarse este metodo
    override fun onQueryTextSubmit(query: String?): Boolean { // cuando termina de buscar y le das buscar
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
    return true
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}