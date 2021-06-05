package com.example.meetandfix.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil.decode
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.meetandfix.R
import kotlinx.android.synthetic.main.fragment_customer_store.*
import java.io.InputStream
import java.lang.Byte.decode
import java.util.*


class CustomerStoreFragment : Fragment() {

    //instanciar los fragmentos
    private val customerReviewsFragment = CustomerReviewsFragment()
    private val customerScheduleFragment = CustomerScheduleFragment()
    private val customerChatFragment = ChatFragment()
    var getNombre: String? = ""
    var getDireccion: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_customer_store, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // super.onCreate(savedInstanceState)
        getNombre = arguments?.getString("nombre")
        getDireccion = arguments?.getString("direccion")
        var getImagen = arguments?.getString("Imagen")
        val Nombre = view?.findViewById<TextView>(R.id.lblNegocioNombreCliente)
        val Direccion = view?.findViewById<TextView>(R.id.lblDireccionRealCliente)
        if (Nombre != null && Direccion!=null) {
            Nombre.setText(getNombre);
            Direccion.setText(getDireccion);
        };
        if(getImagen != null)
        {
            val imgByteArrayFA =  Base64.getDecoder().decode(getImagen)
            val decodedImage = BitmapFactory.decodeByteArray(imgByteArrayFA, 0, imgByteArrayFA.size)
            val Imagen =  view?.findViewById<ImageView>(R.id.imgNegocioMainImage)
            Imagen?.setImageBitmap(decodedImage)
        }



        //ir al fragmento de reseñas
        this.btnVerResñasCliente.setOnClickListener {
            nextFragment(customerReviewsFragment)
        }

        //ir al fragmento de agendar cita
        this.btnAgendarCliente.setOnClickListener {
            nextFragment(customerScheduleFragment)
        }

        //ir al fragmento del chat
        this.btnEnviarMensajeCliente.setOnClickListener {
            nextFragment(customerChatFragment)
        }

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CustomerStoreFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    //Función para cambiar el fragmeto actual
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper, fragment)
            val commit = commit()
        }


}