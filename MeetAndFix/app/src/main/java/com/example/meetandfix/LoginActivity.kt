package com.example.meetandfix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.meetandfix.SQLite.DataBaseHandler
import com.example.meetandfix.UserModel.UserModel
import kotlinx.android.synthetic.main.login_layout.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*


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

            val context= this
            var helper = DataBaseHandler(context)
            var db = helper.readableDatabase
            var ra = db.rawQuery("Select * from Usuario",null)

            if (ra.count==0&&txtCorreo.text.length>0&&txtContraseña.text.length>0)
            {
                helper.InsertData(UserModel(txtCorreo.toString(),txtContraseña.toString()))

            }
           this.callNavigationActivity()
           // if(txtCorreo.text.toString() != "" && txtContraseña.text.toString() != "" )
           // {
                //this.LogInUsuario()
           // }
              /*  else
            {
                Toast.makeText(applicationContext,"no ha llenado los campos necesarios",Toast.LENGTH_SHORT).show()
            }*/


        }


    }

    override fun onResume() {
        super.onResume()
        getDataLogin()
    }

    private fun getDataLogin(){
        val context= this
        var helper = DataBaseHandler(context)
        var db = helper.readableDatabase
        var ra = db.rawQuery("Select * from Usuario",null)
        val List:MutableList<UserModel> = ArrayList()

        if(ra.moveToFirst())
        {
            do{
                List.add(UserModel(ra.getString(1),ra.getString(2)))
            }while(ra.moveToNext())

            txtCorreo.setText(List[0].correo)
            txtContraseña.setText(List[0].contraseña)
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
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->

            val arrayElementos = JSONArray(response)
            //validaciones

            if(arrayElementos.length() > 0 || arrayElementos.length() != null)
            {
                val sharedpref = object : shared(this.applicationContext){}
                var Respuesta = JSONObject(arrayElementos.getString(0))
                var resultado2:String = sharedpref.getNombreUsuario()
                var Resultado:String = Respuesta.get("NombreUsuario").toString()
                //Guardamos todos los datos en la clase de shared para tener las varibles de forma global

                sharedpref.setNombreUsuario(Resultado);
                sharedpref.setIdUsuario( Respuesta.get("IdUsuario").toString());
                sharedpref.setApellidosUsuario( Respuesta.get("ApellidoUsuario").toString());
                sharedpref.setCorreoUsuario( Respuesta.get("CorreoUsuario").toString());
                sharedpref.setTelefonoUsuario( Respuesta.get("TelefonoUsuario").toString());
                sharedpref.setIdUsuario( Respuesta.get("IdUsuario").toString());
                sharedpref.setDireccionUsuario( Respuesta.get("DireccionUsuario").toString());
                sharedpref.setImagenUsuario( Respuesta.get("ImagenUsuario").toString());
                sharedpref.setPassword( Respuesta.get("PasswordUsuario").toString());

                resultado2 = sharedpref.getNombreUsuario()

                Toast.makeText(applicationContext, resultado2,Toast.LENGTH_SHORT).show()
                // mandamos a llamar el main activity de la app despues de guardar los datos
                this.callNavigationActivity()
            }
            else
            {
                Toast.makeText(applicationContext, "Usuario no existe, Correo y/o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            }






           //Toast.makeText(applicationContext, response.toString() , Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener { VolleyError ->
            Toast.makeText(applicationContext, VolleyError.toString(), Toast.LENGTH_LONG ).show()
        }){
            @Throws(AuthFailureError::class)

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("nombre", txtCorreo.text.toString())
                params.put("password",txtContraseña.text.toString())
                return params
            }

        }

        queue.add(request)


    }




  /*   private fun saveTxtAction(){

        //Obtenemos la información de los inputs
       // val strFileName:String =  this.txtFileName.text.toString()
       // val data:String =  this.txtAddSomeMessage.text.toString()

        //Grabar el archivo de texto en storage local
        val fileOutputStream: FileOutputStream

        //importante usar try catch para manejo de errores
        try {
            fileOutputStream =  openFileOutput(strFileName, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        }
        catch (e:FileNotFoundException){
            this.showToast(getString(R.string.WarringErrorSaveFile))
            Log.e("Error Save File",e.toString())
        }
        catch (e: Exception){
            this.showToast(getString(R.string.WarringErrorSaveFile))
            Log.e("Error Save File",e.toString())
        }


        this.showToast(getString(R.string.SuccessfulSaveFile))
        this.cleanTxt()
    }

    private fun loadTxtAction(){

        val strFileName:String =  this.txtFileName.text.toString()
        val url = getFilesDir()
        var file = File(url.toString()+ "/" + strFileName.toString())



        if(file.exists()){

            var fileInputStream: FileInputStream? =  null
            fileInputStream =  openFileInput(strFileName)
            var inputStream: InputStreamReader =  InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader =  BufferedReader(inputStream)

            val stringBuilder:StringBuilder =  StringBuilder()
            var text:String? = null

            while({text =  bufferedReader.readLine(); text}() != null){
                stringBuilder.append(text)
            }

            this.txtAddSomeMessage.setText(stringBuilder.toString().toString())

        }else{
            this.showToast(getString(R.string.WarningMessageFileDoesNotExist))
        }

    }

    private fun deleteTxtAction(){
        val strFileName = this.txtFileName.text

        val url = getFilesDir()
        var file = File(url.toString()+ "/" + strFileName.toString())

        if(file.exists()){
            //EL ARCHIVO EXISTE
            try {
                file.delete()
            }
            catch (e: Exception){
                this.showToast(getString(R.string.WarringErrorSaveFile))
                Log.e("Error Save File",e.toString())
            }
        } else {
            this.showToast(getString(R.string.WarningMessageFileDoesNotExist))
        }
    }
    private fun showToast(text:CharSequence, duration:Int =  Toast.LENGTH_SHORT){
        Toast.makeText(this,text,duration).show()
    }

*/



}