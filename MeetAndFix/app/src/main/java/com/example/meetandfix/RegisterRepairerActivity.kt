package com.example.meetandfix
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.register_customer_form_layout.*
import kotlinx.android.synthetic.main.register_repairer_form_layout.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap
import android.Manifest

class RegisterRepairerActivity : Activity() {
    var imagen = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_repairer_form_layout)

        //Click en el botón de enviar
        this.btnSubmitReparador.setOnClickListener{
            this.callRepairerNavigationActivity()
        }

        this.btnImgReparador.setOnClickListener{
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        this.btnSubmitReparador.setOnClickListener {
            this.RegistraReparador()
        }
    }

    //Abrir la activity de la navegación de reparador
    private fun callRepairerNavigationActivity(){
        val intent =  Intent(this, RepairerMainNavigationActivity::class.java)
        startActivity(intent)
    }


    fun RegistraReparador()
    {
        val Nombre = txtNombreReparador.text.toString()
        val Apellidos = txtApellidosReparador.text.toString()
        val Telefono = txtTelefonoReparador.text.toString()
        val Direccion = txtDireccionReparador.text.toString()
        val Password = txtContraseñaReparador.text.toString()
        val Correo = txtCorreoReparador.text.toString()




        if(Nombre != "" && Apellidos != "" && imagen != "" && Telefono != "" && Password != "" && Correo != "" )
        {
            val queue = Volley.newRequestQueue(this)
            val url2 = ConexionesURL.Registro
            val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->
                Toast.makeText(applicationContext, "Se registro Correctamente" , Toast.LENGTH_SHORT).show()
                this.RegresarLogin()

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
        else
        {
            Toast.makeText(applicationContext,"Asegurese de llenar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

    }

    private fun RegresarLogin()
    {
        val intent =  Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/JPG"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imgProfileCliente.setImageURI(data?.data)

            val bitmap = (this.imgProfileCliente.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,stream)

            var ArregloByte:ByteArray? = stream.toByteArray()
            imagen =  Base64.getEncoder().encodeToString(ArregloByte) //esta es la variable que mandaras en el request como foto


        }
    }
}

