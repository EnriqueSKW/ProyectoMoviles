package com.example.meetandfix.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.meetandfix.R
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import java.util.*
class CustomerProfileFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_profile, container, false)
    }

    // Aqui se tienen que poner el codigo una vez que se instancia el fragment
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharedpref = object : shared(this.context){}
        val Nombre = sharedpref.getNombreUsuario()
        val Apellidos = sharedpref.getApellidosUsuario()
        val Correo = sharedpref.getCorreoUsuario()
        val Direccion  = sharedpref.getDireccionUsuario()
        val Telefono = sharedpref.getTelefonoUsuario()
        val Imagen = sharedpref.getImagenUsuario()
        val imgByteArrayFA =  Base64.getDecoder().decode(Imagen)

        lblNombrePerfilCliente.setText(Nombre)
        txtNombreClienteEdit.setText(Nombre)
        txtApellidosClienteEdit.setText(Apellidos)
        txtCorreoClienteEdit.setText(Correo)
        txtDireccionClienteEdit.setText(Direccion)
        txtTelefonoClienteEdit.setText(Telefono)
        imgUserProfileCliente.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArrayFA,0,imgByteArrayFA.size))


    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            CustomerProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}