package com.example.meetandfix

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.register_type_layout.*

class RegisterTypeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_type_layout)

        //Click en el botón de registrarse como cliente
        this.btnRegistroCliente.setOnClickListener {
            this.callCustomerRegisterActivity()
        }

        //Click en el botón de registrarse como reparador
        this.btnRegistroReparador.setOnClickListener {
            this.callRepairerRegisterActivity()
        }


    }

    //Abrir la activity del formulario de cliente
    private fun callCustomerRegisterActivity(){
        val intent =  Intent(this, RegisterCustomerActivity::class.java)
        startActivity(intent)
    }

    //Abrir la activity del formulario de reparador
    private fun callRepairerRegisterActivity(){
        val intent =  Intent(this, RegisterRepairerActivity::class.java)
        startActivity(intent)
    }
}