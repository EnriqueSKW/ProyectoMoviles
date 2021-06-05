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
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.fragment_customer_reviews.*
import kotlinx.android.synthetic.main.fragment_customer_reviews.btnEscribirReseñaCliente
import kotlinx.android.synthetic.main.fragment_customer_write_review.*
import kotlinx.android.synthetic.main.login_layout.*
import org.json.JSONObject


class CustomerWriteReviewFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_write_review, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // super.onCreate(savedInstanceState)

        this.btnPublicarReseñaCliente.setOnClickListener {
            PublishReview();
        }


    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            CustomerWriteReviewFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    fun PublishReview(){

        if(txtEscribirReseñaCliente.text.toString()!=null && txtEscribirReseñaCliente.text.toString()!="")
        {
            val queue = Volley.newRequestQueue(this.context)
            val url2 = ConexionesURL.ConexionReseña
            val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->

                Toast.makeText(this.context,"Reseña mandada", Toast.LENGTH_SHORT).show()
                txtEscribirReseñaCliente.setText("");
            }, Response.ErrorListener { VolleyError ->
                Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
            }){
                val sharedpref = object : shared(context){}
                @Throws(AuthFailureError::class)

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params.put("funcion", "funcionreseñar")
                    params.put("idcliente", sharedpref.getIdUsuario())
                    params.put("reseña",txtEscribirReseñaCliente.text.toString())
                    params.put("nombrecliente","cliente")
                    params.put("idreparador",sharedpref.getTiendaReparador())
                    return params
                }

            }

            queue.add(request)
        }
     else{
            Toast.makeText(this.context,"Por favor escriba una reseña", Toast.LENGTH_SHORT).show()
        }

    }
}