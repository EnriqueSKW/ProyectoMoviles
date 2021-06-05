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
import com.example.meetandfix.SearchAdapter.ShopAdapter
import com.example.meetandfix.fragments.CitasAdapter.CitaAdapter
import com.example.meetandfix.fragments.CitasAdapter.CitaModel
import org.json.JSONArray
import org.json.JSONObject


class RepairerCalendarFragment : Fragment(), CitaAdapter.ClickListener  {

    private val infoCitaFragment = ChatFragment()
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repairer_calendar, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ObtainCitas()


    }
    fun ObtainCitas(){
        val List:MutableList<CitaModel> = ArrayList()
        val queue = Volley.newRequestQueue(this.context)
        val url2 = ConexionesURL.ConexionCita
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
                if (Primero.isNull("IdReparador"))
                {
                    Toast.makeText(this.context, "No hay citas agendadas", Toast.LENGTH_SHORT).show()
                }
                else {
                    for (i in 0 until arreglo.length()) {
                        val jo: JSONObject = arreglo.getJSONObject(i)
                        List.add(CitaModel(jo.get("IdReparador").toString(),jo.get("IdCliente").toString(),jo.get("Fecha").toString(),jo.get("NombreCliente").toString(),jo.get("Estado").toString()))
                    }
                    //Guardamos todos los datos en la clase de shared para tener las varibles de forma global
                    val recycler= view?.findViewById<RecyclerView>(R.id.Repairer_CitasAgendadasRecyclerID)
                    recycler?.adapter = CitaAdapter(List,this)
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
                params.put("funcion", "funcioncitasreparador")
                params.put("idreparador", "1")
                return params
            }

        }

        queue.add(request)


    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerCalendarFragment().apply {
                arguments = Bundle().apply {
                   
                }
            }
    }
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper_repairer, fragment)
            val commit = commit()
        }

    override fun ClickedItem(cita: CitaModel) {
        bundle.putString("idcliente", cita.idCliente);
        bundle.putString("nombrecliente", cita.nombreCliente);
        bundle.putString("fecha",cita.fecha);

        infoCitaFragment.arguments=bundle;
        nextFragment(infoCitaFragment)
    }
}
