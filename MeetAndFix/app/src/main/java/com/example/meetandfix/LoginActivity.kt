package com.example.meetandfix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login_layout.*
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        //Click en el botón de registrarse
        this.btnRegistro.setOnClickListener {

            this.callRegisterTypeActivity()
        }

        //Click en el botón de entrar
        this.btnLogin.setOnClickListener {
            this.LogInUsuario()
            this.callNavigationActivity()
        }

    }

    //Abrir la activity de seleccionar el tipo de usuario a registrar
    private fun callRegisterTypeActivity(){
            val intent =  Intent(this, RegisterTypeActivity::class.java)
            startActivity(intent)
    }

    //Abrir la activity de la navegación principal
    private fun callNavigationActivity(){
        val intent =  Intent(this, CustomerMainNavigationActivity::class.java)
        startActivity(intent)
    }



    fun LogInUsuario(){
        val queue = Volley.newRequestQueue(this)
        val url2 = ConexionesURL.Login
        val NombreUSuario = txtCorreo.text
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->
            Toast.makeText(applicationContext, NombreUSuario.toString() , Toast.LENGTH_SHORT).show()
           // Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(applicationContext, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                //   params.put("nombre", usuario)
            //    params.put("password",pass)
                return params
            }

        }

        queue.add(request)


    }
}