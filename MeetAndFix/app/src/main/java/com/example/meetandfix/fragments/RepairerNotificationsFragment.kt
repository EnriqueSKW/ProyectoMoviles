package com.example.meetandfix.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.meetandfix.ConexionesURL
import com.example.meetandfix.NotificationAdapter.NotifAdapter
import com.example.meetandfix.NotificationAdapter.NotifModel
import com.example.meetandfix.R
import com.example.meetandfix.shared
import org.json.JSONArray
import org.json.JSONObject


class RepairerNotificationsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repairer_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //super.onCreate(savedInstanceState)
        this.ConseguirNotificaciones()
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerNotificationsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    fun ConseguirNotificaciones()
    {
        val List: MutableList<NotifModel> = ArrayList()
        val queue = Volley.newRequestQueue(this.context)
        val url2 = ConexionesURL.ConexionNotificaion
        val request = @RequiresApi(Build.VERSION_CODES.O)
        object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->


            if(response != null)
            {

                val arreglo = JSONArray(response);
                val Primero = arreglo.getJSONObject(0);


                // Validamos que exista registro de un campo y si es nulo entonces la consulta no trajo nada
                // y mostramos un mensaje de error
                if (Primero.isNull("IdCliente"))
                {
                    Toast.makeText(this.context, "No tienes notificaciones en este momento", Toast.LENGTH_SHORT).show()
                }
                else {
                    for (i in 0 until arreglo.length()) {
                        val jo: JSONObject = arreglo.getJSONObject(i)
                        if(jo.isNull("IdCliente"))
                        {
                            List.add(NotifModel(jo.getInt("IdCliente").toInt(),jo.get("Nombre").toString(),jo.get("MensajeNotificacion").toString()))
                        }
                        else
                        {
                            List.add(NotifModel(jo.getInt("IdCliente").toInt(),jo.get("Nombre").toString(),jo.get("MensajeNotificacion").toString()))
                        }

                    }
                    //Guardamos todos los datos en la clase de shared para tener las varibles de forma global
                    val recycler= view?.findViewById<RecyclerView>(R.id.Repairer_NotificationsRecyclerID)
                    recycler?.adapter = NotifAdapter(List);


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
            val sharedpref = object : shared(context){}
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("funcion", "funcionnotificacionescliente")
                params.put("idcliente", sharedpref.getIdUsuario())
                return params
            }

        }

        queue.add(request)


    }
}