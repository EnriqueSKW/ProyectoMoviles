package com.example.meetandfix.fragments

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.meetandfix.ConexionesURL
import com.example.meetandfix.R
import com.example.meetandfix.ReviewAdapter.ReviewAdapter
import com.example.meetandfix.ReviewAdapter.ReviewModel
import com.example.meetandfix.SearchAdapter.ShopAdapter
import com.example.meetandfix.SearchAdapter.ShopModel
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.login_layout.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import java.io.ByteArrayOutputStream
import java.util.*
import androidx.annotation.RequiresApi
import java.util.Base64.getDecoder

class CustomerSearchFragment : Fragment(),ShopAdapter.ClickListener {

    //instancial los fragmentos
    private val customerStoreFragment = CustomerStoreFragment()
    var bundle = Bundle()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_search, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.ConseguirTiendas();
        //ir al fragment de la vista del negocio
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CustomerSearchFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    //Función para cambiar el fragmeto actual
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper, fragment)
            val commit = commit()
        }

    override fun ClickedItem(shop: ShopModel) {
        bundle.putString("nombre", shop.nombre);
        bundle.putString("direccion", shop.direccion);
        bundle.putString("Id",shop.id.toString());
        bundle.putString("Imagen",shop.image);
        bundle.putString("Telefono",shop.telefono);
        bundle.putString("Correo",shop.correo);

        val sharedpref = object : shared(this.context){}

        sharedpref.SetIdTiendaReparador(shop.id.toString())

        customerStoreFragment.arguments=bundle;
        nextFragment(customerStoreFragment)
    }

    fun ConseguirTiendas()
    {
        val List: MutableList<ShopModel> = ArrayList()
        val queue = Volley.newRequestQueue(this.context)
        val url2 = ConexionesURL.ConexionUsuario
        val request = @RequiresApi(Build.VERSION_CODES.O)
        object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->


            if(response != null)
            {

                val arreglo = JSONArray(response);
                val Primero = arreglo.getJSONObject(0);


                // Validamos que exista registro de un campo y si es nulo entonces la consulta no trajo nada
                // y mostramos un mensaje de error
                if (Primero.isNull("Id"))
                {
                    Toast.makeText(this.context, "No hay tiendas en este momento", Toast.LENGTH_SHORT).show()
                }
                else {
                    for (i in 0 until arreglo.length()) {
                        val jo: JSONObject = arreglo.getJSONObject(i)
                        if(jo.isNull("Imagen"))
                        {
                            List.add(ShopModel(jo.getInt("Id").toInt(),jo.get("NombreNegocio").toString(),jo.get("Direccion").toString(),jo.get("Telefono").toString(),jo.get("Correo").toString(),jo.get("Imagen").toString()))
                        }
                        else
                        {
                            List.add(ShopModel(jo.getInt("Id").toInt(),jo.get("NombreNegocio").toString(),jo.get("Direccion").toString(),jo.get("Telefono").toString(),jo.get("Correo").toString(),jo.get("Imagen").toString()))
                        }

                    }
                    //Guardamos todos los datos en la clase de shared para tener las varibles de forma global
                    val recycler= view?.findViewById<RecyclerView>(R.id.Search_RecyclerID)
                    recycler?.adapter = ShopAdapter(List,this);


                }

            }

            else
            {
                Toast.makeText(this.context, "error en la peticion",
                    Toast.LENGTH_SHORT).show()
            }

            //Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("funcion", "funcionobtenertiendas")
                return params
            }

        }

        queue.add(request)


    }

}