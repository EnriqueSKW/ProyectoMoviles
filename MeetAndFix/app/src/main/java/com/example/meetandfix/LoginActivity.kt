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
import android.util.Log


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

           // val context= this
           // var helper = DataBaseHandler(context)
           // var db = helper.readableDatabase
           // var ra = db.rawQuery("Select * from Usuario",null)

           // if (ra.count==0&&txtCorreo.text.length>0&&txtContraseña.text.length>0)
           // {
            //    helper.InsertData(UserModel(txtCorreo.toString(),txtContraseña.toString()))

           // }
          // this.callNavigationActivity()
            if(txtCorreo.text.toString() != "" && txtContraseña.text.toString() != "" )
            {
                this.LogInUsuario()
            }
                else
            {
                Toast.makeText(applicationContext,"no ha llenado los campos necesarios",Toast.LENGTH_SHORT).show()
            }


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
    private fun callRepairerNavigationActivity(){


        val intent =  Intent(this, RepairerMainNavigationActivity::class.java)

        startActivity(intent)

    }


    fun LogInUsuario(){
        val queue = Volley.newRequestQueue(this)
        val url2 = ConexionesURL.ConexionUsuario
        val request = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> { response ->

            //pasar el resultado a un objeto
            Log.d("Respuesta",response);

            //para recuperar la informacion del objeto es asi
            //arreglo.get("Id") el campo tal cual es

            if(response != null)
            {
                val arreglo = JSONObject(response);
                val sharedpref = object : shared(this.applicationContext){}

                // Validamos que exista registro de un campo y si es nulo entonces la consulta no trajo nada
                // y mostramos un mensaje de error
                if (arreglo.isNull("Id"))
                {
                    Toast.makeText(applicationContext, "USUARIO NO EXISTE O CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show()
                }
                else {
                    //Guardamos todos los datos en la clase de shared para tener las varibles de forma global
                    Log.d("TAG", arreglo.toString());
                    sharedpref.setNombreUsuario(arreglo.get("Nombre").toString());
                    sharedpref.setIdUsuario(arreglo.get("Id").toString());
                    sharedpref.setPassword(arreglo.get("Contraseña").toString());
                    sharedpref.setApellidosUsuario(arreglo.get("Apellidos").toString());
                    sharedpref.setCorreoUsuario(arreglo.get("Correo").toString());
                    sharedpref.SetTipoUsuario(arreglo.get("TipoUsuario").toString());
                    sharedpref.setDireccionUsuario(arreglo.get("Direccion").toString());
                    sharedpref.setTelefonoUsuario(arreglo.get("Telefono").toString());
                    sharedpref.setImagenUsuario(arreglo.get("Imagen").toString());
                    var resultado2 = "Bienvenido " + sharedpref.getNombreUsuario()

                    Toast.makeText(applicationContext, resultado2, Toast.LENGTH_SHORT).show()
                    // mandamos a llamar el main activity de la app despues de guardar los datos
                    if(arreglo.get("TipoUsuario").toString()=="1")
                    this.callNavigationActivity()
                    else
                    this.callRepairerNavigationActivity()
                }
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
                params.put("funcion", "funcionlogin")
                params.put("correo", txtCorreo.text.toString())
                params.put("contraseña",txtContraseña.text.toString())
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