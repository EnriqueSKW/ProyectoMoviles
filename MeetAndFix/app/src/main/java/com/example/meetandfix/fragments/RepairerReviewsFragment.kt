package com.example.meetandfix.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.meetandfix.UserModel.UserModel
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.fragment_customer_reviews.*
import org.json.JSONArray
import org.json.JSONObject

class RepairerReviewsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repairer_reviews, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ObtainReviews()



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
                    val recycler= view?.findViewById<RecyclerView>(R.id.Repairer_ReviesRecyclerID)
                    recycler?.adapter = ReviewAdapter(List)
                    //sharedpref.setNombreUsuario(arreglo.get("Nombre").toString());

                }

            }
            //Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            val sharedpref = object : shared(context){}
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("funcion", "funcionobtenerreseñas")
                params.put("idreparador", sharedpref.getIdUsuario())
                return params
            }

        }

        queue.add(request)


    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerReviewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}