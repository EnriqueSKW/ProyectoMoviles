package com.example.meetandfix.fragments

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
import kotlinx.android.synthetic.main.fragment_customer_reviews.*
import kotlinx.android.synthetic.main.login_layout.*
import org.json.JSONArray
import org.json.JSONObject


class CustomerReviewsFragment : Fragment() {



    //instancial los fragmentos
    private val customerWriteReviewFragment = CustomerWriteReviewFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_reviews, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ObtainReviews()


        this.btnEscribirReseñaCliente.setOnClickListener {
            nextFragment(customerWriteReviewFragment)
        }


    }

    fun ObtainReviews(){
        val List:MutableList<ReviewModel> = ArrayList()
        val queue = Volley.newRequestQueue(this.context)
        val url2 = ConexionesURL.ConexionReseña
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->

            //pasar el resultado a un objeto
            Log.d("Respuesta",response);

            //para recuperar la informacion del objeto es asi
            //arreglo.get("Id") el campo tal cual es

            if(response != null)
            {

                val arreglo = JSONArray(response);
                val Primero = arreglo.getJSONObject(0);


                // Validamos que exista registro de un campo y si es nulo entonces la consulta no trajo nada
                // y mostramos un mensaje de error
                if (Primero.isNull("Id"))
                {
                    Toast.makeText(this.context, "No hay reseñas de esta tienda", Toast.LENGTH_SHORT).show()
                }
                else {
                    for (i in 0 until arreglo.length()) {
                        val jo: JSONObject = arreglo.getJSONObject(i)
                        List.add(ReviewModel(jo.getInt("Id").toInt(),jo.get("NombreCliente").toString(),jo.get("Reseña").toString(),jo.get("IdCliente").toString(),jo.get("IdReparador").toString()))
                    }
                    //Guardamos todos los datos en la clase de shared para tener las varibles de forma global
                    val recycler= view?.findViewById<RecyclerView>(R.id.Review_RecyclerViewID)
                    recycler?.adapter = ReviewAdapter(List)
                    //sharedpref.setNombreUsuario(arreglo.get("Nombre").toString());

                }

            }
                                                                                                  //Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("funcion", "funcionobtenerreseñas")
                params.put("idreparador", "1")
                return params
            }

        }

        queue.add(request)


    }
    companion object {
        fun newInstance(param1: String, param2: String) =
            CustomerReviewsFragment().apply {

            }
    }

    //Función para cambiar el fragmeto actual
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper, fragment)
            val commit = commit()
        }
}