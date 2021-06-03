package com.example.meetandfix.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.meetandfix.ConexionesURL
import com.example.meetandfix.R
import com.example.meetandfix.RegisterCustomerActivity
import com.example.meetandfix.shared
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import kotlinx.android.synthetic.main.register_customer_form_layout.*
import java.io.ByteArrayOutputStream
import java.util.*
import android.Manifest
import kotlinx.android.synthetic.main.register_customer_form_layout.*

import kotlin.collections.HashMap

class CustomerProfileFragment : Fragment() {

    var imagen = ""
    var Idglobal =""


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
        val Password = sharedpref.getPasswordUsuario()
        val Direccion  = sharedpref.getDireccionUsuario()
        val Telefono = sharedpref.getTelefonoUsuario()
        val Imagen = sharedpref.getImagenUsuario()
        val imgByteArrayFA =  Base64.getDecoder().decode(Imagen)
        val decodedImage = BitmapFactory.decodeByteArray(imgByteArrayFA, 0, imgByteArrayFA.size)
        val Id = sharedpref.getIdUsuario()
        Idglobal = Id
        imagen = Imagen

        lblNombrePerfilCliente.setText(Nombre)
        txtNombreClienteEdit.setText(Nombre)
        txtApellidosClienteEdit.setText(Apellidos)
        txtCorreoClienteEdit.setText(Correo)
        txtDireccionClienteEdit.setText(Direccion)
        txtTelefonoClienteEdit.setText(Telefono)
        txtContraseñaClienteEdit.setText(Password)
        imgUserProfileCliente.setImageBitmap(decodedImage)


        //Boton que manda a llamar la funcion para modificar los datos del usuario
        this.btnSubmitClienteEdit.setOnClickListener {
            ModificarUsuario()
        }
    }



    fun ModificarUsuario()
    {
        
        val sharedpref = object : shared(this.context){}
        val Nombre = txtNombreClienteEdit.text.toString()
        val Apellidos = txtApellidosClienteEdit.text.toString()
        val Telefono = txtTelefonoClienteEdit.text.toString()
        val Direccion = txtDireccionClienteEdit.text.toString()
        val Password = txtContraseñaClienteEdit.text.toString()



        if(Nombre != "" && Apellidos != "" && imagen != "" && Telefono != "" && Password != ""  )
        {
            val queue = Volley.newRequestQueue(this.context)
            val url = ConexionesURL.ConexionUsuario
            val request = object : StringRequest(Request.Method.POST, url, Response.Listener<String> { response ->
                Toast.makeText(this.context, "Se registro Correctamente" , Toast.LENGTH_SHORT).show()
              

            }, Response.ErrorListener { VolleyError ->
                Toast.makeText(this.context, VolleyError.toString(), Toast.LENGTH_LONG ).show()
            }){
                @Throws(AuthFailureError::class)

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params.put("idusuario",sharedpref.getIdUsuario())
                    params.put("contraseña", Password)
                    params.put("nombre", Nombre)
                    params.put("apellidos",Apellidos)
                    params.put("telefono", Telefono)
                    params.put("direccion",Direccion)
                    params.put("imagen",imagen)
                    params.put("funcion","modificarusuario")
                    return params
                }

            }

            queue.add(request)
        }
        else
        {
            Toast.makeText(this.context,"Asegurese de llenar todos los campos",Toast.LENGTH_SHORT).show()
            return
        }




    }



    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }


    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/PNG"
        startActivityForResult(intent, CustomerProfileFragment.IMAGE_PICK_CODE)
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            CustomerProfileFragment.PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(getActivity()?.getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == CustomerProfileFragment.IMAGE_PICK_CODE){
            imgProfileCliente.setImageURI(data?.data)

            val bitmap = (this.imgProfileCliente.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,stream)

            var ArregloByte:ByteArray? = stream.toByteArray()
            imagen =  Base64.getEncoder().encodeToString(ArregloByte) //esta es la variable que mandaras en el request como foto


        }
    }


}