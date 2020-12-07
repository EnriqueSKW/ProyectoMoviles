package com.example.meetandfix

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.login_layout.*
import kotlinx.android.synthetic.main.register_customer_form_layout.*
import org.json.JSONArray
import org.json.JSONObject

class RegisterCustomerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_customer_form_layout)



        this.btnSubmitCliente.setOnClickListener {
            this.RegistrarUsuario()
        }
    }



    fun RegistrarUsuario()
    {
        val Nombre = txtNombreCliente.text.toString()
        val Apellidos = txtApellidosCliente.text.toString()
        val Telefono = txtTelefonoCliente.text.toString()
        val Direccion = txtDireccionCliente.text.toString()
        val Password = txtContraseñaCliente.text.toString()
        val Correo = txtCorreoCliente.text.toString()
        val imagen = "base64/type=png"


         Toast.makeText(applicationContext,Nombre + Apellidos + Telefono + Direccion + Password + Correo + imagen,Toast.LENGTH_LONG).show()


        val queue = Volley.newRequestQueue(this)
        val url2 = ConexionesURL.Registro
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->

            this.RegresarLogin()


            // Toast.makeText(applicationContext,Resultado,Toast.LENGTH_SHORT).show()

            //Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(applicationContext, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("Correo",Correo)
                params.put("Password", Password)
                params.put("Nombre", Nombre)
                params.put("Apellido",Apellidos)
                params.put("Telefono", Telefono)
                params.put("Direccion",Direccion)
                params.put("Imagen", imagen)

                return params
            }

        }

        queue.add(request)


    }


    //Abrir la activity de login cuando el usuario se registre correctamente o de para atras
    private fun RegresarLogin()
    {
        val intent =  Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }




}