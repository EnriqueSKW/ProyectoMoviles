package com.example.meetandfix.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.meetandfix.ConexionesURL
import com.example.meetandfix.R
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_customer_store.*
import kotlinx.android.synthetic.main.login_layout.*
import org.json.JSONObject
import java.util.*


class ChatFragment : Fragment() {

    var getidcliente: String? = ""
    var getfecha: String? = ""
    var getstatus: String? = ""
    var getnombrecliente: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // super.onCreate(savedInstanceState)
        getidcliente = arguments?.getString("idcliente")
        getfecha = arguments?.getString("fecha")
        getnombrecliente = arguments?.getString("nombrecliente")
        getstatus = arguments?.getString("estado")

        val NombreCliente = view?.findViewById<TextView>(R.id.textNombreClienteID)
        val Fecha = view?.findViewById<TextView>(R.id.TextFechaClienteID)
        val IdCliente = view?.findViewById<TextView>(R.id.textIdClienteinfocita)

        if (NombreCliente != null && Fecha!=null && IdCliente != null) {
            NombreCliente.setText(getnombrecliente)
            Fecha.setText(getfecha)
            IdCliente.setText(getidcliente)
        };

        this.ButtonAceptaarCitaID.setOnClickListener()
        {
            CambiarEstado("aceptada")
        }
        this.ButtonRechazarCitaID.setOnClickListener()
        {
            CambiarEstado("rechazada")
        }
    }

    fun CambiarEstado(Estado: String){
        val status = Estado
        val queue = Volley.newRequestQueue(this.context)
        val url2 = ConexionesURL.ConexionCita
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->


            Toast.makeText(this.context,"Cita cambiada" , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("funcion", "funcionestado")
                params.put("idcliente", textIdClienteinfocita.text.toString())
                params.put("idreparador","1")
                params.put("estado",status)
                return params
            }

        }

        queue.add(request)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    companion object {

    }
}