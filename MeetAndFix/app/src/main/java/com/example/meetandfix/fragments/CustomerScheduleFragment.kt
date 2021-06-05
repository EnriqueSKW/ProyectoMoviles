package com.example.meetandfix.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.meetandfix.ConexionesURL
import com.example.meetandfix.R
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.fragment_customer_reviews.*
import kotlinx.android.synthetic.main.fragment_customer_schedule.*
import kotlinx.android.synthetic.main.login_layout.*
import org.json.JSONObject


class CustomerScheduleFragment : Fragment() {

var Fechafinal:String = ""
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
        return inflater.inflate(R.layout.fragment_customer_schedule, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.btnAgendarCitaCliente.setOnClickListener()
        {
            val dia = this.datePicker.dayOfMonth
            val mes = this.datePicker.month
            val anio = this.datePicker.year
              Fechafinal = dia.toString() + "/" + mes.toString() + "/" + anio.toString()
            this.AgendarCita()
        }

    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            CustomerScheduleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }



    fun AgendarCita(){
        val queue = Volley.newRequestQueue(this.context)
        val url2 = ConexionesURL.ConexionCita
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->

            if(response != null)
            {
                Toast.makeText(this.context, "Se agendo tu cita correctamente",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this.context, "Paso algo extrañp",Toast.LENGTH_SHORT).show()
            }

            //Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            val sharedpref = object : shared(context){}
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("funcion", "funcionagendar")
                params.put("idcliente", sharedpref.getIdUsuario())
                params.put("idreparador",sharedpref.getTiendaReparador())
                params.put("nombrecliente", sharedpref.getNombreUsuario())
                params.put("fecha",Fechafinal)
                return params
            }

        }

        queue.add(request)


    }
}