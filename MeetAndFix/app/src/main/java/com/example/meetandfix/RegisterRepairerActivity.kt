package com.example.meetandfix
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.register_repairer_form_layout.*

class RegisterRepairerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_repairer_form_layout)

        //Click en el botón de enviar
        this.btnSubmitReparador.setOnClickListener{
            this.callRepairerNavigationActivity()
        }
    }

    //Abrir la activity de la navegación de reparador
    private fun callRepairerNavigationActivity(){
        val intent =  Intent(this, RepairerMainNavigationActivity::class.java)
        startActivity(intent)
    }
}